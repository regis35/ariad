package bzh.ariad.checker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bzh.ariad.checker.dto.CardIdInformation;
import bzh.ariad.checker.dto.GenderEnum;
import bzh.ariad.checker.exception.NotValidCardIdException;
import bzh.ariad.checker.parser.mrz.BirthDateJson;
import bzh.ariad.checker.parser.mrz.ControlsJson;
import bzh.ariad.checker.parser.mrz.HolderDetailJson;
import bzh.ariad.checker.parser.mrz.MrzRequestJson;
import bzh.ariad.checker.parser.mrz.MrzResponseJson;
import bzh.ariad.checker.parser.mrz.MrzResponseParser;

/**
 * Checker card ID service
 * 
 * @author Regis Le Coz
 */
@Service
public class CheckerCardIdService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheckerCardIdService.class);
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private MrzResponseParser mrzParser;

	@Value("${ariad.url}")
	private String ariadUrl;

	@Value("${ariad.token}")
	private String ariadToken;

	private static final String AUTHORIZATION_KEY = "Authorization";
	private static final String TOKEN_TEMPLATE = "Basic %s";

	/**
	 * Call ariadNext service, and get cardId information
	 * 
	 * @param userId UserId
	 * @param lines  Lines of CardId
	 * @return CardIdInformation Object
	 * @throws NotValidCardIdException
	 */
	public CardIdInformation getInformation(String userId, List<String> lines) throws NotValidCardIdException {

		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION_KEY, String.format(TOKEN_TEMPLATE, ariadToken));
		headers.setContentType(MediaType.APPLICATION_JSON);

		String body = buildBody(lines);
		LOGGER.info("Send to url : " + ariadUrl + "/task/mrz-> content : " + body);
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> respEntity = restTemplate.exchange(ariadUrl + "/task/mrz", HttpMethod.POST, entity,
				String.class);

		String response = respEntity.getBody();
		LOGGER.debug("Body form ariad service : " + response);
		
		MrzResponseJson mrzJson = null;
		try {
			mrzJson = mrzParser.parse(response);
		} catch (Exception e) {
			LOGGER.error("Error when parsin Mrz json : " + e.getMessage());
			throw new RuntimeException("Error when parsing mrz json");
		}
		
		if (!isValidCardId(mrzJson) || mrzJson.getHolderDetail() != null) {
			throw new NotValidCardIdException("The cardId of user " + userId + " is invalid");
		}

		CardIdInformation info = buildCardIdInformation(userId, mrzJson.getHolderDetail());
		
		LOGGER.debug(info.toString());
		return info;
	}

	public static void main(String[] args) {
		
		List<String> s = new ArrayList<String>();
		s.get(10);
		
	}
	
	private String buildBody(List<String> lines) {
		MrzRequestJson request = new MrzRequestJson();
		try {
			request.setLine1(lines.get(0));
			request.setLine2(lines.get(1));
			request.setLine3(lines.get(2));
		} catch (IndexOutOfBoundsException e) {
			// nothing to do
		}
		try {
			return mapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error when reading mrz json for body request");
		}
	}
	
	/**
	 * Transform HolderDetailJson object to CardIdInformation Object
	 * 
	 * @return CardIdInformation Object
	 */
	private CardIdInformation buildCardIdInformation(String userId, HolderDetailJson holderDetailJson) {

		GenderEnum gender = GenderEnum.valueOf(holderDetailJson.getGender());

		String firstName = holderDetailJson.getFirstName().stream().map(Object::toString)
				.collect(Collectors.joining(" "));
		String lastName = holderDetailJson.getLastName().stream().map(Object::toString)
				.collect(Collectors.joining(" "));

		CardIdInformation cardIdInfo = new CardIdInformation();
		
		BirthDateJson birthday = holderDetailJson.getBirthDate();
		if (birthday != null) {
		
			LocalDate birth = LocalDate.of(birthday.getYear(), birthday.getMonth(), birthday.getDay());
			cardIdInfo.setBirthDate(birth);
		}
		
		cardIdInfo.setFirstName(firstName);
		cardIdInfo.setGender(gender);
		cardIdInfo.setLastName(lastName);
		cardIdInfo.setUserId(userId);
		
		return cardIdInfo;
	}

	/**
	 * Check cardId Control
	 * 
	 * @param mrzJson
	 * @return true if cardId is valid else return false
	 */
	private boolean isValidCardId(MrzResponseJson mrzJson) {
		boolean result = false;
		if (mrzJson != null) {
			List<ControlsJson> controls = mrzJson.getControls();
			if (controls != null && !controls.isEmpty()) {
				result = true;
				for (ControlsJson controlsJson : controls) {
					if (!"OK".equals(controlsJson.getResult())) {
						result = false;
						break;
					}
				}
			}
		}
		return result;
	}

}
