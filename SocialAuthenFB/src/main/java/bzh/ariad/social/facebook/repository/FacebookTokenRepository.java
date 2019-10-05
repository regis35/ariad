package bzh.ariad.social.facebook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bzh.ariad.social.facebook.entity.FacebookToken;

@Repository
public interface FacebookTokenRepository extends CrudRepository<FacebookToken, String> {

	FacebookToken findByAccessToken(String accessToken);
	
}
