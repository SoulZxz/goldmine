package com.goldmine.webstat.model;

public class UserSignUp extends UserAction {

	public UserSignUp() {
		super();
		this.actionType = UserActionType.USER_SIGN_UP;
	}

	@Override
	public String toString() {
		return "UserSignUp [actionType=" + actionType + ", userActionContext="
				+ userActionContext + "]";
	}

}
