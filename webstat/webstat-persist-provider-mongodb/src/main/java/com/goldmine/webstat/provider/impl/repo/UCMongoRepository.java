package com.goldmine.webstat.provider.impl.repo;

import com.mongodb.BasicDBObject;

public abstract class UCMongoRepository<T extends BasicDBObject> extends
		MongoRepository<T> {

	@Override
	protected void setDBName() {
		this.dbName = "webstat";
	}

}
