package bzh.ariad.social.facebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class FbApplication {

	public static void main(String[] args) {
		SpringApplication.run(FbApplication.class, args);
	}
}
