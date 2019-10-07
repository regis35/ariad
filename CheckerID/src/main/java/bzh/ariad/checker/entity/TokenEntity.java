package bzh.ariad.checker.entity;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author Regis Le Coz
 */
@RedisHash("token")
public class TokenEntity implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1273124649318547473L;

	@Id
	private String id;

	@Indexed
	private String idProvider;

	private String provider;

	private String userInfoEndpoint;

	@Indexed
	private String token;

	private String tokenType;

	@TimeToLive
	private long TimeToLive;

	public TokenEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getUserInfoEndpoint() {
		return userInfoEndpoint;
	}

	public void setUserInfoEndpoint(String userInfoEndpoint) {
		this.userInfoEndpoint = userInfoEndpoint;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public long getTimeToLive() {
		return TimeToLive;
	}

	public void setTimeToLive(long timeToLive) {
		TimeToLive = timeToLive;
	}

	public String getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(String idProvider) {
		this.idProvider = idProvider;
	}

	@Override
	public int hashCode() {
		return Objects.hash(TimeToLive, id, idProvider, provider, token, tokenType, userInfoEndpoint);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TokenEntity)) {
			return false;
		}
		TokenEntity other = (TokenEntity) obj;
		return TimeToLive == other.TimeToLive && Objects.equals(id, other.id)
				&& Objects.equals(idProvider, other.idProvider) && Objects.equals(provider, other.provider)
				&& Objects.equals(token, other.token) && Objects.equals(tokenType, other.tokenType)
				&& Objects.equals(userInfoEndpoint, other.userInfoEndpoint);
	}

	@Override
	public String toString() {
		return "TokenEntity [id=" + id + ", idProvider=" + idProvider + ", provider=" + provider + ", userInfoEndpoint="
				+ userInfoEndpoint + ", token=" + token + ", tokenType=" + tokenType + ", TimeToLive=" + TimeToLive
				+ "]";
	}

}
