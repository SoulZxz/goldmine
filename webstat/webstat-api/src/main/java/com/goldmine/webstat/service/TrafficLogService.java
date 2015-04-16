package com.goldmine.webstat.service;

import com.goldmine.webstat.model.UserActionContext;

public interface TrafficLogService {

	void logPageView(UserActionContext userActionContext, String uri, String queryString);

	void logUseFunction(UserActionContext userActionContext, String function, String vars);

}
