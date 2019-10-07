package bzh.ariad.checker.ws;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bzh.ariad.checker.entity.TokenEntity;
import bzh.ariad.checker.service.TokenService;
import bzh.ariad.common.SocialUserDto;
import bzh.ariad.common.UserIdDto;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("users")
@Api("Api for user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserIdDto createUser(@RequestBody SocialUserDto socialUser) {
	
		LOGGER.debug("Create new user " + socialUser.toString());
		
		return tokenService.createUser(socialUser);
	}
	
	@GetMapping
	public List<TokenEntity> list() {
		return tokenService.list();
	}
}
