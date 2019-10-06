package bzh.ariad.common;

import java.util.Objects;

/**
 * Token dto
 * 
 * @author regislecoz
 */
public class TokenDto {

	private String token;
	private String type;
	private long ttl; // second

	public TokenDto() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getTtl() {
		return ttl;
	}

	public void setTtl(long ttl) {
		this.ttl = ttl;
	}

	@Override
	public String toString() {
		return "TokenDto [token=" + token + ", type=" + type + ", ttl=" + ttl + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(token, ttl, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TokenDto)) {
			return false;
		}
		TokenDto other = (TokenDto) obj;
		return Objects.equals(token, other.token) && ttl == other.ttl && Objects.equals(type, other.type);
	}

}
