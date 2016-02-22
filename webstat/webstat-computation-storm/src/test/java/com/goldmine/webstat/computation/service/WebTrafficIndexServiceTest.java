package com.goldmine.webstat.computation.service;

import java.util.Date;

import org.testng.annotations.Test;

import com.goldmine.webstat.computation.domain.ActionContext;
import com.goldmine.webstat.computation.domain.ActionSpecInfo;
import com.goldmine.webstat.computation.domain.UserActionRollUp;
import com.goldmine.webstat.computation.utils.BeanMapper;
import com.goldmine.webstat.computation.utils.DataDigester;
import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UserActionContext;
import com.goldmine.webstat.model.UserActionType;
import com.goldmine.webstat.model.UserIdType;

public class WebTrafficIndexServiceTest {

	@Test
	public void testMap() {
		UserActionContext uac = new UserActionContext();
		uac.setAdTag("adTag");
		uac.setAppId("appId");
		uac.setCampaign("campaign");
		uac.setDevice("device");
		uac.setIp("ip");
		uac.setReferrer("referrer");
		uac.setReferrerName("referrerName");
		uac.setTimestamp(new Date());
		uac.setUserAgent("userAgent");
		uac.setUserId("userId");
		uac.setUserIdType(UserIdType.IP);

		PageView pageView = new PageView();
		pageView.setUserActionContext(uac);
		pageView.setActionType(UserActionType.PAGE_VIEW);
		pageView.setQueryString("queryString");
		pageView.setUri("uri");

		ActionContext actionContext = BeanMapper.mapObjectByType(pageView.getUserActionContext(),
				ActionContext.class);
		ActionSpecInfo actionSpecInfo = BeanMapper.mapObjectByType(pageView, ActionSpecInfo.class);

		UserActionRollUp newRollUp = new UserActionRollUp();
		BeanMapper.mapObject(actionContext, newRollUp);
		BeanMapper.mapObject(actionSpecInfo, newRollUp);

		newRollUp.setDataId(DataDigester.digest(newRollUp.usageTrait()));
		
		System.out.println(newRollUp.toString());
	}

}
