package com.goldmine.webstat.computation.component.bolt;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import com.goldmine.webstat.computation.bean.TimeFrame;
import com.goldmine.webstat.computation.component.WebTrafficNameDef;
import com.goldmine.webstat.computation.service.WebTrafficIndexService;
import com.goldmine.webstat.model.PageView;

public class PVCount extends WebTrafficDataBolt {

	private static final long serialVersionUID = 4089996293920156890L;

	public PVCount(TimeFrame timeFrame) {
		super(timeFrame);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(WebTrafficNameDef.PAGE_VIEW));
	}

	@Override
	protected Object getRawReturn(Tuple input) {
		return this.getPageView(input);
	}

	@Override
	protected void safeExecute(Tuple input) {
		WebTrafficIndexService service = this.getWebTrafficIndexService();

		PageView pageView = this.getPageView(input);
		service.updateUserActionIndex(pageView, timeFrame);
	}

}
