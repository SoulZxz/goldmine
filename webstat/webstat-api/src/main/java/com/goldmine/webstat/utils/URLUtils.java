package com.goldmine.webstat.utils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.stereotype.Component;

import com.goldmine.webstat.bean.URLComponent;

@Component
public class URLUtils {

	public URLComponent extractComponent(String url) {
		if (StringUtils.isNotBlank(url)) {
			String[] segs = url.split("\\?");
			String uri = segs[0];
			String queryString = segs.length >= 2 ? segs[1] : null;

			return new URLComponent(uri, queryString);
		} else {
			return new URLComponent(url, null);
		}
	}

	public Map<String, String> extractParameters(String queryString) {
		Map<String, String> parameters = new HashMap<String, String>();

		List<NameValuePair> pairs = URLEncodedUtils.parse(queryString, Charset.forName("utf-8"));
		for (NameValuePair pair : pairs) {
			parameters.put(pair.getName(), pair.getValue());
		}
		return parameters;
	}
}
