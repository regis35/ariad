package bzh.ariad.social.facebook.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bzh.ariad.common.UserDto;
import bzh.ariad.social.facebook.service.FacebookService;

@RestController
@RequestMapping("user")
public class FacebookController2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookController.class);

	@Autowired
	private FacebookService service;

	@GetMapping("/kiki")
	public UserDto getUserInfo(@RequestHeader(name = "token") String token, @RequestHeader(name = "userInfoEndpointUri") String userInfoEndpointUri) {
		
		return service.getInformation(token,userInfoEndpointUri);
	}
}
