package com.goldmine.webstat.computation.repo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goldmine.webstat.computation.domain.ActionContext;
import com.goldmine.webstat.computation.domain.ActionSpecInfo;
import com.goldmine.webstat.computation.domain.UserActionRollUp;
import com.goldmine.webstat.computation.repo.UserActionRollUpRepo;

@Repository
public class UserActionRollUpRepoImpl extends WebstatRepositoryImpl<UserActionRollUp, String>
		implements UserActionRollUpRepo {

	@Override
	public UserActionRollUp findExistingRollUp(Date start, String timeframe,
			ActionContext actionContext, ActionSpecInfo actionSpecInfo) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder builder = new StringBuilder(
				"from UserActionRollUp rollup where rollup.fromDate = ? and rollup.timeframe = ?");
		params.add(start);
		params.add(timeframe);

		builder.append(buildConditionQuery("rollup", actionContext));
		builder.append(buildConditionQuery("rollup", actionSpecInfo));

		List<UserActionRollUp> result = this.find(builder.toString(), params.toArray());
		return this.returnFirst(result);
	}

	@Override
	public int increaseUsers(String id, long increment) {
		String hql = "update UserActionRollUp rollup set rollup.rollUpCount = rollup.rollUpCount + ? where rollup.id = ? and rollup.rollUpType = 'USERS'";
		return this.getHibernateTemplate().bulkUpdate(hql, increment, id);
	}

	@Override
	public int increaseUsage(String id, long increment) {
		String hql = "update UserActionRollUp rollup set rollup.rollUpCount = rollup.rollUpCount + ? where rollup.id = ? and rollup.rollUpType = 'USAGE'";
		return this.getHibernateTemplate().bulkUpdate(hql, increment, id);
	}

}
