package io.sdkman.broker;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

@Singleton
class MongoProvider {

    private final MongoConfig mongoConfig;

    @Inject
    public MongoProvider(MongoConfig mongoConfig) {
        this.mongoConfig = mongoConfig;
    }

    public MongoDatabase database() throws Exception {
        return mongo().getDatabase(mongoConfig.getDbName());
    }

    private MongoClient mongo() throws Exception {
        ServerAddress serverAddress = new ServerAddress(mongoConfig.getHost(), mongoConfig.getPort());
        if (mongoConfig.getUsername() != null && mongoConfig.getPassword() != null) {
            MongoCredential credential = MongoCredential.createCredential(
                    mongoConfig.getUsername(),
                    mongoConfig.getDbName(),
                    mongoConfig.getPassword().toCharArray());
            List credentials = new ArrayList() {{
                add(credential);
            }};

            return new MongoClient(serverAddress, credentials);
        } else {
            return new MongoClient(serverAddress);
        }
    }
}
