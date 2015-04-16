package com.goldmine.webstat.provider.impl.domain;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.goldmine.webstat.model.UserIdType;
import com.mongodb.BasicDBObject;

public class MongoUserActionContext extends BasicDBObject {

	private static final long serialVersionUID = -5221773925254951866L;

	public UserIdType getUserIdType() {
		String userIdType = this.getString("userIdType");
		return StringUtils.isBlank(userIdType) ? null : UserIdType.valueOf(userIdType);
	}

	public void setUserIdType(UserIdType userIdType) {
		this.put("userIdType", userIdType.name());
	}

	public String getUserId() {
		return this.getString("userId");
	}

	public void setUserId(String userId) {
		this.put("userId", userId);
	}

	public String getDevice() {
		return this.getString("device");
	}

	public void setDevice(String device) {
		this.put("device", device);
	}

	public String getUserAgent() {
		return this.getString("userAgent");
	}

	public void setUserAgent(String userAgent) {
		this.put("userAgent", userAgent);
	}

	public String getReferrer() {
		return this.getString("referrer");
	}

	public void setReferrer(String referrer) {
		this.put("referrer", referrer);
	}

	public String getIp() {
		return this.getString("ip");
	}

	public void setIp(String ip) {
		this.put("ip", ip);
	}

	public Date getTimestamp() {
		return new Date(this.getLong("timestamp"));
	}

	public void setTimestamp(Date timestamp) {
		this.put("timestamp", timestamp.getTime());
	}

	public String getAppId() {
		return this.getString("appId");
	}

	public void setAppId(String appId) {
		this.put("appId", appId);
	}

	public String getCampaign() {
		return this.getString("campaign");
	}

	public void setCampaign(String campaign) {
		this.put("campaign", campaign);
	}

	public String getAdTag() {
		return this.getString("adTag");
	}

	public void setAdTag(String adTag) {
		this.put("adTag", adTag);
	}

}
