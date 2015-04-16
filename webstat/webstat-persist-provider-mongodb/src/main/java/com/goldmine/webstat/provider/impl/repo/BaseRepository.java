package com.goldmine.webstat.provider.impl.repo;

import java.util.List;

import com.mongodb.BasicDBObject;

/**
 * <p>
 * 通用数据访问接口
 * </p>
 * 
 * 提供一般的CRUD操作定义
 * 
 * @author zhaoxuanzhang 2013-9-12
 * @param <T>
 *            数据类型
 * @param <K>
 *            数据主键类型
 */
public interface BaseRepository<T extends BasicDBObject> {

	void save(T t);

	T get(BasicDBObject query);

	void remove(T t);

	List<T> listAll();

	List<T> find(BasicDBObject query);

	List<T> find(BasicDBObject query, BasicDBObject keys);

	void bulkSave(List<T> ts);

}
