package bzh.ariad.checker;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import bzh.ariad.checker.dto.CardIdInformation;
import bzh.ariad.checker.dto.GenderEnum;
import bzh.ariad.checker.exception.NotValidCardIdException;
import bzh.ariad.checker.service.CheckerCardIdServiceImpl;
import bzh.ariad.checker.ws.CheckerIdCardController;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = { CheckerIdCardController.class })
@Tag("Integration")
@DisplayName("Integration test of carId service")
class CheckerIdCardControllerTestIT {

	@MockBean
	private CheckerCardIdServiceImpl checkerCardIdService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setMockOutput() {
	}

	@Test
	@DisplayName("Test 406 error")
	void testGetInformation406() throws Exception {
		try {
			String userId = "kiki";
			String line1 = "line1";
			String line2 = "line2";

			when(checkerCardIdService.getInformation(any(), any())).thenThrow(NotValidCardIdException.class);

			mockMvc.perform(get("/checker/cardId/{userId}", userId).param("line1", line1)
					.param("line2", line2).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andReturn();
		} catch (Exception e) {
			if (e.getCause() instanceof NotValidCardIdException == false )
				fail("The exception 'NotValidCardIdException' must be throwed");
		}
	}

	@Test
	@DisplayName("Nominal case")
	void testGetInformationOk() throws Exception {
		
		String userId = "kiki";
		String line1 = "line1";
		String line2 = "line2";

		CardIdInformation cii = new CardIdInformation();
		cii.setUserId(userId);
		cii.setBirthDate(LocalDate.of(1978, 8, 19));
		cii.setFirstName("Regis");
		cii.setLastName("Le Coz");
		when(checkerCardIdService.getInformation(eq("kiki"), anyList())).thenReturn(cii);

		mockMvc.perform(get("/checker/cardId/{userId}", userId).param("line1", line1)
				.param("line2", line2).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(content().string("{\"userId\":\"kiki\",\"lastName\":\"Le Coz\",\"firstName\":\"Regis\",\"gender\":null,\"birthDate\":\"1978-08-19\"}"))
				.andReturn();
	}
	
	@Test
	@DisplayName("Bad parameter")
	void testBadParameter() throws Exception {
		
		String userId = "kiki";

		CardIdInformation cii = new CardIdInformation();
		cii.setUserId(userId);
		cii.setBirthDate(LocalDate.of(1978, 8, 19));
		cii.setFirstName("Regis");
		cii.setLastName("Le Coz");
		when(checkerCardIdService.getInformation(eq("kiki"), anyList())).thenReturn(cii);

		mockMvc.perform(get("/checker/cardId/{userId}", userId).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isBadRequest())
				.andExpect(status().reason("Required String parameter 'line1' is not present"))
				.andReturn();
	}

	@Test
	@DisplayName("Nominal case, all line")
	void testGetInformationOkAllLine() throws Exception {
		
		String userId = "kiki";
		String line1 = "line1";
		String line2 = "line2";
		String line3 = "line3";
		
		CardIdInformation cii = new CardIdInformation();
		cii.setUserId(userId);
		cii.setBirthDate(LocalDate.of(1978, 8, 19));
		cii.setFirstName("Regis");
		cii.setLastName("Le Coz");
		cii.setGender(GenderEnum.F);
		when(checkerCardIdService.getInformation(eq("kiki"), anyList())).thenReturn(cii);

		mockMvc.perform(get("/checker/cardId/{userId}", userId).param("line1", line1)
				.param("line2", line2).param("line3", line3).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(content().string("{\"userId\":\"kiki\",\"lastName\":\"Le Coz\",\"firstName\":\"Regis\",\"gender\":\"F\",\"birthDate\":\"1978-08-19\"}"))
				.andReturn();
	}
}
