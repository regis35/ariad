package bzh.ariad.checker.service;

import java.util.List;

import bzh.ariad.checker.entity.TokenEntity;
import bzh.ariad.common.SocialUserDto;
import bzh.ariad.common.UserIdDto;

/**
 * Token service
 * @author regislecoz
 */
public interface TokenService {

	/**
	 * Create new user token
	 * @param socialUser
	 * @return
	 */
	public UserIdDto createUser( SocialUserDto socialUser);

	/**
	 * List all token on db
	 * @return
	 */
	public List<TokenEntity> list();
	
}
