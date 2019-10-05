package bzh.ariad.checker.exception;

/**
 * Exception throw when cardId is not valid
 * @author Regis Le Coz
 */
public class NotValidCardIdException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3069169461198830698L;

	public NotValidCardIdException(String message) {
		super(message);
	}
}
