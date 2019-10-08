package bzh.ariad.social.facebook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bzh.ariad.social.facebook.entity.TokenEntity;

@Repository
public interface TokenRepository extends CrudRepository<TokenEntity, String> {

	TokenEntity findByAccessToken(String accessToken);
	
}
