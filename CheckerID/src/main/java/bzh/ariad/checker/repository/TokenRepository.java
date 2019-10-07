package bzh.ariad.checker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bzh.ariad.checker.entity.TokenEntity;

@Repository
public interface TokenRepository extends CrudRepository<TokenEntity, String> {

	TokenEntity findByAccessToken(String accessToken);
	
}
