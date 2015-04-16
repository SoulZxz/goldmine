package com.goldmine.webstat.model;

public class UserOrder extends UserAction {

	public UserOrder() {
		super();
		this.actionType = UserActionType.USER_ORDER;
	}

	@Override
	public String toString() {
		return "UserOrder [actionType=" + actionType + ", userActionContext="
				+ userActionContext + "]";
	}

}
