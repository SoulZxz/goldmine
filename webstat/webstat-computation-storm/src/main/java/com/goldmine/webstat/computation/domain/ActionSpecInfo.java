package com.goldmine.webstat.computation.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ActionSpecInfo {

	@Column(name = "action_type")
	private String actionType;

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

	@Column(name = "vars")
	private String vars;

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
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

	public String getVars() {
		return vars;
	}

	public void setVars(String vars) {
		this.vars = vars;
	}

	public String trait() {
		return "ActionSpecInfo [actionType=" + actionType + ", uri=" + uri + ", contentUrl="
				+ contentUrl + ", function=" + function + "]";
	}

}
