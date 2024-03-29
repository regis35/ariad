package bzh.ariad.checker.ws;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bzh.ariad.checker.exception.NotValidCardIdException;
import bzh.ariad.checker.service.CheckUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Checker ID card controller
 * @author Regis Le Coz
 */
@RestController
@RequestMapping("verify")
@Api("This service valid user identity")
public class CheckerIdCardController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheckerIdCardController.class);
	
	@Autowired
	private CheckUserService service;
	
	@GetMapping(value="/{userId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "verify user information")
	@ApiResponses (value = {
			@ApiResponse(code = 406 , message = "The ID card information is not correct")
	})
	public boolean getInformation(@Valid @PathVariable("userId") @NotBlank String userId, @RequestParam(name = "line1",required = true) @NotBlank String line1, @RequestParam(name = "line2",required = false) String line2, @RequestParam(name = "line3",required = false) String line3 ) throws NotValidCardIdException{
		
		List<String> cardIdLines = new ArrayList<>(3);
		if (!StringUtils.isEmpty(line1)) {
			LOGGER.debug("line 1->" + line1 );
			cardIdLines.add(line1);
		}
		if (!StringUtils.isEmpty(line2)) {
			LOGGER.debug("line 2->" + line2 );
			cardIdLines.add(line2);
		}
		if (!StringUtils.isEmpty(line3)) {
			LOGGER.debug("line 3->" + line3 );
			cardIdLines.add(line3);
		}
		
		return service.isValid(userId, cardIdLines);
	}
	
}
