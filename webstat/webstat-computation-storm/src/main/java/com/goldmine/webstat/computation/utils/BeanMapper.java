package com.goldmine.webstat.computation.utils;

import java.util.Map;

import org.dozer.DozerBeanMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BeanMapper {

	private static ObjectMapper mapper = new ObjectMapper();

	private static DozerBeanMapper dozerMapper = new DozerBeanMapper();

	/**
	 * 把源对象的属性复制到新的目标对象
	 * 
	 * @param src
	 *            源对象
	 * @param destClazz
	 *            目标对象的类型
	 * @return 新的目标对象
	 */
	public static <T> T mapObjectByType(Object src, Class<T> destClazz) {
		return dozerMapper.map(src, destClazz);
	}

	/**
	 * 把源对象的属性复制到目标对象
	 * 
	 * @param src
	 *            源对象
	 * @param dest
	 *            目标对象
	 */
	public static void mapObject(Object src, Object dest) {
		dozerMapper.map(src, dest);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(Object obj) {
		return mapper.convertValue(obj, Map.class);
	}

}
