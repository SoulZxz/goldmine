package com.goldmine.webstat.viewer.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ActionContext {

	@Column(name = "referrer")
	protected String referrer;

	@Column(name = "referrer_name")
	protected String referrerName;

	@Column(name = "app_id")
	protected String appId;

	@Column(name = "campaign")
	protected String campaign;

	@Column(name = "ad_tag")
	protected String adTag;

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getReferrerName() {
		return referrerName;
	}

	public void setReferrerName(String referrerName) {
		this.referrerName = referrerName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getAdTag() {
		return adTag;
	}

	public void setAdTag(String adTag) {
		this.adTag = adTag;
	}

}
