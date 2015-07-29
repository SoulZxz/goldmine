package com.goldmine.webstat.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.goldmine.webstat.bean.ServerHost;
import com.goldmine.webstat.model.UserActionContext;
import com.goldmine.webstat.model.UserIdType;

/**
 * <p>
 * 基础控制器，系统错误屏障，提供通用的异常 -> http响应 映射处理
 * </p>
 * 
 * @author zhaoxuanzhang 2013-09-12
 */
public abstract class BaseController {

	public static final String TEXT_HTML_TYPE = "text/html; charset=UTF-8";

	private final static Logger LOG = LoggerFactory.getLogger(BaseController.class);

	public static final String PROP_contextPath = "contextPath";

	private static final String EN_UTF8 = "UTF-8";

	public static final String PARA_CAMPAIGN = "_campaign";

	public static final String PARA_AD_TAG = "_adTag";

	public static final String PARA_PAGE_NAME = "_pageName";

	@Autowired
	@Qualifier("serverHost")
	private String serverAddress;

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleThrowable(Throwable ex) {
		LOG.error(ex.toString(), ex);
		informAdmin(ex);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		return new ResponseEntity<String>(ex.getMessage(), responseHeaders,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleInvalidInput(IllegalArgumentException ex) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		return new ResponseEntity<String>(ex.getMessage(), responseHeaders, HttpStatus.BAD_REQUEST);
	}

	private void informAdmin(Throwable ex) {
		// TODO 发送错误报告
		ex.printStackTrace();
	}

	protected String getRequestBody(HttpServletRequest request) throws IOException {
		InputStream isBody = request.getInputStream();
		String body = IOUtils.toString(isBody, EN_UTF8);
		return body;
	}

	@ModelAttribute
	public UserActionContext createUserActionContext(HttpServletRequest request,
			@RequestHeader(value = "REFERER", required = false) String referer,
			@RequestHeader(value = "User-Agent", required = false) String userAgent,
			@RequestHeader(value = "x-forwarded-for", required = false) String clientIp,
			@CookieValue(value = "openId", required = false) String openId,
			@RequestParam(value = PARA_CAMPAIGN, required = false) String campaign,
			@RequestParam(value = PARA_AD_TAG, required = false) String adTag,
			@RequestParam(value = PARA_PAGE_NAME, required = false) String pageName) {
		UserActionContext userActionContext = new UserActionContext();
		userActionContext.setReferrer(referer);
		userActionContext.setReferrerName(pageName);
		userActionContext.setUserAgent(userAgent);
		userActionContext.setTimestamp(new Date());
		userActionContext.setCampaign(campaign);
		userActionContext.setAdTag(adTag);

		// reverse proxy header first
		String effectiveClientIp = this.getEffectiveClientIp(request.getRemoteAddr(), clientIp);

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

	@ModelAttribute
	public ServerHost getServerHost() {
		ServerHost serverHost = new ServerHost();
		serverHost.setAddress(serverAddress);
		return serverHost;
	}

	private String getEffectiveClientIp(String remoteAddr, String clientIp) {
		if (StringUtils.isNotBlank(clientIp)) {
			return clientIp;
		} else {
			return remoteAddr;
		}
	}

}
