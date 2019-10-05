package bzh.ariad.social.facebook.ws;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controleur permettant d'attraper les erreurs de spring pour les rediriger
 * vers la class RestExceptionHandler
 * 
 * @author Regis Le Coz
 */
@Controller
public class CustomErrorController implements ErrorController {

	private static final String REQUEST_URI = "javax.servlet.forward.request_uri";

	@RequestMapping("error")
	public void handlerError(HttpServletRequest request) throws RuntimeException {
		String requestUri = (String) request.getAttribute(REQUEST_URI);
		RuntimeException exception = new RuntimeException("error");
		if (!StringUtils.isEmpty(requestUri)) {
			exception.addSuppressed(new Throwable("Unknown path : " + requestUri));
		}
		throw exception;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
