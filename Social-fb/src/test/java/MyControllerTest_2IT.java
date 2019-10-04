//
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.omp.transport.referentiel.common.entity.BidonEntity;
//import com.omp.transport.referentiel.service.MyServiceBidon;
//
//@AutoConfigureMockMvc
//@ContextConfiguration(classes = { MyController.class })
//@WebMvcTest
//@Tag("Integration")
//@DisplayName("Test du Ws service /hello en passant par la couche Http")
//class MyControllerTest_2IT {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean // annotation permettant d'injecter un mock de bean
//	private MyServiceBidon serviceBidon;
//
//	@Test
//	@DisplayName("Test du Ws service /hello en passant par la couche Http")
//	void testBonjour() throws Exception {
//
//		// Prépare le retour de la methode mock
//		Optional<BidonEntity> be = Optional.of(new BidonEntity());
//		be.get().setLastname("testU");
//		when(serviceBidon.searchById(12)).thenReturn(be);
//
//		MvcResult result = mockMvc
//				.perform(MockMvcRequestBuilders.get("/test/bidon/12").contentType(MediaType.APPLICATION_JSON_UTF8)
//						.accept(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk()).andExpect(content().json("{\"nom\":\"testU\",\"prenom\":null,\"id\":0}"))
//				.andReturn();
//
//	}
//
//	@Test
//	@DisplayName("Test de la validation d'un objet lors d'un POST -> Ok")
//	void testPostBonjourOk() throws Exception {
//
//		MvcResult result = mockMvc
//				.perform(MockMvcRequestBuilders.post("/test/bidons")
//						.content("{\"nom\":\"testU\",\"prenom\":\"lol\",\"id\":0}")
//						.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk()).andReturn();
//
//	}
//
//	@Test
//	@DisplayName("Test de la validation d'un objet lors d'un POST -> flux Json non valide")
//	void testPostBonjourKoJsonNotValid() throws Exception {
//
//		MvcResult result = mockMvc
//				.perform(MockMvcRequestBuilders.post("/test/bidons")
//						.content("{\"nom\":\"testU\",\"prenom\":\"lol\",\"id\":0")
//						.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isBadRequest()).andReturn();
//
//	}
//
//	@Test
//	@DisplayName("Test de la validation d'un objet lors d'un POST -> L'un des critères n'est pas respecté")
//	void testPostBonjourKoNotValid() throws Exception {
//
//		MvcResult result = mockMvc
//				.perform(MockMvcRequestBuilders.post("/test/bidons")
//						.content("{\"nom\":\"t\",\"prenom\":\"lol\",\"id\":0}")
//						.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isBadRequest()).andReturn();
//	}
//
//}
