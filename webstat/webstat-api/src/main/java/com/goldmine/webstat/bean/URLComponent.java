package com.goldmine.webstat.bean;

public class URLComponent {

	private String uri;

	private String queryString;

	public URLComponent(String uri, String queryString) {
		super();
		this.uri = uri;
		this.queryString = queryString;
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

}
