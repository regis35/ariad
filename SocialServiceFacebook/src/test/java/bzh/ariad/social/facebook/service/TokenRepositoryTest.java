package bzh.ariad.social.facebook.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import bzh.ariad.social.facebook.entity.TokenEntity;
import bzh.ariad.social.facebook.repository.TokenRepository;

@ExtendWith(SpringExtension.class)
@DataRedisTest
@Testcontainers
@DisplayName("Redis test unitaire")
class TokenRepositoryTest extends AbstractRedisTest {

	@Autowired
	private TokenRepository repository;

	@BeforeEach
	public void before() {
		repository.deleteAll();
		assertEquals(0,repository.count());
	}

	@Test
	@DisplayName("Check redis ttl of object")
	public void testTtl() throws Exception {
		
		TokenEntity t = new TokenEntity();
		t.setId(UUID.randomUUID().toString());
		t.setIdProvider("12");
		t.setTimeToLive(5);
		t.setToken("myTok");

		repository.save(t);
		
		assertEquals(1,repository.count());
		
		TimeUnit.SECONDS.sleep(8);

		Optional<TokenEntity> res = repository.findById(t.getId());
		assertTrue(res.isEmpty());
	}

}
