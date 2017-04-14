package com.goldmine.webstat.computation.component.bolt;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import com.goldmine.webstat.computation.component.WebTrafficNameDef;
import com.goldmine.webstat.computation.service.WebTrafficIndexService;

public class PageViewPersist extends WebTrafficDataBolt {

	private static final long serialVersionUID = -4370075075947710587L;

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
		service.saveUserActionFact(this.getPageView(input));
	}

}
