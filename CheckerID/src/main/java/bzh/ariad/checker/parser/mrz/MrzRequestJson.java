package bzh.ariad.checker.parser.mrz;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Mrz Request Json
 * @author Regis Le Coz
 */
@JsonInclude(Include.NON_NULL)
public class MrzRequestJson {

	private String line1;
	private String line2;
	private String line3;
	/**
	 * @return the line1
	 */
	public final String getLine1() {
		return line1;
	}
	/**
	 * @param line1 the line1 to set
	 */
	public final void setLine1(String line1) {
		this.line1 = line1;
	}
	/**
	 * @return the line2
	 */
	public final String getLine2() {
		return line2;
	}
	/**
	 * @param line2 the line2 to set
	 */
	public final void setLine2(String line2) {
		this.line2 = line2;
	}
	/**
	 * @return the line3
	 */
	public final String getLine3() {
		return line3;
	}
	/**
	 * @param line3 the line3 to set
	 */
	public final void setLine3(String line3) {
		this.line3 = line3;
	}
	
}
