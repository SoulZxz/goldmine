package com.goldmine.webstat.computation;

import backtype.storm.topology.TopologyBuilder;

import com.goldmine.webstat.computation.bean.TimeFrame;
import com.goldmine.webstat.computation.component.bolt.PVCount;
import com.goldmine.webstat.computation.component.bolt.PageViewPersist;
import com.goldmine.webstat.computation.component.bolt.UseFunctionCount;
import com.goldmine.webstat.computation.component.bolt.UseFunctionPersist;
import com.goldmine.webstat.computation.component.spout.PageViewSpout;
import com.goldmine.webstat.computation.component.spout.UseFunctionSpout;

public class TopologyBuilderFactory {

	public static TopologyBuilder getBuilder() {
		TopologyBuilder builder = new TopologyBuilder();
		// page view
		builder.setSpout("PageViewSpout", new PageViewSpout(), 1);

		builder.setBolt("PageViewPersist", new PageViewPersist(), 2).shuffleGrouping(
				"PageViewSpout");
		builder.setBolt("PVCountHour", new PVCount(TimeFrame.HOUR), 2).shuffleGrouping(
				"PageViewPersist");
		builder.setBolt("PVCountDay", new PVCount(TimeFrame.DAY), 2).shuffleGrouping("PVCountHour");
		builder.setBolt("PVCountMonth", new PVCount(TimeFrame.MONTH), 2).shuffleGrouping(
				"PVCountDay");

		// use function
		builder.setSpout("UseFunctionSpout", new UseFunctionSpout(), 1);

		builder.setBolt("UseFunctionPersist", new UseFunctionPersist(), 2).shuffleGrouping(
				"UseFunctionSpout");
		builder.setBolt("UseFunctionCountHour", new UseFunctionCount(TimeFrame.HOUR), 2)
				.shuffleGrouping("UseFunctionPersist");
		builder.setBolt("UseFunctionCountDay", new UseFunctionCount(TimeFrame.DAY), 2)
				.shuffleGrouping("UseFunctionCountHour");
		builder.setBolt("UseFunctionCountMonth", new UseFunctionCount(TimeFrame.MONTH), 2)
				.shuffleGrouping("UseFunctionCountDay");

		return builder;
	}

}
