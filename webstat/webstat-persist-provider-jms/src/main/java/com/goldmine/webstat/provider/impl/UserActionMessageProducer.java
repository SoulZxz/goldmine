package com.goldmine.webstat.provider.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class UserActionMessageProducer {

	private static final Logger logger = LogManager.getLogger(UserActionMessageProducer.class);

	private static final long POLL_INTERVAL = 40L;

	private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	private ExecutorService threadPool = Executors.newCachedThreadPool();

	@Autowired
	@Qualifier("connectionFactory")
	private ConnectionFactory connectionFactory;

	@Autowired
	@Qualifier("factDataMessageQueue")
	private Queue queue;

	@Autowired
	private QueueStorage queueStorage;

	private JmsTemplate jmsTemplate;

	@PostConstruct
	public void initJmsTemplate() {
		this.jmsTemplate = new JmsTemplate(connectionFactory);

		threadPool.execute(new Runnable() {
			public void run() {
				while (true) {
					try {
						final PageView pageView = queueStorage.takePageView();
						jmsTemplate.send(queue, new MessageCreator() {
							public Message createMessage(Session session) throws JMSException {
								MapMessage mapMsg = session.createMapMessage();
								mapMsg.setString("actionType", "PageView");
								mapMsg.setString("factData", gson.toJson(pageView));
								return mapMsg;
							}
						});
						Thread.sleep(POLL_INTERVAL);
					} catch (Throwable e) {
						logger.error(e.toString(), e);
					}
				}
			}
		});

		threadPool.execute(new Runnable() {
			public void run() {
				while (true) {
					try {
						final UseFunction useFunction = queueStorage.takeUseFunction();
						jmsTemplate.send(queue, new MessageCreator() {
							public Message createMessage(Session session) throws JMSException {
								MapMessage mapMsg = session.createMapMessage();
								mapMsg.setString("actionType", "UseFunction");
								mapMsg.setString("factData", gson.toJson(useFunction));
								return mapMsg;
							}
						});
						Thread.sleep(POLL_INTERVAL);
					} catch (InterruptedException e) {
						logger.error(e.toString(), e);
					}
				}
			}
		});
	}
}
