package com.goldmine.webstat.computation.component.bolt;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.goldmine.webstat.computation.bean.TimeFrame;
import com.goldmine.webstat.computation.component.WebTrafficNameDef;
import com.goldmine.webstat.computation.service.WebTrafficIndexService;
import com.goldmine.webstat.computation.utils.ApplicationContextHolder;
import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;

public abstract class WebTrafficDataBolt extends BaseRichBolt {

	private static final long serialVersionUID = 5349421500810905932L;

	private static final Logger logger = LogManager.getLogger(WebTrafficIndexService.class);

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

	@Override
	public void execute(Tuple input) {
		try {
			safeExecute(input);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			collector.emit(input, new Values(getRawReturn(input)));
			collector.ack(input);
		}
	}

	protected abstract Object getRawReturn(Tuple input);

	protected abstract void safeExecute(Tuple input);

}
