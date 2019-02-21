package model.account;

import java.util.Optional;

/**
 * Represents a Game Account.
 *
 */
public interface Account {

    /**
     * Get the username.
     * 
     * @return the account username
     */
    String getUsername();

    /**
     * Get the nickname(optional).
     * 
     * @return the account nickname
     */
    Optional<String> getNickname();

    /**
     * Get the password.
     * 
     * @return the account password
     */
    String getPassword();
}
