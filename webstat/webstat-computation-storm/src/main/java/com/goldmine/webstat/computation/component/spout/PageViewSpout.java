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
import com.goldmine.webstat.model.PageView;

public class PageViewSpout extends WebTrafficDataSpout {

	private static final long serialVersionUID = -9047442684520723947L;

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(WebTrafficNameDef.PAGE_VIEW));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void nextTuple() {
		PageView pageView = getFactDataProvider().receivePageView();
		if (pageView != null) {
			collector.emit(new Values(pageView));
		}

		Utils.sleep(StormConfigReader.getSpoutEmitInterval());
	}

}
