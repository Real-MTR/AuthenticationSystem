package xyz.mtrdevelopment.authentication.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import lombok.Getter;
import xyz.mtrdevelopment.auth.api.AuthenticationAPI;
import xyz.mtrdevelopment.authentication.server.implementations.UserManager;
import xyz.mtrdevelopment.authentication.server.repositories.UserRepository;
import xyz.mtrdevelopment.authentication.server.utilities.database.MongoAPI;
import xyz.mtrdevelopment.authentication.server.utilities.database.credentials.MongoCredentials;

/**
 * Authentication is a part of AuthenticationSystem
 * Which was created / maintained by
 *
 * @author MTR
 */

@Getter
public class Authentication {

    private final AuthenticationAPI api;

    private final MongoAPI mongoAPI;
    private final Gson gson;

    private final UserRepository userRepository;
    private final UserManager userManager;

    public Authentication() {
        this.api = new AuthenticationAPI();

        this.mongoAPI = new MongoAPI(new MongoCredentials("localhost", "Authentication", null, null, 27017, false));
        this.gson = new GsonBuilder()
                .serializeNulls()
                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .setPrettyPrinting()
                .create();

        this.userRepository = new UserRepository(mongoAPI, gson);
        this.userManager = new UserManager(this);
    }
}