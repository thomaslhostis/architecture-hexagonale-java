package com.architecturehexagonale.tests.functionaltests.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestDatabaseCleaner {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void dropCollections() {
        mongoTemplate.getCollectionNames().forEach(collectionName ->
                mongoTemplate.getCollection(collectionName).drop()
        );
    }
}
