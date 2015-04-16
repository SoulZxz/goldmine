package com.goldmine.webstat.model;

public class PageView extends UserAction {

	private String uri;

	private String queryString;

	public PageView() {
		super();
		this.actionType = UserActionType.PAGE_VIEW;
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

	@Override
	public String toString() {
		return "PageView [uri=" + uri + ", queryString=" + queryString
				+ ", actionType=" + actionType + ", userActionContext="
				+ userActionContext + "]";
	}

}
