package com.goldmine.webstat.viewer.repo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goldmine.webstat.viewer.domain.ActionContext;
import com.goldmine.webstat.viewer.domain.ActionSpecInfo;
import com.goldmine.webstat.viewer.domain.UserActionFact;
import com.goldmine.webstat.viewer.repo.UserActionFactRepo;

@Repository
public class UserActionFactRepoImpl extends WebstatRepositoryImpl<UserActionFact, Long> implements
		UserActionFactRepo {

	@Override
	public UserActionFact findUserHasAction(Date start, Date end, ActionContext actionContext,
			ActionSpecInfo actionSpecInfo) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder builder = new StringBuilder("from UserActionFact fact where 1 = 1");

		if (start != null) {
			builder.append(" and fact.userActionContext.timestamp >= ?");
			params.add(start);
		}

		if (end != null) {
			builder.append(" and fact.userActionContext.timestamp < ?");
			params.add(end);
		}

		builder.append(buildConditionQuery("fact.userActionContext", actionContext));
		builder.append(buildConditionQuery("fact.actionSpecInfo", actionSpecInfo));

		List<UserActionFact> result = this.find(builder.toString(), params.toArray());
		return this.returnFirst(result);
	}

	@Override
	public List<UserActionFact> findUserActionFacts(Date start, Date end,
			ActionContext actionContext, ActionSpecInfo actionSpecInfo) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder builder = new StringBuilder("from UserActionFact fact where 1 = 1");

		if (start != null) {
			builder.append(" and fact.userActionContext.timestamp >= ?");
			params.add(start);
		}

		if (end != null) {
			builder.append(" and fact.userActionContext.timestamp < ?");
			params.add(end);
		}

		builder.append(buildConditionQuery("fact.userActionContext", actionContext));
		builder.append(buildConditionQuery("fact.actionSpecInfo", actionSpecInfo));

		return this.find(builder.toString(), params.toArray());
	}

}
