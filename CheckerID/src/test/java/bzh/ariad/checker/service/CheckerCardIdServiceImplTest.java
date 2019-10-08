package bzh.ariad.checker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import bzh.ariad.checker.dto.CardIdInformation;
import bzh.ariad.checker.dto.GenderEnum;
import bzh.ariad.checker.parser.mrz.BirthDateJson;
import bzh.ariad.checker.parser.mrz.ControlsJson;
import bzh.ariad.checker.parser.mrz.HolderDetailJson;
import bzh.ariad.checker.parser.mrz.MrzResponseJson;
import bzh.ariad.checker.parser.mrz.MrzResponseParser;

@ExtendWith(MockitoExtension.class)
class CheckIOServiceImplTest {

	@Mock
	private MrzResponseParser parser;
	
	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private CheckIOServiceImpl service;

	private String loadJson(String file) {
		String json = "";

		ClassPathResource cpr = new ClassPathResource(file);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try (InputStream in = new BufferedInputStream(new FileInputStream(cpr.getFile()))) {

			IOUtils.copy(in, out);

			json = new String(out.toByteArray(), "UTF-8");

			IOUtils.closeQuietly(out);

		} catch (Exception e) {
			fail(e.getMessage());
		}
		return json;
	}

//	@Test
	void testGetInformationOk() throws Exception {
		String body = loadJson("docVal.json");
		ResponseEntity<String> res = new ResponseEntity<>(body, HttpStatus.OK);
		when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<Class<String>>any())).thenReturn(res);

		MrzResponseJson mrzJson = new MrzResponseJson();
		List<ControlsJson> controls = new ArrayList<>();
		mrzJson.setControls(controls);

		ControlsJson c1 = new ControlsJson();
		c1.setResult("OK");
		controls.add(c1);

		ControlsJson c2 = new ControlsJson();
		c2.setResult("OK");
		controls.add(c2);
		
		BirthDateJson birthdate = new BirthDateJson();
		birthdate.setDay(10);
		birthdate.setMonth(10);
		birthdate.setYear(2010);

		HolderDetailJson detail = new HolderDetailJson();
		detail.setFirstName(Arrays.asList("REGIS", "SECOND"));
		detail.setGender("M");
		detail.setLastName(Arrays.asList("LE", "COZ"));
		detail.setBirthDate(birthdate);
		mrzJson.setHolderDetail(detail);
		
		when(parser.parse(ArgumentMatchers.any())).thenReturn(mrzJson);
		
		List<String> lines = Arrays.asList("l1", "l2");
