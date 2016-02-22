package com.goldmine.webstat.computation.service;

import com.goldmine.webstat.computation.bean.TimeFrame;
import com.goldmine.webstat.model.UserAction;

public interface WebTrafficIndexService {

	void updateUserActionIndex(UserAction userAction, TimeFrame timeFrame);

	void saveUserActionFact(UserAction userAction);

}
