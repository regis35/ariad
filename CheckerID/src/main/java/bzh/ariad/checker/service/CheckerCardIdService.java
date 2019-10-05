package bzh.ariad.checker.service;

import java.util.List;

import bzh.ariad.checker.dto.CardIdInformation;
import bzh.ariad.checker.exception.NotValidCardIdException;

public interface CheckerCardIdService {

	/**
	 * Call ariadNext service, and get cardId information
	 * 
	 * @param userId UserId
	 * @param lines  Lines of CardId
	 * @return CardIdInformation Object
	 * @throws NotValidCardIdException
	 */
	public CardIdInformation getInformation(String userId, List<String> lines) throws NotValidCardIdException;
}
