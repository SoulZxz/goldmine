package com.goldmine.webstat.model;

public class UseFunction extends UserAction {

	private String function;

	private String vars;

	public UseFunction() {
		super();
		this.actionType = UserActionType.USE_FUNCTION;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getVars() {
		return vars;
	}

	public void setVars(String vars) {
		this.vars = vars;
	}

	@Override
	public String toString() {
		return "UseFunction [function=" + function + ", vars=" + vars + ", actionType="
				+ actionType + ", userActionContext=" + userActionContext + "]";
	}

}
