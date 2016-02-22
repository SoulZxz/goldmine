package com.goldmine.webstat.computation.component.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

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
	public void execute(Tuple input) {
		WebTrafficIndexService service = this.getWebTrafficIndexService();

		PageView pageView = this.getPageView(input);
		service.updateUserActionIndex(pageView, timeFrame);

		collector.emit(input, new Values(pageView));
		collector.ack(input);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(WebTrafficNameDef.PAGE_VIEW));
	}

}
