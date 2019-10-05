package bzh.ariad.social.facebook.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author Regis Le Coz
 *
 */
@RedisHash("FB_token")
public class FacebookToken implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1273124649318547473L;

	@Id
	private String idClient;

	@Indexed
	private String accessToken;

	private String refreshToken;

	@TimeToLive
	private long TimeToLive;

	/**
	 * @return the idClient
	 */
	public final String getIdClient() {
		return idClient;
	}

	/**
	 * @param idClient the idClient to set
	 */
	public final void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	/**
	 * @return the accessToken
	 */
	public final String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public final void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the timeToLive
	 */
	public final long getTimeToLive() {
		return TimeToLive;
	}

	/**
	 * @param timeToLive the timeToLive to set
	 */
	public final void setTimeToLive(long timeToLive) {
		TimeToLive = timeToLive;
	}

	/**
	 * @return the refreshToken
	 */
	public final String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public final void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		return "FacebookToken [idClient=" + idClient + ", accessToken=" + accessToken + ", refreshToken=" + refreshToken
				+ "]";
	}

}
