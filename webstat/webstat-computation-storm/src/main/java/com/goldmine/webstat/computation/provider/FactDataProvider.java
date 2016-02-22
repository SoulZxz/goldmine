package com.goldmine.webstat.computation.provider;

import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;

public interface FactDataProvider {

	PageView receivePageView();

	UseFunction receiveUseFunction();

}
