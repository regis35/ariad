package bzh.ariad.social.facebook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import bzh.ariad.common.SocialUserDto;
import bzh.ariad.common.UserIdDto;
import bzh.ariad.social.facebook.exception.UserComposantException;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Value("${ariad.service.checkerid.url.create.user}")
	private String createUserUrl;

	@Autowired
	private RestTemplate restTemplate;

	private static String ERR_001 = "Error when calling ws %s http status is %s.";

	@HystrixCommand(fallbackMethod = "createUserfallback")
	public UserIdDto createUser(SocialUserDto socialUser) throws UserComposantException {
		// TODO utiliser le composant registry pour résoudre le nom des composants
		ResponseEntity<UserIdDto> response = restTemplate.postForEntity(createUserUrl, socialUser, UserIdDto.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			// TODO externaliser les messages
			String msg = String.format(ERR_001, createUserUrl, response.getStatusCode());
			LOGGER.error(msg);
			// TODO remonter un autre type d'erreur
			throw new RuntimeException(msg);
		}
		return response.getBody();
	}

	public UserIdDto createUserfallback(SocialUserDto socialUser) throws UserComposantException {

		LOGGER.error("Error when calling the user creation service");

		// TODO Ici on pourrait stocker les informations reçu, et les rejouer lorsque le
		// service serait à nouveau disponible

		throw new UserComposantException("Service 'CheckerId' Unavailable");
	}
}
