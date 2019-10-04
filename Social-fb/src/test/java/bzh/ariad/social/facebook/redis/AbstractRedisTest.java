package bzh.ariad.social.facebook.redis;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class AbstractRedisTest {

	static {
		GenericContainer redis = new GenericContainer("redis:latest").withExposedPorts(6379)
				.waitingFor(Wait.forLogMessage(".*Ready to accept connections.*\\n", 1));
        redis.start();

        System.setProperty("spring.redis.host", redis.getContainerIpAddress());
        System.setProperty("spring.redis.port", redis.getFirstMappedPort() + "");
    }
	
}
