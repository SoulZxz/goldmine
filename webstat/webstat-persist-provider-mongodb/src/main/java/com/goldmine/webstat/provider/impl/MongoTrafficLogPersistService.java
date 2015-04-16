package com.goldmine.webstat.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;
import com.goldmine.webstat.provider.TrafficLogPersistServiceProvider;
import com.goldmine.webstat.provider.impl.domain.MongoUserAction;
import com.goldmine.webstat.provider.impl.repo.UserActionRepository;

public class MongoTrafficLogPersistService implements
		TrafficLogPersistServiceProvider {

	@Autowired
	private UserActionRepository userActionRepository;

	@Autowired
	private MongoBeanConverter mongoBeanConverter;

	public void logPageView(PageView pageView) {
		MongoUserAction target = mongoBeanConverter.convert(pageView);
		target.setDigest(DataDigest.digest(pageView.toString()));
		userActionRepository.save(target);
	}

	public void logUseFunction(UseFunction useFunction) {
		MongoUserAction target = mongoBeanConverter.convert(useFunction);
		target.setDigest(DataDigest.digest(useFunction.toString()));
		userActionRepository.save(target);
	}

}
