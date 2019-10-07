package bzh.ariad.social.facebook.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import bzh.ariad.common.UserDto;
import bzh.ariad.common.UserIdDto;
import bzh.ariad.social.facebook.error.ApiError;
import bzh.ariad.social.facebook.exception.UserComposantException;
import bzh.ariad.social.facebook.service.FacebookService;

@RestController
public class FacebookController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookController.class);

	@Autowired
	private FacebookService service;

	@GetMapping("/login/success")
	public UserIdDto login(Model model, OAuth2AuthenticationToken authentication) throws UserComposantException {

		LOGGER.error("facebook login success");
		return service.senUserInfoToCheckerID(authentication);
	}

	@GetMapping("/login/failure")
	public ResponseEntity<Object> loginFailure(Model model, OAuth2AuthenticationToken authentication) {

		LOGGER.error("facebook login failure");

		ApiError error = new ApiError(HttpStatus.UNAUTHORIZED);
		error.setMessage("Unauthorized");
		ResponseEntity<Object> entity = new ResponseEntity<>(error, error.getStatus());

		return entity;
	}
	
	@GetMapping("/user")
	public UserDto getUserInfo(@RequestHeader(name = "token") String token, @RequestHeader(name = "userInfoEndpointUri") String userInfoEndpointUri) {
		
		return service.getInformation(token,userInfoEndpointUri);
	}
	
}
