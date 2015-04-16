package com.goldmine.webstat.provider.impl.repo;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

@Component
public abstract class MongoRepository<T extends BasicDBObject> implements
		BaseRepository<T> {

	protected String dbName;

	protected DBCollection collection;

	@Autowired
	protected MongoClient mongoClient;

	protected abstract void setDBName();

	protected abstract void initIndex();

	public MongoRepository() {
		setDBName();
	}

	@PostConstruct
	public void init() {
		collection = getDB().getCollection(resolveClass().getSimpleName());
		collection.setObjectClass(resolveClass());
		initIndex();
	}

	protected DB getDB() {
		return mongoClient.getDB(dbName);
	}

	@Override
	public void save(T t) {
		collection.save(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(BasicDBObject query) {
		return (T) collection.findOne(query);
	}

	@Override
	public void remove(T t) {
		collection.remove(t);
	}

	@Override
	public List<T> listAll() {
		DBCursor cursor = collection.find();
		return toList(cursor);
	}

	@Override
	public List<T> find(BasicDBObject query) {
		DBCursor cursor = collection.find(query);
		return toList(cursor);
	}

	@Override
	public List<T> find(BasicDBObject query, BasicDBObject keys) {
		DBCursor cursor = collection.find(query, keys);
		return toList(cursor);
	}

	@Override
	public void bulkSave(List<T> ts) {
		BulkWriteOperation builder = collection
				.initializeUnorderedBulkOperation();
		for (T t : ts) {
			builder.insert(t);
		}
		builder.execute();
	}

	@SuppressWarnings("unchecked")
	private List<T> toList(DBCursor cursor) {
		List<T> result = new ArrayList<T>();
		try {
			while (cursor.hasNext()) {
				result.add((T) cursor.next());
			}
		} finally {
			cursor.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	protected Class<T> resolveClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
