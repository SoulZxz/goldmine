package com.goldmine.webstat.provider.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;

@Component
public class QueueStorage {

	private BlockingQueue<PageView> pageViewQueue = new LinkedBlockingQueue<>();

	private BlockingQueue<UseFunction> useFunctionQueue = new LinkedBlockingQueue<>();

	public void offer(PageView pageView) {
		pageViewQueue.offer(pageView);
	}

	public void offer(UseFunction useFunction) {
		useFunctionQueue.offer(useFunction);
	}

	public PageView takePageView() throws InterruptedException {
		return pageViewQueue.take();
	}

	public UseFunction takeUseFunction() throws InterruptedException {
		return useFunctionQueue.take();
	}

}
