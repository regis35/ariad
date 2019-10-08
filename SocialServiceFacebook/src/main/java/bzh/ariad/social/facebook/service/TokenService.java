package bzh.ariad.social.facebook.service;

import java.util.List;

import bzh.ariad.common.SocialUserDto;
import bzh.ariad.common.UserIdDto;
import bzh.ariad.social.facebook.entity.TokenEntity;

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
	public UserIdDto createUser(SocialUserDto socialUser);

	/**
	 * List all token on db
	 * @return
	 */
	public List<TokenEntity> list();
	
}
