package bzh.ariad.checker.parser;

import java.util.List;

/**
 * @author Regis Le Coz
 */
public class HolderDetailJson {
	
	private List<String> lastName;
	private List<String> firstName;
	private String gender;
	private BirthDateJson birthDate;
	/**
	 * @return the lastName
	 */
	public final List<String> getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public final void setLastName(List<String> lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the firstName
	 */
	public final List<String> getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public final void setFirstName(List<String> firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the gender
	 */
	public final String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public final void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the birthDate
	 */
	public final BirthDateJson getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public final void setBirthDate(BirthDateJson birthDate) {
		this.birthDate = birthDate;
	}
}
