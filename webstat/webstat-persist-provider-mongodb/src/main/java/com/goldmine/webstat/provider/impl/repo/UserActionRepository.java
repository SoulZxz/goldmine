package com.goldmine.webstat.provider.impl.repo;

import org.springframework.stereotype.Repository;

import com.goldmine.webstat.provider.impl.domain.MongoUserAction;
import com.mongodb.BasicDBObject;

@Repository
public class UserActionRepository extends UCMongoRepository<MongoUserAction> {

	@Override
	protected void initIndex() {
		collection.createIndex(new BasicDBObject("actionType", 1));
		collection.createIndex(new BasicDBObject("digest", 1));
		collection.createIndex(new BasicDBObject("userActionContext.timestamp",
				1));
	}

}
