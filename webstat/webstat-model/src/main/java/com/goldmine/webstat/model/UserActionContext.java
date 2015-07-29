package com.goldmine.webstat.model;

import java.util.Date;

/**
 * 用户行为背景，包括时间，地点（ui），用户标识
 * 
 * @author zhaoxuanzhang
 * 
 */
public class UserActionContext {

	private UserIdType userIdType;

	private String userId;

	private String device;

	private String userAgent;

	private String referrer;

	private String referrerName;

	private String ip;

	private Date timestamp;

	private String appId;

	private String campaign;

	private String adTag;

	public UserIdType getUserIdType() {
		return userIdType;
	}

	public void setUserIdType(UserIdType userIdType) {
		this.userIdType = userIdType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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

	@Override
	public String toString() {
		return "UserActionContext [userIdType=" + userIdType + ", userId=" + userId + ", device="
				+ device + ", userAgent=" + userAgent + ", referrer=" + referrer + ", ip=" + ip
				+ ", timestamp=" + timestamp + ", campaign=" + campaign + ", adTag=" + adTag + "]";
	}

}
