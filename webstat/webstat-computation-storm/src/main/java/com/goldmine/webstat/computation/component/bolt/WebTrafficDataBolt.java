package com.goldmine.webstat.computation.component.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import com.goldmine.webstat.computation.bean.TimeFrame;
import com.goldmine.webstat.computation.component.WebTrafficNameDef;
import com.goldmine.webstat.computation.service.WebTrafficIndexService;
import com.goldmine.webstat.computation.utils.ApplicationContextHolder;
import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;

public abstract class WebTrafficDataBolt extends BaseRichBolt {

	private static final long serialVersionUID = 5349421500810905932L;

	protected OutputCollector collector;

	protected TimeFrame timeFrame;

	public WebTrafficDataBolt() {
		super();
	}

	public WebTrafficDataBolt(TimeFrame timeFrame) {
		super();
		this.timeFrame = timeFrame;
	}

	protected WebTrafficIndexService getWebTrafficIndexService() {
		return ApplicationContextHolder.getApplicationContext().getBean(
				WebTrafficIndexService.class);
	}

	protected PageView getPageView(Tuple input) {
		return (PageView) input.getValueByField(WebTrafficNameDef.PAGE_VIEW);
	}

	protected UseFunction getUseFunction(Tuple input) {
		return (UseFunction) input.getValueByField(WebTrafficNameDef.USE_FUNCTION);
	}
}
