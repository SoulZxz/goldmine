package com.goldmine.webstat.computation.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 辅助获取spring的ApplicationContext
 * </p>
 * 
 * @author 赵玄璋 2015年5月18日
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

	private static ApplicationContext instance;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		initInstance(applicationContext);
	}

	public static ApplicationContext getApplicationContext() {
		return instance;
	}

	private static void initInstance(ApplicationContext applicationContext) {
		instance = applicationContext;
	}

}
