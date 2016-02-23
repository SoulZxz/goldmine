package com.goldmine.webstat.computation.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "webstat_user_action_roll_up", indexes = { @Index(columnList = "referrer"),
		@Index(columnList = "app_id"), @Index(columnList = "campaign"),
		@Index(columnList = "ad_tag"), @Index(columnList = "from_date"),
		@Index(columnList = "to_date"), @Index(columnList = "timeframe"),
		@Index(columnList = "action_type"), @Index(columnList = "roll_up_type"),
		@Index(columnList = "uri"), @Index(columnList = "content_url"),
		@Index(columnList = "function") })
public class UserActionRollUp {

	@Id
	@Column(name = "id")
	private String dataId;

	@Column(name = "referrer")
	private String referrer;

	@Column(name = "referrer_name")
	private String referrerName;

	@Column(name = "app_id")
	private String appId;

	@Column(name = "campaign")
	private String campaign;

	@Column(name = "ad_tag")
	private String adTag;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "from_date")
	private Date fromDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "to_date")
	private Date toDate;

	@Column(name = "timeframe")
	private String timeframe;

	@Column(name = "action_type")
	private String actionType;

	@Column(name = "roll_up_type")
	private String rollUpType;

	@Column(name = "roll_up_count")
	private Long rollUpCount;

	/**
	 * for page view
	 */
	@Column(name = "uri")
	private String uri;

	@Column(name = "query_string")
	private String queryString;

	/**
	 * for display content
	 */
	@Column(name = "content_url")
	private String contentUrl;

	/**
	 * for use function
	 */
	@Column(name = "function")
	private String function;

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getRollUpType() {
		return rollUpType;
	}

	public void setRollUpType(String rollUpType) {
		this.rollUpType = rollUpType;
	}

	public Long getRollUpCount() {
		return rollUpCount;
	}

	public void setRollUpCount(Long rollUpCount) {
		this.rollUpCount = rollUpCount;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String usageTrait() {
		return "UserActionRollUp [appId=" + appId + ", campaign=" + campaign + ", adTag=" + adTag
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", timeframe=" + timeframe
				+ ", actionType=" + actionType + ", uri=" + uri + ", contentUrl=" + contentUrl
				+ ", function=" + function + "]";
	}

	public String usersTrait() {
		return "UserActionRollUp [fromDate=" + fromDate + ", toDate=" + toDate + ", timeframe="
				+ timeframe + ", actionType=" + actionType + ", uri=" + uri + ", contentUrl="
				+ contentUrl + ", function=" + function + "]";
	}

}
