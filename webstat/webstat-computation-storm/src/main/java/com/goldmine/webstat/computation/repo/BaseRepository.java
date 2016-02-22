package com.goldmine.webstat.computation.repo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 通用数据访问接口
 * </p>
 * 
 * 提供一般的CRUD操作定义
 * 
 * @author zhaoxuanzhang 2015-12-12
 * @param <T>
 *            数据类型
 * @param <K>
 *            数据主键类型
 */
public interface BaseRepository<T, K extends Serializable> {

	void save(T t);

	T get(K id);

	void remove(T t);

	List<T> find(String hql, Object... params);
}
