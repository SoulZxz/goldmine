package com.goldmine.webstat.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;
import com.goldmine.webstat.provider.TrafficLogPersistServiceProvider;

public class JMSTrafficLogPersistService implements TrafficLogPersistServiceProvider {

	@Autowired
	private QueueStorage queueStorage;

	public void sendPageViewMessage(final PageView pageView) {
		queueStorage.offer(pageView);
	}

	public void sendUseFunctionMessage(final UseFunction useFunction) {
		queueStorage.offer(useFunction);
	}

	@Override
	public void logPageView(PageView pageView) {
		this.sendPageViewMessage(pageView);
	}

	@Override
	public void logUseFunction(UseFunction useFunction) {
		this.sendUseFunctionMessage(useFunction);
	}

}
