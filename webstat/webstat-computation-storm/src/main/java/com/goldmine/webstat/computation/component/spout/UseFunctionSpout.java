package com.goldmine.webstat.computation.component.spout;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

import com.goldmine.webstat.computation.component.WebTrafficNameDef;
import com.goldmine.webstat.computation.utils.StormConfigReader;
import com.goldmine.webstat.model.UseFunction;

public class UseFunctionSpout extends WebTrafficDataSpout {

	private static final long serialVersionUID = 4051112964220433427L;

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(WebTrafficNameDef.USE_FUNCTION));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void nextTuple() {
		UseFunction useFunction = getFactDataProvider().receiveUseFunction();
		if (useFunction != null) {
			collector.emit(new Values(useFunction));
		}

		Utils.sleep(StormConfigReader.getSpoutEmitInterval());
	}
}
