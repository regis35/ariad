package bzh.ariad.common;

import java.util.Objects;

/**
 * Social user dto
 * 
 * @author regislecoz
 */
public class SocialUserDto {

	private String provider;
	private String id;
	private String userInfoEndpoint;
	private TokenDto token = new TokenDto();

	public SocialUserDto() {
		super();
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TokenDto getToken() {
		return token;
	}

	public String getUserInfoEndpoint() {
		return userInfoEndpoint;
	}

	public void setUserInfoEndpoint(String userInfoEndpoint) {
		this.userInfoEndpoint = userInfoEndpoint;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, provider, token, userInfoEndpoint);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SocialUserDto)) {
			return false;
		}
		SocialUserDto other = (SocialUserDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(provider, other.provider)
				&& Objects.equals(token, other.token) && Objects.equals(userInfoEndpoint, other.userInfoEndpoint);
	}

	@Override
	public String toString() {
		return "SocialUserDto [provider=" + provider + ", id=" + id + ", userInfoEndpoint=" + userInfoEndpoint
				+ ", token=" + token + "]";
	}
}
