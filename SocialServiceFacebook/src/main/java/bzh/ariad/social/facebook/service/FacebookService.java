package bzh.ariad.social.facebook.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bzh.ariad.common.SocialUserDto;
import bzh.ariad.common.UserDto;
import bzh.ariad.common.UserIdDto;
import bzh.ariad.social.facebook.exception.UserComposantException;

@Service
public class FacebookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookService.class);
	
	@Autowired
	private OAuth2AuthorizedClientService authorizedService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * This service sends the authentication information to the checkerID component
	 * @return UserId
	 */
	public UserIdDto createUser(OAuth2AuthenticationToken authentication) throws UserComposantException {
		
		LOGGER.debug("[Start] senUserInfoToCheckerID(");

		// Init social user dto
		String registrationId = authentication.getAuthorizedClientRegistrationId();
		String principalName = authentication.getName();
		
		OAuth2AuthorizedClient client = authorizedService.loadAuthorizedClient(registrationId, principalName);
		String userInfoEndpointUri = client.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();
		
		// TODO créer un Factory via Spring
		SocialUserDto socialUser = new SocialUserDto();
		socialUser.setId(principalName);
		socialUser.setProvider(registrationId);
		socialUser.setUserInfoEndpoint(userInfoEndpointUri);
		// Init token dto
		OAuth2AccessToken accessToken = client.getAccessToken();
		socialUser.getToken().setToken(accessToken.getTokenValue());
		socialUser.getToken().setType(accessToken.getTokenType().getValue());
		
		// Calculate the duration of the token in seconds 
		Instant tokenDuration = accessToken.getExpiresAt();
		Duration reste = Duration.between(Instant.now(), tokenDuration);
		socialUser.getToken().setTtl(reste.getSeconds());

		LOGGER.debug("Object creates : " + socialUser);
		
		return tokenService.createUser(socialUser);
	}
	
	@SuppressWarnings("rawtypes")
	public UserDto getInformation(String token, String userInfoEndpointUri) {

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>("param", headers);
			ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity,
					Map.class);

			Map userAttributes = response.getBody();
			
			UserDto user = new UserDto();
			user.setEmail((String) userAttributes.get("email"));
			user.setId((String) userAttributes.get("id"));
			user.setLastname((String) userAttributes.get("lastname"));
			user.setName((String) userAttributes.get("name"));
			user.setBirthdate((String) userAttributes.get("birthdate"));
			
			LOGGER.debug("Object creates : " + user);
			
			return user;
	}

}
