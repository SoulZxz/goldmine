package com.goldmine.webstat.viewer.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldmine.webstat.viewer.bean.DataWrapper;
import com.goldmine.webstat.viewer.domain.UserActionFact;
import com.goldmine.webstat.viewer.service.DataService;

@Controller
@RequestMapping(value = "data")
public class DataServiceController {

	@Autowired
	private DataService dataService;

	@RequestMapping(value = "facts/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataWrapper<Integer> getFactsCount(@RequestParam String actionType,
			@RequestParam(required = false) String function,
			@RequestParam(required = false) String appId,
			@RequestParam(required = false) String campaign,
			@RequestParam(required = false) String adTag) {
		DataWrapper<Integer> result = new DataWrapper<Integer>();
		List<UserActionFact> facts = dataService.findUserActionFacts(null, null, actionType,
				function, appId, campaign, adTag);
		result.setData(facts.size());
		return result;
	}
}
