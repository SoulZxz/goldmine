package com.goldmine.webstat.computation.provider.impl;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.goldmine.webstat.computation.provider.FactDataProvider;
import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;

@Profile("default")
@Component
public class JMSFactDataProvider implements FactDataProvider {

	private LinkedBlockingQueue<PageView> pageViewQueue = new LinkedBlockingQueue<>();

	private LinkedBlockingQueue<UseFunction> useFunctionQueue = new LinkedBlockingQueue<>();

	@Override
	public PageView receivePageView() {
		return pageViewQueue.poll();
	}

	@Override
	public UseFunction receiveUseFunction() {
		return useFunctionQueue.poll();
	}

	public void addPageView(PageView pageView) {
		pageViewQueue.offer(pageView);
	}

	public void addUseFunction(UseFunction useFunction) {
		useFunctionQueue.offer(useFunction);
	}
}
