package com.goldmine.webstat.model;

public class DisplayContent extends UserAction {

	private String contentUrl;

	public DisplayContent() {
		super();
		this.actionType = UserActionType.DISPLAY_CONTENT;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	@Override
	public String toString() {
		return "DisplayContent [contentUrl=" + contentUrl + ", actionType="
				+ actionType + ", userActionContext=" + userActionContext + "]";
	}

}
