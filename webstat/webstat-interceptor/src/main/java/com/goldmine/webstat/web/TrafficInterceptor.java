package com.goldmine.webstat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.goldmine.webstat.model.PageView;
import com.goldmine.webstat.model.UserActionContext;
import com.goldmine.webstat.provider.TrafficLogPersistServiceProvider;
import com.goldmine.webstat.utils.ParamExtractor;

public class TrafficInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TrafficLogPersistServiceProvider trafficLogPersistServiceProvider;

	private String[] pageSuffix;

	private String appId;

	public String[] getPageSuffix() {
		return pageSuffix;
	}

	public void setPageSuffix(String[] pageSuffix) {
		this.pageSuffix = pageSuffix;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		String uri = request.getRequestURI();
		if (this.matches(uri)) {
			UserActionContext userActionContext = ParamExtractor.extractUserActionContext(request);
			userActionContext.setAppId(appId);
			trafficLogPersistServiceProvider.logPageView(this.buildPageView(userActionContext,
					this.buildFullUri(request), request.getQueryString()));
		}
		return true;
	}

	private String buildFullUri(HttpServletRequest request) {
		StringBuilder fullUri = new StringBuilder(request.getScheme()).append("://").append(
				request.getServerName());
		if (request.getServerPort() != 80) {
			fullUri.append(":").append(request.getServerPort());
		}
		fullUri.append(request.getRequestURI());
		return fullUri.toString();
	}

	private boolean matches(String uri) {
		if (pageSuffix == null) {
			return false;
		} else {
			for (String suffix : pageSuffix) {
				if (uri.endsWith(suffix)) {
					return true;
				}
			}
			return false;
		}
	}

	private PageView buildPageView(UserActionContext userActionContext, String uri,
			String queryString) {
		PageView pageView = new PageView();
		pageView.setUserActionContext(userActionContext);
		pageView.setUri(uri);
		pageView.setQueryString(queryString);
		return pageView;
	}

}
