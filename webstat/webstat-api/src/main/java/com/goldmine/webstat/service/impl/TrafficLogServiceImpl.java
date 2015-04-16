package com.goldmine.webstat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;
import com.goldmine.webstat.model.UserActionContext;
import com.goldmine.webstat.provider.TrafficLogPersistServiceProvider;
import com.goldmine.webstat.service.TrafficLogService;

@Service
public class TrafficLogServiceImpl implements TrafficLogService {

	@Autowired
	private TrafficLogPersistServiceProvider provider;

	public void logPageView(UserActionContext userActionContext, String uri, String queryString) {
		PageView pageView = new PageView();
		pageView.setUserActionContext(userActionContext);
		pageView.setUri(uri);
		pageView.setQueryString(queryString);
		provider.logPageView(pageView);
	}

	public void logUseFunction(UserActionContext userActionContext, String function, String vars) {
		UseFunction useFunction = new UseFunction();
		useFunction.setUserActionContext(userActionContext);
		useFunction.setFunction(function);
		useFunction.setVars(vars);
		provider.logUseFunction(useFunction);
	}

}
