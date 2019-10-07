package bzh.ariad.checker.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bzh.ariad.checker.entity.TokenEntity;
import bzh.ariad.checker.repository.TokenRepository;
import bzh.ariad.common.SocialUserDto;
import bzh.ariad.common.UserIdDto;

@ExtendWith(MockitoExtension.class)
@DisplayName("Exemple de testU avec Mockito, en passant par la couche Http")
@Tag("Unitaire")
class TokenServiceImplTest {

	@Mock
	private TokenRepository repository;
	
	@InjectMocks
	private TokenService service = new TokenServiceImpl();
	
	@Test
	@DisplayName("Create user test unitaire")
	void testCreateUser() {
		TokenEntity tokenE = new TokenEntity();
		tokenE.setId(UUID.randomUUID().toString());
		when(repository.save(any())).thenReturn(tokenE);
		
		SocialUserDto socialDto = new SocialUserDto();
		
		UserIdDto result = service.createUser(socialDto);
		
		verify(repository, times(1)).save(any());
		
		assertNotNull(result);
		assertNotNull(result.getId());
	}
	
	@Test
	@DisplayName("list users test unitaire")
	void testListUsers() {
		List<TokenEntity> tokensList = new ArrayList<TokenEntity>();
		
		
		TokenEntity tokenE = new TokenEntity();
		tokenE.setId(UUID.randomUUID().toString());
		tokensList.add(tokenE);
		
		when(repository.findAll()).thenReturn(tokensList);
		
		List<TokenEntity> tokens = service.list();
		
		verify(repository, times(1)).findAll();
		
		assertNotNull(tokens);
		assertEquals(1, tokensList.size());
	}

}
