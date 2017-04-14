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
import com.goldmine.webstat.model.UseFunction;

public class UseFunctionCount extends WebTrafficDataBolt {

	private static final long serialVersionUID = 6340491795310317469L;

	public UseFunctionCount(TimeFrame timeFrame) {
		super(timeFrame);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(WebTrafficNameDef.USE_FUNCTION));
	}

	@Override
	protected Object getRawReturn(Tuple input) {
		return this.getUseFunction(input);
	}

	@Override
	protected void safeExecute(Tuple input) {
		WebTrafficIndexService service = this.getWebTrafficIndexService();

		UseFunction useFunction = this.getUseFunction(input);
		service.updateUserActionIndex(useFunction, timeFrame);
	}

}
