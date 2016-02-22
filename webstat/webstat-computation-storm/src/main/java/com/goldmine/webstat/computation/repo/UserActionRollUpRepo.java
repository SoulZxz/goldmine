package com.goldmine.webstat.computation.repo;

import java.util.Date;

import com.goldmine.webstat.computation.domain.ActionContext;
import com.goldmine.webstat.computation.domain.ActionSpecInfo;
import com.goldmine.webstat.computation.domain.UserActionRollUp;

public interface UserActionRollUpRepo extends BaseRepository<UserActionRollUp, String> {

	UserActionRollUp findExistingRollUp(Date start, String timeframe, ActionContext actionContext,
			ActionSpecInfo actionSpecInfo);

	int increaseUsers(String id, long increment);

	int increaseUsage(String id, long increment);

}
