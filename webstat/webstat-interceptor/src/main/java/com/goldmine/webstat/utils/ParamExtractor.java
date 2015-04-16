package com.goldmine.webstat.utils;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.goldmine.webstat.model.UserActionContext;
import com.goldmine.webstat.model.UserIdType;

public class ParamExtractor {

	public static UserActionContext extractUserActionContext(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		String referer = request.getHeader("REFERER");
		String userAgent = request.getHeader("User-Agent");
		String campaign = request.getParameter("_campaign");
		String adTag = request.getParameter("_adTag");
		String openId = findCookieValue(cookies, "openId");
		String clientIp = request.getParameter("x-forwarded-for");

		UserActionContext userActionContext = new UserActionContext();
		userActionContext.setReferrer(referer);
		userActionContext.setUserAgent(userAgent);
		userActionContext.setTimestamp(new Date());
		userActionContext.setCampaign(campaign);
		userActionContext.setAdTag(adTag);

		String effectiveClientIp = getEffectiveClientIp(request.getRemoteAddr(), clientIp);

		// setup User Id, use openId if availiable
		if (StringUtils.isNotBlank(openId)) {
			userActionContext.setUserIdType(UserIdType.WEIXIN_OPENID);
			userActionContext.setUserId(openId);
		} else {
			userActionContext.setUserIdType(UserIdType.IP);
			userActionContext.setUserId(effectiveClientIp);
		}
		userActionContext.setIp(effectiveClientIp);
		return userActionContext;
	}

	private static String findCookieValue(Cookie[] cookies, String target) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(target)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	private static String getEffectiveClientIp(String remoteAddr, String clientIp) {
		if (StringUtils.isNotBlank(clientIp)) {
			return clientIp;
		} else {
			return remoteAddr;
		}
	}
}
