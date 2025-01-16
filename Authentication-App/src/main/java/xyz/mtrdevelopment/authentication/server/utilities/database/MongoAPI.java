package xyz.mtrdevelopment.authentication.server.utilities.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import xyz.mtrdevelopment.authentication.server.utilities.database.credentials.MongoCredentials;

import java.util.Collections;

@Getter
public class MongoAPI {

    private final MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private final MongoCredentials credentials;

    public MongoAPI(MongoCredentials credentials) {
        this.credentials = credentials;

        if (credentials.isAuth()) {
            MongoCredential credential = MongoCredential.createCredential(credentials.getUser(), credentials.getDatabase(), credentials.getPassword().toCharArray());
            mongoClient = new MongoClient(new ServerAddress(credentials.getHost(), credentials.getPort()), Collections.singletonList(credential));
        } else {
            mongoClient = new MongoClient(credentials.getHost(), credentials.getPort());
        }
        mongoDatabase = mongoClient.getDatabase(credentials.getDatabase());

    }
}