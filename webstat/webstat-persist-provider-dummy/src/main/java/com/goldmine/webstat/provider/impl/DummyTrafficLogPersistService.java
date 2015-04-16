package com.goldmine.webstat.provider.impl;

import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;
import com.goldmine.webstat.provider.TrafficLogPersistServiceProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DummyTrafficLogPersistService implements
		TrafficLogPersistServiceProvider {

	private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	public void logPageView(PageView pageView) {
		System.out.println(gson.toJson(pageView));
	}

	public void logUseFunction(UseFunction useFunction) {
		System.out.println(gson.toJson(useFunction));
	}

}
