package com.goldmine.webstat.viewer.service;

import java.util.Date;
import java.util.List;

import com.goldmine.webstat.viewer.domain.UserActionFact;

public interface DataService {

	List<UserActionFact> findUserActionFacts(Date start, Date end, String actionType,
			String function, String appId, String campaign, String adTag);

}
