package com.goldmine.webstat.web;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldmine.webstat.bean.ServerHost;
import com.goldmine.webstat.bean.URLComponent;
import com.goldmine.webstat.model.UserActionContext;
import com.goldmine.webstat.service.TrafficLogService;
import com.goldmine.webstat.utils.FileUtils;
import com.goldmine.webstat.utils.URLUtils;

@Controller
@RequestMapping(value = "api")
public class StatAPIController extends BaseController {

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private URLUtils urlUtils;

	@Autowired
	private TrafficLogService trafficLogService;

	@RequestMapping(value = "statScript", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getStatScript(ServerHost serverHost,
			@RequestParam(required = false, defaultValue = "false") Boolean logPv,
			@RequestParam(required = true) String appId) {
		StringBuilder script = new StringBuilder();

		script.append(fileUtils.readFileAsString("stat_api.js"));
		if (logPv) {
			script.append(fileUtils.readFileAsString("report_pv.js"));
		}

		String hostChanged = StringUtils.replace(script.toString(), "${host}",
				serverHost.getAddress());
		return StringUtils.replace(hostChanged, "${appId}", appId);
	}

	@RequestMapping(value = "logPv", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String logPageView(UserActionContext userActionContext,
			@RequestParam(value = "_referer", required = false) String pageReferer,
			@RequestParam(required = true) String appId) {
		// 获取引用脚本的网页地址
		String pageUrl = userActionContext.getReferrer();
		URLComponent component = urlUtils.extractComponent(pageUrl);

		// 记录用户行为
		this.configContextParameters(userActionContext, component.getQueryString());
		userActionContext.setReferrer(pageReferer);
		userActionContext.setAppId(appId);
		trafficLogService.logPageView(userActionContext, component.getUri(),
				component.getQueryString());
		return "";
	}

	@RequestMapping(value = "customEvent/{event}", method = { RequestMethod.GET, RequestMethod.POST }, produces = "text/javascript")
	@ResponseBody
	public byte[] logCustomEvent(UserActionContext userActionContext, @PathVariable String event,
			@RequestParam(required = true) String appId,
			@RequestParam(required = true) String pageName,
			@RequestParam(required = false) String vars) {
		userActionContext.setAppId(appId);
		userActionContext.setReferrerName(pageName);

		String pageUrl = userActionContext.getReferrer();
		URLComponent component = urlUtils.extractComponent(pageUrl);

		this.configContextParameters(userActionContext, component.getQueryString());

		trafficLogService.logUseFunction(userActionContext, event, vars);
		return fileUtils.readFileAsBytes("stat_dummy.png");
	}

	/**
	 * <p>
	 * 从queryString获取campaign和adTag参数信息
	 * </p>
	 * 
	 * @param userActionContext
	 * @param queryString
	 */
	private void configContextParameters(UserActionContext userActionContext, String queryString) {
		Map<String, String> parameters = urlUtils.extractParameters(queryString);
		userActionContext.setAdTag(parameters.get(PARA_AD_TAG));
		userActionContext.setCampaign(parameters.get(PARA_CAMPAIGN));
	}

}
