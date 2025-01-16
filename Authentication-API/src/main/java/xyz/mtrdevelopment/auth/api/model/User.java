package xyz.mtrdevelopment.auth.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.UUID;

/**
 * User is a part of AuthenticationSystem
 * Which was created / maintained by
 *
 * @author MTR
 */

@Data
public class User {

    /**
     * User class is being saved as a JSON object in a MongoDB (Or any other database) server
     * It's content is just to define each user's E-Mail, Username and Password
     */

    @SerializedName("_id")
    private final UUID uuid;
    private final String email, username, password;
}