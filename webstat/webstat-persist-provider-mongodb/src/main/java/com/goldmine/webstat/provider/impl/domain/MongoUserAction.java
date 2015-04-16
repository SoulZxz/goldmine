package com.goldmine.webstat.provider.impl.domain;

import org.apache.commons.lang.StringUtils;
import org.bson.BSONObject;

import com.goldmine.webstat.model.UserActionType;
import com.mongodb.BasicDBObject;

public class MongoUserAction extends BasicDBObject {

	private static final long serialVersionUID = -4866067254742822865L;

	public String getDigest() {
		return this.getString("digest");
	}

	public void setDigest(String digest) {
		this.put("digest", digest);
	}

	public UserActionType getActionType() {
		String actionType = this.getString("actionType");
		return StringUtils.isBlank(actionType) ? null : UserActionType
				.valueOf(actionType);
	}

	public void setActionType(UserActionType actionType) {
		this.put("actionType", actionType.name());
	}

	public MongoUserActionContext getUserActionContext() {
		BasicDBObject val = (BasicDBObject) this.get("userActionContext");

		if (val == null) {
			return null;
		} else {
			MongoUserActionContext context = new MongoUserActionContext();
			context.putAll((BSONObject) val);
			return context;
		}
	}

	public void setUserActionContext(MongoUserActionContext userActionContext) {
		this.put("userActionContext", userActionContext);
	}

	// specific props
	public String getContentUrl() {
		return this.getString("contentUrl");
	}

	public void setContentUrl(String contentUrl) {
		this.put("contentUrl", contentUrl);
	}

	public String getUri() {
		return this.getString("uri");
	}

	public void setUri(String uri) {
		this.put("uri", uri);
	}

	public String getQueryString() {
		return this.getString("queryString");
	}

	public void setQueryString(String queryString) {
		this.put("queryString", queryString);
	}

	public String getFunction() {
		return this.getString("function");
	}

	public void setFunction(String function) {
		this.put("function", function);
	}

}
