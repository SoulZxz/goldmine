package com.goldmine.webstat.provider.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.goldmine.webstat.model.UserAction;
import com.goldmine.webstat.model.UserActionContext;
import com.goldmine.webstat.provider.impl.domain.MongoUserAction;
import com.goldmine.webstat.provider.impl.domain.MongoUserActionContext;

@Component
public class MongoBeanConverter {

	private String[] ignoreProps = { "userActionContext" };

	public MongoUserAction convert(UserAction userAction) {
		MongoUserAction result = new MongoUserAction();
		result.setUserActionContext(this.convert(userAction
				.getUserActionContext()));
		BeanUtils.copyProperties(userAction, result, ignoreProps);
		return result;
	}

	public MongoUserActionContext convert(UserActionContext userActionContext) {
		MongoUserActionContext result = new MongoUserActionContext();
		BeanUtils.copyProperties(userActionContext, result);
		return result;
	}

}
