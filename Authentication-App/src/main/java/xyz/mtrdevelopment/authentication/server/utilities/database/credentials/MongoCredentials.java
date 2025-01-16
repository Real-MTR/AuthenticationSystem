package xyz.mtrdevelopment.authentication.server.utilities.database.credentials;

import lombok.Data;

@Data
public class MongoCredentials {
    private final String host, database, user, password;
    private final int port;
    private final boolean auth;
}