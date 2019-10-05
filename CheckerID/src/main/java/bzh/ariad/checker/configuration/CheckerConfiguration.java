package bzh.ariad.checker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CheckerConfiguration {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
