package com.goldmine.webstat.computation;

import java.util.Date;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.goldmine.webstat.computation.serializer.PageViewSerializer;
import com.goldmine.webstat.computation.serializer.UseFunctionSerializer;
import com.goldmine.webstat.computation.serializer.UserActionContextSerializer;
import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;
import com.goldmine.webstat.model.UserActionContext;
import com.goldmine.webstat.model.UserActionType;
import com.goldmine.webstat.model.UserIdType;

public class Main {

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
	}

}
