package com.goldmine.webstat.viewer.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "webstat_user_action_fact", indexes = { @Index(columnList = "trait"),
		@Index(columnList = "action_type"), @Index(columnList = "function"),
		@Index(columnList = "app_id"), @Index(columnList = "campaign"),
		@Index(columnList = "ad_tag"), @Index(columnList = "timestamp") })
public class UserActionFact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long factId;

	@Embedded
	private UserActionContext userActionContext;

	@Embedded
	private ActionSpecInfo actionSpecInfo;

	@Column(name = "trait")
	private String trait;

	public UserActionContext getUserActionContext() {
		return userActionContext;
	}

	public void setUserActionContext(UserActionContext userActionContext) {
		this.userActionContext = userActionContext;
	}

	public Long getFactId() {
		return factId;
	}

	public void setFactId(Long factId) {
		this.factId = factId;
	}

	public ActionSpecInfo getActionSpecInfo() {
		return actionSpecInfo;
	}

	public void setActionSpecInfo(ActionSpecInfo actionSpecInfo) {
		this.actionSpecInfo = actionSpecInfo;
	}

	public String getTrait() {
		return trait;
	}

	public void setTrait(String trait) {
		this.trait = trait;
	}

}
