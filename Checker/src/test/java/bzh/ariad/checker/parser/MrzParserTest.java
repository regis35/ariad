package bzh.ariad.checker.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Regis Le Coz
 */
class MrzParserTest {

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

	@SuppressWarnings("deprecation")
	@Test
	void testParse() throws Exception {

		String json = loadJson("docVal.json");
		
		MrzParser parser = new MrzParser();
		MrzJson mrz = parser.parse(json);
		assertNotNull(mrz);
		
		assertEquals("4968174368",mrz.getUid() );
		assertEquals("804172", mrz.getAnalysisRefUid());
		
		assertNotNull(mrz.getControls());
		assertEquals(5,mrz.getControls().size());
		
		HolderDetailJson hdj = mrz.getHolderDetail();
		assertNotNull(hdj);
		assertEquals("M",hdj.getGender() );
		assertEquals(1,hdj.getFirstName().size() );
		assertEquals(2,hdj.getLastName().size() );
		
		BirthDateJson bdj = hdj.getBirthDate();
		assertNotNull(bdj);
		assertEquals(19,bdj.getDay());
		assertEquals(8,bdj.getMonth());
		assertEquals(1978,bdj.getYear());
	}

}
