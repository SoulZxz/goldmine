package com.goldmine.webstat.computation.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldmine.webstat.computation.bean.TimeFrame;
import com.goldmine.webstat.computation.domain.ActionContext;
import com.goldmine.webstat.computation.domain.ActionSpecInfo;
import com.goldmine.webstat.computation.domain.UserActionContext;
import com.goldmine.webstat.computation.domain.UserActionFact;
import com.goldmine.webstat.computation.domain.UserActionRollUp;
import com.goldmine.webstat.computation.repo.UserActionFactRepo;
import com.goldmine.webstat.computation.repo.UserActionRollUpRepo;
import com.goldmine.webstat.computation.service.WebTrafficIndexService;
import com.goldmine.webstat.computation.utils.BeanMapper;
import com.goldmine.webstat.computation.utils.DataDigester;
import com.goldmine.webstat.computation.utils.TimeFrameNormalizer;
import com.goldmine.webstat.model.UserAction;

@Service
public class WebTrafficIndexServiceImpl implements WebTrafficIndexService {

	@Autowired
	private UserActionRollUpRepo userActionRollUpRepo;

	@Autowired
	private UserActionFactRepo userActionFactRepo;

	@Override
	@Transactional
	public void saveUserActionFact(UserAction userAction) {
		UserActionContext userActionContext = BeanMapper.mapObjectByType(
				userAction.getUserActionContext(), UserActionContext.class);
		ActionSpecInfo actionSpecInfo = BeanMapper
				.mapObjectByType(userAction, ActionSpecInfo.class);

		UserActionFact fact = new UserActionFact();
		fact.setUserActionContext(userActionContext);
		fact.setActionSpecInfo(actionSpecInfo);
		fact.setTrait(createFactTrait(actionSpecInfo));

		userActionFactRepo.save(fact);
	}

	@Override
	@Transactional
	public void updateUserActionIndex(UserAction userAction, TimeFrame timeframe) {
		Date occurTime = userAction.getUserActionContext().getTimestamp();
		Date start = TimeFrameNormalizer.calculateTimeFrameStart(occurTime, timeframe);
		Date end = TimeFrameNormalizer.calculateTimeFrameEnd(start, timeframe);
		ActionContext actionContext = BeanMapper.mapObjectByType(userAction.getUserActionContext(),
				ActionContext.class);
		ActionSpecInfo actionSpecInfo = BeanMapper
				.mapObjectByType(userAction, ActionSpecInfo.class);

		this.updateUsage(start, end, timeframe, actionContext, actionSpecInfo);
		this.updateUsers(start, end, timeframe, actionContext, actionSpecInfo, userAction
				.getUserActionContext().getUserId());
	}

	private void updateUsage(Date start, Date end, TimeFrame timeframe,
			ActionContext actionContext, ActionSpecInfo actionSpecInfo) {
		UserActionRollUp usageRollUp = new UserActionRollUp();
		BeanMapper.mapObject(actionContext, usageRollUp);
		BeanMapper.mapObject(actionSpecInfo, usageRollUp);
		usageRollUp.setTimeframe(timeframe.toString());
		usageRollUp.setFromDate(start);
		usageRollUp.setToDate(end);
		// 清除 referrer和queryString
		usageRollUp.setReferrer(null);
		usageRollUp.setQueryString(null);

		String targetId = DataDigester.digest(usageRollUp.usageTrait());

		UserActionRollUp existsUsageRollUp = userActionRollUpRepo.get(targetId);

		if (existsUsageRollUp == null) {
			usageRollUp.setRollUpType("USAGE");
			usageRollUp.setRollUpCount(1L);
			usageRollUp.setDataId(targetId);

			userActionRollUpRepo.save(usageRollUp);
		} else {
			userActionRollUpRepo.increaseUsage(existsUsageRollUp.getDataId(), 1L);
		}
	}

	private void updateUsers(Date start, Date end, TimeFrame timeframe,
			ActionContext actionContext, ActionSpecInfo actionSpecInfo, String userId) {
		UserActionRollUp usersRollUp = new UserActionRollUp();
		usersRollUp.setActionType(actionSpecInfo.getActionType());
		usersRollUp.setContentUrl(actionSpecInfo.getContentUrl());
		usersRollUp.setUri(actionSpecInfo.getUri());
		usersRollUp.setFunction(actionSpecInfo.getFunction());
		usersRollUp.setTimeframe(timeframe.toString());
		usersRollUp.setFromDate(start);
		usersRollUp.setToDate(end);

		String targetId = DataDigester.digest(usersRollUp.usersTrait());

		UserActionRollUp existsUsersRollUp = userActionRollUpRepo.get(targetId);

		if (existsUsersRollUp == null) {
			usersRollUp.setRollUpType("USERS");
			usersRollUp.setRollUpCount(1L);
			usersRollUp.setDataId(targetId);

			userActionRollUpRepo.save(usersRollUp);
		} else {
			String trait = this.createFactTrait(actionSpecInfo);
			int userActionFactSize = userActionFactRepo.findUserHasActionByTrait(start, end,
					userId, trait);
			// 只有本次前置运算步骤刚刚保存进去的数据
			if (userActionFactSize == 1) {
				userActionRollUpRepo.increaseUsers(existsUsersRollUp.getDataId(), 1L);
			}
		}
	}

	private String createFactTrait(ActionSpecInfo actionSpecInfo) {
		return DataDigester.digest(actionSpecInfo.trait());
	}
}
