package bzh.ariad.checker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bzh.ariad.checker.dto.CardIdInformation;
import bzh.ariad.checker.dto.GenderEnum;
import bzh.ariad.checker.exception.NotValidCardIdException;
import bzh.ariad.checker.parser.MrzJson;
import bzh.ariad.checker.parser.MrzParser;

/**
 * Checker card ID service
 * 
 * @author Regis Le Coz
 */
@Service
public class CheckerCardIdService {

	@Autowired
	private MrzParser mrzParser;
	
	@Value("${ariad.url}")
	private String ariadUrl;

	@Value("${ariad.token}")
	private String ariadToken;

	private static final String BODY_TEMPLATE = "{\"line1\": \"%s\",\"line2\": \"%s\"}";
	private static final String AUTHORIZATION_KEY = "Authorization";
	private static final String TOKEN_TEMPLATE = "Basic %s";

	/**
	 * Call ariadNext service, and get cardId information
	 * @param userId UserId
	 * @param lines Lines of CardId
	 * @return CardIdInformation Object
	 * @throws NotValidCardIdException
	 */
	public CardIdInformation getInformation(String userId, List<String> lines) throws NotValidCardIdException {

		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION_KEY, String.format(TOKEN_TEMPLATE, ariadToken));
		headers.setContentType(MediaType.APPLICATION_JSON);

		String body = String.format(BODY_TEMPLATE, lines.get(0), lines.get(1));
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> respEntity = restTemplate.exchange(ariadUrl + "/task/mrz", HttpMethod.POST, entity,
				String.class);

		try {
			MrzJson mrzJson = mrzParser.parse(respEntity.getBody());
			
			
			
			
		} catch (Exception e) {
			throw new RuntimeException("Error when parsin mrz json");
		}
		
		
		CardIdInformation cardIdInfo = new CardIdInformation();
		cardIdInfo.setBirthDate(LocalDate.now());
		cardIdInfo.setFirstName("Régis");
		cardIdInfo.setGender(GenderEnum.M);
		cardIdInfo.setLastName("Le Coz");
		cardIdInfo.setUserId(userId);

		return cardIdInfo;
	}
	
	private boolean isValidCardId(MrzJson mrzJson) {
		
	}

}
