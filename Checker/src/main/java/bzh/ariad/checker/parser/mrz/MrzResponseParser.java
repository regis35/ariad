package bzh.ariad.checker.parser.mrz;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Mrz json parser
 * @author Regis Le Coz
 */
@Component
public class MrzResponseParser {

	public MrzResponseJson parse(String json) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		MrzResponseJson mzr = objectMapper.readValue(json, MrzResponseJson.class);  
		
		return mzr;
	}
	
}
