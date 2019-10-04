package bzh.ariad.checker.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;

/**
 * Card ID information Dto
 * 
 * @author Regis Le Coz
 */
public class CardIdInformation {

	@ApiModelProperty(notes = "user id")
	private String userId;
	@ApiModelProperty(notes = "last name")
	private String lastName;
	@ApiModelProperty(notes = "first name")
	private String firstName;
	@ApiModelProperty(notes = "gender")
	private GenderEnum gender;
	@ApiModelProperty(notes = "birth date")
	private LocalDate birthDate;

	/**
	 * @return the userId
	 */
	public final String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public final void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the lastName
	 */
	public final String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the gender
	 */
	public final GenderEnum getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public final void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthDate
	 */
	public final LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public final void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "CardIdInformation [userId=" + userId + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", gender=" + gender + ", birthDate=" + birthDate + "]";
	}
}
