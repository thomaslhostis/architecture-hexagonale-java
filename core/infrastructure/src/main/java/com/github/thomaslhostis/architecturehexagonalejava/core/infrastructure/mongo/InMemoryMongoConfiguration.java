package com.github.thomaslhostis.architecturehexagonalejava.core.infrastructure.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.net.InetSocketAddress;

@Configuration
public class InMemoryMongoConfiguration {

    private static final String DATABASE_NAME = "test";

    @Bean
    public String mongoUri(MongoServer mongoServer) {
        InetSocketAddress serverAddress = mongoServer.getLocalAddress();
        return "mongodb://" + serverAddress.getHostName() + ":" + serverAddress.getPort() + "/" + DATABASE_NAME;
    }

    @Bean
    public MongoDatabaseFactory mongoDbFactory(String mongoUri) {
        return new SimpleMongoClientDatabaseFactory(mongoUri);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }

    @Bean(destroyMethod = "shutdown")
    public MongoServer mongoServer() {
        MongoServer mongoServer = new MongoServer(new MemoryBackend());
        mongoServer.bind();
        return mongoServer;
    }

    @Bean
    public MongoClient mongoClient(String mongoUri) {
        ConnectionString connectionString = new ConnectionString(mongoUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }
}
