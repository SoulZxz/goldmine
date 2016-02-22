package com.goldmine.webstat.computation.support;

import java.util.Date;
import java.util.Random;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.goldmine.webstat.computation.provider.FactDataProvider;
import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UseFunction;
import com.goldmine.webstat.model.UserActionContext;
import com.goldmine.webstat.model.UserActionType;
import com.goldmine.webstat.model.UserIdType;

@Profile("test")
@Component
public class DummyRandomProvider  implements FactDataProvider {

	@Override
	public PageView receivePageView() {
		String[] adTags = new String[] { "adTag1", "adTag2" };
		String[] appIds = new String[] { "appId1", "appId2" };
		String[] campaigns = new String[] { "campaign1", "campaign2" };
		String[] devices = new String[] { "device1", "device2" };
		String[] ips = new String[] { "ip1", "ip2" };
		String[] referrers = new String[] { "referrer1", "referrer2" };
		String[] referrerNames = new String[] { "referrerName1", "referrerName2" };
		String[] userAgents = new String[] { "userAgent1", "userAgent2" };
		String[] userIds = new String[] { "userId1", "userId2" };
		String[] queryStrings = new String[] { "queryString1", "queryString2" };
		String[] uris = new String[] { "uri1", "uri2" };

//		String[] adTags = new String[] { "adTag1" };
//		String[] appIds = new String[] { "appId1" };
//		String[] campaigns = new String[] { "campaign1" };
//		String[] devices = new String[] { "device1" };
//		String[] ips = new String[] { "ip1" };
//		String[] referrers = new String[] { "referrer1" };
//		String[] referrerNames = new String[] { "referrerName1" };
//		String[] userAgents = new String[] { "userAgent1" };
//		String[] userIds = new String[] { "userId1" };
//		String[] queryStrings = new String[] { "queryString1" };
//		String[] uris = new String[] { "uri1" };
		
		Random rand = new Random();

		UserActionContext uac = new UserActionContext();
		uac.setAdTag(pickRandom(adTags, rand));
		uac.setAppId(pickRandom(appIds, rand));
		uac.setCampaign(pickRandom(campaigns, rand));
		uac.setDevice(pickRandom(devices, rand));
		uac.setIp(pickRandom(ips, rand));
		uac.setReferrer(pickRandom(referrers, rand));
		uac.setReferrerName(pickRandom(referrerNames, rand));
		uac.setTimestamp(new Date());
		uac.setUserAgent(pickRandom(userAgents, rand));
		uac.setUserId(pickRandom(userIds, rand));
		uac.setUserIdType(UserIdType.IP);

		PageView pageView = new PageView();
		pageView.setUserActionContext(uac);
		pageView.setActionType(UserActionType.PAGE_VIEW);
		pageView.setQueryString(pickRandom(queryStrings, rand));
		pageView.setUri(pickRandom(uris, rand));

		return pageView;
	}

	@Override
	public UseFunction receiveUseFunction() {
		// TODO Auto-generated method stub
		return new UseFunction();
	}

	private String pickRandom(String[] options, Random rand) {
		return options[rand.nextInt(options.length)];
	}

}
