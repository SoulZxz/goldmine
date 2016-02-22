package com.goldmine.webstat.computation.provider.impl;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class FactDataJMSListener implements MessageListener {

	private static final Logger logger = LogManager.getLogger(FactDataJMSListener.class);

	private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	@Autowired
	private JMSFactDataProvider factDataProvider;

	@Override
	public void onMessage(Message msg) {
		if (msg instanceof MapMessage) {
			MapMessage mapMsg = (MapMessage) msg;
			try {
				String actionType = mapMsg.getString("actionType");
				String factData = mapMsg.getString("factData");
				if ("PageView".equals(actionType)) {
					factDataProvider.addPageView(gson.fromJson(factData, PageView.class));
				} else if ("UseFunction".equals(actionType)) {
					factDataProvider.addUseFunction(gson.fromJson(factData, UseFunction.class));
				}
			} catch (JMSException e) {
				logger.error(e.toString(), e);
			}
		} else {
			logger.error("msg " + msg + " is not MapMessage");
		}
	}

}
