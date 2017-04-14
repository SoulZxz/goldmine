package com.goldmine.webstat.viewer.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 用户行为背景，包括时间，地点（ui），用户标识
 * 
 * @author zhaoxuanzhang
 * 
 */
@Embeddable
public class UserActionContext extends ActionContext {

	@Column(name = "user_id_type")
	private String userIdType;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "device")
	private String device;

	@Column(name = "user_agent")
	private String userAgent;

	@Column(name = "ip")
	private String ip;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp")
	private Date timestamp;

	public String getUserIdType() {
		return userIdType;
	}

	public void setUserIdType(String userIdType) {
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

	@Override
	public String toString() {
		return "UserActionContext [userIdType=" + userIdType + ", userId=" + userId + ", device="
				+ device + ", userAgent=" + userAgent + ", referrer=" + referrer + ", ip=" + ip
				+ ", timestamp=" + timestamp + ", campaign=" + campaign + ", adTag=" + adTag + "]";
	}

}
