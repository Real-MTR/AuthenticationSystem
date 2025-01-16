package xyz.mtrdevelopment.authentication.server.implementations;

import com.mongodb.client.model.Filters;
import xyz.mtrdevelopment.auth.api.manager.IUserManager;
import xyz.mtrdevelopment.auth.api.model.User;
import xyz.mtrdevelopment.authentication.server.Authentication;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * UserManager is a part of AuthenticationSystem
 * Which was created / maintained by
 *
 * @author MTR
 */

public class UserManager implements IUserManager {

    private final Authentication instance;
    private final Map<UUID, User> userMap = new ConcurrentHashMap<>();

    public UserManager(Authentication instance) {
        this.instance = instance;

        instance.getUserRepository().getAll(User.class).join().forEach(userCompletableFuture -> {
            try {
                User user = userCompletableFuture.get();

                userMap.putIfAbsent(user.getUuid(), user);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public User getByUUID(UUID uuid) {
        return userMap.get(uuid);
    }

    @Override
    public User getByUsername(String username) {
        return CompletableFuture.supplyAsync(() -> userMap.values().stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null)).join();
    }

    @Override
    public User getByEmail(String email) {
        return CompletableFuture.supplyAsync(() -> userMap.values().stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null)).join();
    }

    @Override
    public void save(User user) {
        userMap.putIfAbsent(user.getUuid(), user);
        instance.getUserRepository().saveData(user.getUuid().toString(), user);
    }

    @Override
    public void delete(User user) {
        CompletableFuture.runAsync(() -> {
            userMap.remove(user.getUuid());
            instance.getUserRepository().getCollection().deleteOne(Filters.eq("_id", user.getUuid().toString()));
        }).join();
    }

    @Override
    public List<User> getUsers() {
        return userMap.values().stream().toList();
    }
}
