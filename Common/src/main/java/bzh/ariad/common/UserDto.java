package bzh.ariad.common;

import java.util.Objects;

public class UserDto {
	private String id;
	private String email;
	private String name;
	private String lastname;
	private String birthdate;

	public UserDto() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthdate, email, id, lastname, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserDto)) {
			return false;
		}
		UserDto other = (UserDto) obj;
		return Objects.equals(birthdate, other.birthdate) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", email=" + email + ", name=" + name + ", lastname=" + lastname + ", birthdate="
				+ birthdate + "]";
	}

}
