package xyz.mtrdevelopment.auth.api;

import lombok.Getter;
import lombok.Setter;
import xyz.mtrdevelopment.auth.api.manager.IUserManager;

/**
 * AuthenticationAPI is a part of AuthenticationSystem
 * Which was created / maintained by
 *
 * @author MTR
 */

@Getter @Setter
public class AuthenticationAPI {

    @Getter private static AuthenticationAPI instance;

    private IUserManager userManager;

    public AuthenticationAPI() {
        instance = this;
    }
}
