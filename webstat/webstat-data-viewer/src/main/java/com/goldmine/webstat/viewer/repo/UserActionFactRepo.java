package com.goldmine.webstat.viewer.repo;

import java.util.Date;
import java.util.List;

import com.goldmine.webstat.viewer.domain.ActionContext;
import com.goldmine.webstat.viewer.domain.ActionSpecInfo;
import com.goldmine.webstat.viewer.domain.UserActionFact;

public interface UserActionFactRepo extends BaseRepository<UserActionFact, Long> {

	UserActionFact findUserHasAction(Date start, Date end, ActionContext actionContext,
			ActionSpecInfo actionSpecInfo);

	List<UserActionFact> findUserActionFacts(Date start, Date end, ActionContext actionContext,
			ActionSpecInfo actionSpecInfo);
}
