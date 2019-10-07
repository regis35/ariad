package bzh.ariad.checker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bzh.ariad.checker.entity.TokenEntity;
import bzh.ariad.checker.repository.TokenRepository;
import bzh.ariad.common.SocialUserDto;
import bzh.ariad.common.UserIdDto;

/**
 * Token service impl
 * 
 * @author regislecoz
 */
@Service
public class TokenServiceImpl implements TokenService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

	@Autowired
	private TokenRepository repository;

	@Override
	public UserIdDto createUser(SocialUserDto socialUser) {
		LOGGER.debug("Create new user " + socialUser.toString());

		TokenEntity t = new TokenEntity();
		t.setId(UUID.randomUUID().toString());
		t.setIdProvider(socialUser.getId());
		t.setProvider(socialUser.getProvider());
		t.setUserInfoEndpoint(socialUser.getUserInfoEndpoint());
		t.setTimeToLive(socialUser.getToken().getTtl());
		t.setToken(socialUser.getToken().getToken());
		t.setTokenType(socialUser.getToken().getType());

		repository.save(t);

		UserIdDto id = new UserIdDto();
		id.setId(t.getId());

		return id;
	}

	@Override
	public List<TokenEntity> list() {
		List<TokenEntity> tokens = new ArrayList<>();
		repository.findAll().forEach(tokens::add);
		return tokens;
	}

}
