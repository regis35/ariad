package bzh.ariad.social.facebook.redis;

import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import bzh.ariad.social.facebook.entity.FacebookToken;
import bzh.ariad.social.facebook.repository.FacebookTokenRepository;

@ExtendWith(SpringExtension.class)
@DataRedisTest
@Testcontainers
public class RedisTest extends AbstractRedisTest {

	@Autowired
	FacebookTokenRepository repository;

	@BeforeEach
	public void before() {
		repository.deleteAll();
		System.out.println(repository.count());
	}

	@Test
	@DisplayName("Check redis ttl of object")
	public void testTtl() throws Exception {
		FacebookToken fb = new FacebookToken();
		fb.setAccessToken("accessnew");
		fb.setRefreshToken("refersh");
		fb.setIdClient("ttltest2");
		fb.setTimeToLive(5);

		repository.save(fb);

		TimeUnit.SECONDS.sleep(8);

		Optional<FacebookToken> res = repository.findById("ttltest2");
		assertTrue(res.isEmpty());
	}

	@Test
	public void testTtl2() throws Exception {
		System.out.println(repository.count());
	}

}
