package bzh.ariad.social.facebook.error;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;
	private String message;
	private String debugMessage;

	private ApiError() {
	}

	public ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	public ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	public ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}
}
