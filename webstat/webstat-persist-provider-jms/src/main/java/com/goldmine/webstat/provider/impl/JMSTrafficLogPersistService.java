package com.goldmine.webstat.provider.impl;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;
import com.goldmine.webstat.provider.TrafficLogPersistServiceProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JMSTrafficLogPersistService implements TrafficLogPersistServiceProvider {

	private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	@Autowired
	@Qualifier("connectionFactory")
	private ConnectionFactory connectionFactory;

	@Autowired
	@Qualifier("factDataMessageQueue")
	private Queue queue;

	private JmsTemplate jmsTemplate;

	@PostConstruct
	public void initJmsTemplate() {
		this.jmsTemplate = new JmsTemplate(connectionFactory);
	}

	public void sendPageViewMessage(final PageView pageView) {
		this.jmsTemplate.send(this.queue, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMsg = session.createMapMessage();
				mapMsg.setString("actionType", "PageView");
				mapMsg.setString("factData", gson.toJson(pageView));
				return mapMsg;
			}
		});
	}

	public void sendUseFunctionMessage(final UseFunction useFunction) {
		this.jmsTemplate.send(this.queue, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMsg = session.createMapMessage();
				mapMsg.setString("actionType", "UseFunction");
				mapMsg.setString("factData", gson.toJson(useFunction));
				return mapMsg;
			}
		});
	}

	@Override
	public void logPageView(PageView pageView) {
		this.sendPageViewMessage(pageView);
	}

	@Override
	public void logUseFunction(UseFunction useFunction) {
		this.sendUseFunctionMessage(useFunction);
	}

}
