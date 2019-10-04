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
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import bzh.ariad.social.facebook.ws.FacebookController;

@Service
public class FacebookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookService.class);
	
	@Autowired
	private OAuth2AuthorizedClientService authorizedService;
	
	public void getInformation(OAuth2AuthenticationToken authentication) {

		String registrationId = authentication.getAuthorizedClientRegistrationId();
		String principalName = authentication.getName();
		
		LOGGER.debug("Registration Id : " + registrationId);
		LOGGER.debug("Principal name : " + principalName);
		
		OAuth2AuthorizedClient client = authorizedService.loadAuthorizedClient(registrationId, principalName);

		String userInfoEndpointUri = client.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();

		OAuth2AccessToken accessToken = client.getAccessToken();

		LOGGER.debug("access Token : " + accessToken.getTokenValue());

		Instant tokenDuration = accessToken.getExpiresAt();

		Duration reste = Duration.between(Instant.now(), tokenDuration);

		LOGGER.debug("Durée de vie du token : " + reste.getSeconds());

		String name = "";
		if (!StringUtils.isEmpty(userInfoEndpointUri)) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken.getTokenValue());
			HttpEntity entity = new HttpEntity("", headers);
			ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity,
					Map.class);

			Map userAttributes = response.getBody();
			name = (String) userAttributes.get("name");
//			model.addAttribute("name", name);
		}

	}

}