//		service.getInformation("regis", lines);

	}

	@Test
	void testBuildBody() {

		List<String> lines = Arrays.asList("l1", "l2");
		CheckIOServiceImpl service = new CheckIOServiceImpl();

		String body = service.buildBody(lines);
		assertEquals("{\"line1\":\"l1\",\"line2\":\"l2\"}", body);
	}

	@Test
	void testBuildBodyMoreElement() {

		List<String> lines = Arrays.asList("l1", "l2", "l3", "l4");
		CheckIOServiceImpl service = new CheckIOServiceImpl();

		String body = service.buildBody(lines);
		assertEquals("{\"line1\":\"l1\",\"line2\":\"l2\",\"line3\":\"l3\"}", body);
	}

	@Test
	void testBuildBodyListEmpty() {

		List<String> lines = Arrays.asList();
		CheckIOServiceImpl service = new CheckIOServiceImpl();

		String body = service.buildBody(lines);
		assertEquals("{}", body);
	}

	@Test
	void testBuildCardIdInformation() {
		CheckIOServiceImpl service = new CheckIOServiceImpl();

		BirthDateJson birthdate = new BirthDateJson();
		birthdate.setDay(10);
		birthdate.setMonth(10);
		birthdate.setYear(2010);

		HolderDetailJson detail = new HolderDetailJson();
		detail.setFirstName(Arrays.asList("REGIS", "SECOND"));
		detail.setGender("M");
		detail.setLastName(Arrays.asList("LE", "COZ"));
		detail.setBirthDate(birthdate);

		CardIdInformation res = service.buildCardIdInformation("id12", detail);
		assertNotNull(res);
		assertEquals(res.getFirstName(), "REGIS SECOND");
		assertEquals(res.getLastName(), "LE COZ");
		assertEquals(res.getGender(), GenderEnum.M);
		assertTrue(res.getBirthDate().isEqual(LocalDate.of(2010, 10, 10)));
	}

	@Test
	void testBuildCardIdInformationBirddateIsNull() {
		CheckIOServiceImpl service = new CheckIOServiceImpl();

		HolderDetailJson detail = new HolderDetailJson();
		detail.setFirstName(Arrays.asList("REGIS", "SECOND"));
		detail.setGender("M");
		detail.setLastName(Arrays.asList("LE", "COZ"));

		CardIdInformation res = service.buildCardIdInformation("id12", detail);
		assertNotNull(res);
		assertEquals(res.getFirstName(), "REGIS SECOND");
		assertEquals(res.getLastName(), "LE COZ");
		assertEquals(res.getGender(), GenderEnum.M);
		assertNull(res.getBirthDate());
	}

	@Test
	void testIsValidCardId() {

		MrzResponseJson mrzJson = new MrzResponseJson();
		List<ControlsJson> controls = new ArrayList<>();
		mrzJson.setControls(controls);

		ControlsJson c1 = new ControlsJson();
		c1.setResult("OK");
		controls.add(c1);

		ControlsJson c2 = new ControlsJson();
		c2.setResult("OK");
		controls.add(c2);

		CheckIOServiceImpl service = new CheckIOServiceImpl();
		assertTrue(service.isValidCardId(mrzJson));

	}

	@Test
	void testIsValidCardIdOK_bis() {

		MrzResponseJson mrzJson = new MrzResponseJson();
		List<ControlsJson> controls = new ArrayList<>();
		mrzJson.setControls(controls);

		ControlsJson c1 = new ControlsJson();
		c1.setResult("OK");
		controls.add(c1);

		ControlsJson c2 = new ControlsJson();
		c2.setResult("None");
		controls.add(c2);

		CheckIOServiceImpl service = new CheckIOServiceImpl();
		assertTrue(service.isValidCardId(mrzJson));

	}
	
	@Test
	void testIsValidCardIdKo() {

		MrzResponseJson mrzJson = new MrzResponseJson();
		List<ControlsJson> controls = new ArrayList<>();
		mrzJson.setControls(controls);

		ControlsJson c1 = new ControlsJson();
		c1.setResult("OK");
		controls.add(c1);

		ControlsJson c2 = new ControlsJson();
		c2.setResult("None");
		controls.add(c2);
		
		ControlsJson c3 = new ControlsJson();
		c3.setResult("ERROR");
		controls.add(c3);

		CheckIOServiceImpl service = new CheckIOServiceImpl();
		assertFalse(service.isValidCardId(mrzJson));

	}

	@Test
	void testIsValidCardIdNoList() {

		MrzResponseJson mrzJson = new MrzResponseJson();

		CheckIOServiceImpl service = new CheckIOServiceImpl();
		assertFalse(service.isValidCardId(mrzJson));

	}

	@Test
	void testIsValidCardIdListEmpty() {

		MrzResponseJson mrzJson = new MrzResponseJson();
		List<ControlsJson> controls = new ArrayList<>();
		mrzJson.setControls(controls);

		CheckIOServiceImpl service = new CheckIOServiceImpl();
		assertFalse(service.isValidCardId(mrzJson));

	}

	@Test
	void testIsValidCardIdNull() {

		CheckIOServiceImpl service = new CheckIOServiceImpl();
		assertFalse(service.isValidCardId(null));

	}
}
