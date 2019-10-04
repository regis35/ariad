package bzh.ariad.checker.parser;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Mrz json parser
 * @author Regis Le Coz
 */
@Component
public class MrzParser {

	public MrzJson parse(String json) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		MrzJson mzr = objectMapper.readValue(json, MrzJson.class);  
		
		return mzr;
	}
	
}
