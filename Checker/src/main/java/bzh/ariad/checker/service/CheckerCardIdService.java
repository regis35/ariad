package bzh.ariad.checker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import bzh.ariad.checker.dto.CardIdInformation;
import bzh.ariad.checker.dto.GenderEnum;
import bzh.ariad.checker.exception.NotValidCardIdException;

/**
 * Checker card ID service
 * @author Regis Le Coz
 */
@Service
public class CheckerCardIdService {

	public CardIdInformation getInformation(String userId, List<String> line) throws NotValidCardIdException {
		
		CardIdInformation cardIdInfo = new CardIdInformation();
		cardIdInfo.setBirthDate(LocalDate.now());
		cardIdInfo.setFirstName("Régis");
		cardIdInfo.setGender(GenderEnum.M);
		cardIdInfo.setLastName("Le Coz");
		cardIdInfo.setUserId(userId);
		
		return cardIdInfo;
	}
	
}
