package com.goldmine.webstat.computation.repo.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.goldmine.webstat.computation.utils.BeanMapper;

public class WebstatRepositoryImpl<T, K extends Serializable> extends BaseRepositoryImpl<T, K> {

	@Autowired
	public void init(@Qualifier("webstatSessionFactory") SessionFactory factory) {
		setSessionFactory(factory);
	}

	protected static String buildConditionQuery(String prefix, Object condition) {
		StringBuilder builder = new StringBuilder();

		Map<String, Object> map = BeanMapper.toMap(condition);
		for (Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != null) {
				builder.append(" and ").append(prefix).append(".").append(entry.getKey())
						.append(" = '").append(entry.getValue()).append("'");
			}
		}

		return builder.toString();
	}

	protected T returnFirst(List<T> result) {
		return result.isEmpty() ? null : result.get(0);
	}

}
