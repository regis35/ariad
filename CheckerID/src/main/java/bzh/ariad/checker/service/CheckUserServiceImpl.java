package bzh.ariad.checker.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bzh.ariad.checker.dto.CardIdInformation;
import bzh.ariad.checker.exception.NotValidCardIdException;

/**
 * Check user service
 * 
 * @author regislecoz
 *
 */
@Service
public class CheckUserServiceImpl implements CheckUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheckUserServiceImpl.class);
	
	@Autowired
	private CheckIOService checkIOService;
	
	/**
	 * Valid user identity
	 * 
	 * @return true if identity is valid else return false
	 */
	@Override
	public boolean isValid(String userId, List<String> lines) {
		boolean result = false;
		// 1- extract MRZ to uploaded cardID
		try {
			// 2- Call checkIO service
			CardIdInformation userInfoMrz = checkIOService.getInformation(userId, lines);
			LOGGER.debug("userInfoMrz : " + userInfoMrz.toString());
			
			// 3- Save userInfoMrz into MySql database
			
			// 4- Call getUserInfo to SocialServiceFacebook
			
			
			// 5- Valid userInfoMrz and userInfoSocial
			
			// 6- update userInfoMrz into MySql database
			
			// 7- Verify social data
			
		} catch (NotValidCardIdException e) {
			LOGGER.error("CardID of user [] is invalid", userId);			
			return false;
		}
		return result;
	}

}
