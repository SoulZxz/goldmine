package com.goldmine.webstat.provider.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;
import com.goldmine.webstat.provider.TrafficLogPersistServiceProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PlainfileTrafficLogPersistService implements TrafficLogPersistServiceProvider {

	private Log log = LogFactory.getLog(PlainfileTrafficLogPersistService.class);

	private Gson gson = new GsonBuilder().disableHtmlEscaping()
			.setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	public void logPageView(PageView pageView) {
		log.info(gson.toJson(pageView));
	}

	public void logUseFunction(UseFunction useFunction) {
		log.info(gson.toJson(useFunction));
	}

}
