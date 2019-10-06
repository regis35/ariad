package bzh.ariad.common;

import java.util.Objects;

/**
 * User id dto
 * 
 * @author regislecoz
 */
public class UserIdDto {

	private String id;

	public UserIdDto() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserIdDto [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserIdDto)) {
			return false;
		}
		UserIdDto other = (UserIdDto) obj;
		return Objects.equals(id, other.id);
	}
}
