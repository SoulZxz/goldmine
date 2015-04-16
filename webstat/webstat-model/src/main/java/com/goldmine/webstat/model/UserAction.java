package com.goldmine.webstat.model;

public class UserAction {

	protected UserActionType actionType;

	protected UserActionContext userActionContext;

	public UserActionType getActionType() {
		return actionType;
	}

	public void setActionType(UserActionType actionType) {
		this.actionType = actionType;
	}

	public UserActionContext getUserActionContext() {
		return userActionContext;
	}

	public void setUserActionContext(UserActionContext userActionContext) {
		this.userActionContext = userActionContext;
	}

}
