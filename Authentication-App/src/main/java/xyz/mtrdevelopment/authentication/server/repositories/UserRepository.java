package xyz.mtrdevelopment.authentication.server.repositories;

import com.google.gson.Gson;
import xyz.mtrdevelopment.auth.api.model.User;
import xyz.mtrdevelopment.authentication.server.utilities.database.MongoAPI;
import xyz.mtrdevelopment.authentication.server.utilities.database.repository.MongoRepository;

/**
 * UserRepository is a part of AuthenticationSystem
 * Which was created / maintained by
 *
 * @author MTR
 */

public class UserRepository extends MongoRepository<User> {

    public UserRepository(MongoAPI mongoAPI, Gson gson) {
        super(mongoAPI, gson);
        setCollection(mongoAPI.getMongoDatabase().getCollection("authentication-users"));
    }
}
