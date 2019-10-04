package bzh.ariad.social.facebook.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import bzh.ariad.social.facebook.error.ApiError;

@RestController
public class FacebookController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookController.class);

	

	@SuppressWarnings("unchecked")
	@GetMapping("/login/success")
	public String login(Model model, OAuth2AuthenticationToken authentication) {

		
//		return "Hello " + name;
		return "Hello ";
	}

	@GetMapping("/login/failure")
	public ResponseEntity<Object> loginFailure(Model model, OAuth2AuthenticationToken authentication) {

		LOGGER.error("Login failure");
		
		ApiError error = new ApiError(HttpStatus.UNAUTHORIZED);
		error.setMessage("Unauthorized");
		ResponseEntity<Object> entity = new ResponseEntity<>(error, error.getStatus());
		
		return entity;
	}
	
	@GetMapping("/user/{id}/info")
	public void getMoreInfo() {
		System.out.println("getMoreInfo");
	}
	
	@GetMapping("/login")
	public String loginss(@RequestHeader("userId") String id) {
		System.out.println("login "+id);
		return "/login";
	}
}
