package com.goldmine.webstat.provider;

import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;

public interface TrafficLogPersistServiceProvider {

	void logPageView(PageView pageView);

	void logUseFunction(UseFunction useFunction);

}
