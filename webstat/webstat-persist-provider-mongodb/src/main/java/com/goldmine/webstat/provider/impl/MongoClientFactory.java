package com.goldmine.webstat.provider.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Component("mongoClientFactory")
public class MongoClientFactory {

	@Autowired
	@Qualifier("mongodbs")
	private ArrayList<ServerAddress> mongodbs;

	public MongoClient createMongoClient() {
		return new MongoClient(mongodbs);
	}

}
