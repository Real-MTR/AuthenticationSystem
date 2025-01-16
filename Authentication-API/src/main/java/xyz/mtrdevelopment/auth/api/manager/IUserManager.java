package xyz.mtrdevelopment.auth.api.manager;

import xyz.mtrdevelopment.auth.api.model.User;

import java.util.List;
import java.util.UUID;

/**
 * IUserManager is a part of AuthenticationSystem
 * Which was created / maintained by
 *
 * @author MTR
 */

public interface IUserManager {

    /**
     * @param uuid is used to filter which User you want to get
     * @return returning the user you are trying to look for. If it doesn't exist, it will return as null!
     */
    User getByUUID(UUID uuid);

    /**
     * @param username is used to filter which User you want to get
     * @return returning the user you are trying to look for. If it doesn't exist, it will return as null!
     */
    User getByUsername(String username);

    /**
     * @param email is used to filter which User you want to get
     * @return returning the user you are trying to look for. If it doesn't exist, it will return as null!
     */
    User getByEmail(String email);

    /**
     * Saving the user in the database as a JSON Object
     * @param user is used to define which user am I trying to save
     */
    void save(User user);

    /**
     * Deleting a user from the database
     * @param user is used to define which user am I trying to delete from the database
     */
    void delete(User user);

    /**
     * @return all the users in the cache
     */
    List<User> getUsers();
}