package com.goldmine.webstat.viewer.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldmine.webstat.viewer.domain.ActionContext;
import com.goldmine.webstat.viewer.domain.ActionSpecInfo;
import com.goldmine.webstat.viewer.domain.UserActionFact;
import com.goldmine.webstat.viewer.repo.UserActionFactRepo;
import com.goldmine.webstat.viewer.service.DataService;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	private UserActionFactRepo userActionFactRepo;

	@Override
	@Transactional("webstatTransactionManager")
	public List<UserActionFact> findUserActionFacts(Date start, Date end, String actionType,
			String function, String appId, String campaign, String adTag) {
		ActionContext actionContext = new ActionContext();
		ActionSpecInfo actionSpecInfo = new ActionSpecInfo();

		actionContext.setAdTag(adTag);
		actionContext.setAppId(appId);
		actionContext.setCampaign(campaign);

		actionSpecInfo.setActionType(actionType);
		actionSpecInfo.setFunction(function);

		return userActionFactRepo.findUserActionFacts(start, end, actionContext, actionSpecInfo);
	}

}
