package com.goldmine.webstat.computation.component.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.topology.base.BaseRichSpout;

import com.goldmine.webstat.computation.provider.FactDataProvider;
import com.goldmine.webstat.computation.utils.ApplicationContextHolder;

public abstract class WebTrafficDataSpout extends BaseRichSpout {

	private static final long serialVersionUID = 7712464586716460224L;

	protected SpoutOutputCollector collector;

	protected FactDataProvider getFactDataProvider() {
		return ApplicationContextHolder.getApplicationContext().getBean(FactDataProvider.class);
	}

}
