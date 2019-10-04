//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.omp.transport.referentiel.common.entity.BidonEntity;
//import com.omp.transport.referentiel.dto.ObjetBidonDTO;
//import com.omp.transport.referentiel.mapper.ObjetBidonMapperImpl;
//import com.omp.transport.referentiel.service.MyServiceBidon;
//
//@ExtendWith(SpringExtension.class)
//@Tag("Integration")
//@ContextConfiguration(classes = { ObjetBidonMapperImpl.class })
//@DisplayName("Exemple de testU avec un @ExtendWith différent, en passant par la couche Http")
//class MyControllerTestIT {
//
//	@Mock
//	private MyServiceBidon serviceBidon;
//
//	@InjectMocks
//	private MyController controller;
//
//	@BeforeEach
//	void setMockOutput() {
//		Optional<BidonEntity> be = Optional.of(new BidonEntity());
//		be.get().setLastname("testU");
//		when(serviceBidon.searchById(12)).thenReturn(be);
//	}
//
//	@Test
//	@DisplayName("Test de la methode bonjour avec mockito")
//	void testBonjour() throws Exception {
//		// On ne passe pas par la couche Http avec ce test
//		ObjetBidonDTO ob = controller.getBidon(12);
//		assertNotNull(ob);
//		assertEquals("testU", ob.getNom());
//	}
//
//}
