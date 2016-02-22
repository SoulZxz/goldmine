package com.goldmine.webstat.computation.component.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.goldmine.webstat.computation.component.WebTrafficNameDef;
import com.goldmine.webstat.computation.service.WebTrafficIndexService;
import com.goldmine.webstat.model.UseFunction;

public class UseFunctionPersist extends WebTrafficDataBolt {

	private static final long serialVersionUID = -5226566048259981530L;

	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		WebTrafficIndexService service = this.getWebTrafficIndexService();

		UseFunction useFunction = this.getUseFunction(input);
		service.saveUserActionFact(useFunction);

		collector.emit(input, new Values(useFunction));
		collector.ack(input);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(WebTrafficNameDef.USE_FUNCTION));
	}
}
