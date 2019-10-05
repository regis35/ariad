package bzh.ariad.checker.parser.mrz;

/**
 * @author Regis Le Coz
 *
 */
public class ControlsJson {
	
	private String identifier;
	private String result;
	private String titleMsg;
	private String resultMsg;
	
	/**
	 * @return the titleMsg
	 */
	public final String getTitleMsg() {
		return titleMsg;
	}
	/**
	 * @param titleMsg the titleMsg to set
	 */
	public final void setTitleMsg(String titleMsg) {
		this.titleMsg = titleMsg;
	}
	/**
	 * @return the resultMsg
	 */
	public final String getResultMsg() {
		return resultMsg;
	}
	/**
	 * @param resultMsg the resultMsg to set
	 */
	public final void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	/**
	 * @return the identifier
	 */
	public final String getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier the identifier to set
	 */
	public final void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	/**
	 * @return the result
	 */
	public final String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public final void setResult(String result) {
		this.result = result;
	}

}
