package com.goldmine.webstat.computation.repo;

import java.util.Date;

import com.goldmine.webstat.computation.domain.ActionContext;
import com.goldmine.webstat.computation.domain.ActionSpecInfo;
import com.goldmine.webstat.computation.domain.UserActionFact;

public interface UserActionFactRepo extends BaseRepository<UserActionFact, Long> {

	UserActionFact findUserHasAction(Date start, Date end, ActionContext actionContext,
			ActionSpecInfo actionSpecInfo);

	int findUserHasActionByTrait(Date start, Date end, String userId, String trait);
}
