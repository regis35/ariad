//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
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
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.omp.transport.referentiel.common.entity.BidonEntity;
//import com.omp.transport.referentiel.dto.ObjetBidonDTO;
//import com.omp.transport.referentiel.service.MyServiceBidon;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("Exemple de testU avec Mockito, en passant par la couche Http")
//@Tag("Unitaire")
//class MyControllerTest {
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
//	void testBonjour() throws Exception{
//		ObjetBidonDTO ob = controller.getBidon(12);
//		assertNotNull(ob);
//		assertEquals("testU", ob.getNom());
//	}
//}
