package com.goldmine.webstat.computation;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.utils.Utils;

import com.goldmine.webstat.computation.serializer.PageViewSerializer;
import com.goldmine.webstat.computation.serializer.UseFunctionSerializer;
import com.goldmine.webstat.computation.serializer.UserActionContextSerializer;
import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;
import com.goldmine.webstat.model.UserActionContext;
import com.goldmine.webstat.model.UserActionType;
import com.goldmine.webstat.model.UserIdType;

public class LocalRunner {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext xmlCtx = new ClassPathXmlApplicationContext();
		xmlCtx.setConfigLocation("spring-conf/spring.xml");
		xmlCtx.refresh();

		Config conf = new Config();
		conf.setDebug(true);
		conf.setNumWorkers(2);
		conf.registerSerialization(PageView.class, PageViewSerializer.class);
		conf.registerSerialization(UserActionContext.class, UserActionContextSerializer.class);
		conf.registerSerialization(UseFunction.class, UseFunctionSerializer.class);
		conf.registerSerialization(UserActionType.class);
		conf.registerSerialization(UserIdType.class);
		conf.registerSerialization(Date.class);

		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("test", conf, TopologyBuilderFactory.getBuilder().createTopology());
		Utils.sleep(120000);
		cluster.killTopology("test");
		cluster.shutdown();
	}

}
