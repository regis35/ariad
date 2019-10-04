package bzh.ariad.checker.parser;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Regis Le Coz
 */
public class MrzJson {

	/**
	 * @return the holderDetail
	 */
	public final HolderDetailJson getHolderDetail() {
		return holderDetail;
	}

	/**
	 * @param holderDetail the holderDetail to set
	 */
	public final void setHolderDetail(HolderDetailJson holderDetail) {
		this.holderDetail = holderDetail;
	}

	private String uid;
	private String analysisRefUid;

	private List<ControlsJson> controls;
	
	private HolderDetailJson holderDetail;

	/**
	 * @return the uid
	 */
	public final String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public final void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the analysisRefUid
	 */
	public final String getAnalysisRefUid() {
		return analysisRefUid;
	}

	/**
	 * @param analysisRefUid the analysisRefUid to set
	 */
	public final void setAnalysisRefUid(String analysisRefUid) {
		this.analysisRefUid = analysisRefUid;
	}

	/**
	 * @return the controls
	 */
	public final List<ControlsJson> getControls() {
		return controls;
	}

	/**
	 * @param controls the controls to set
	 */
	public final void setControls(List<ControlsJson> controls) {
		this.controls = controls;
	}
}
