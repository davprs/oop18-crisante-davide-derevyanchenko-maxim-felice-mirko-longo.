package model.account;

/**
 * Represents a Game Account.
 *
 */
public interface Account {

    /**
     * Get the username.
     * @return the account username
     */
    String getUsername();

    /**
     * Get the nickname.
     * @return the account nickname
     */
    String getNickname();

    /**
     * Get the password.
     * @return the account password
     */
    String getPassword();

    /**
     * Get the int value of the best Score. 
     * @return the best Score
     */
    int getBestScore();

    /**
     * Get the Settings.
     * @return the Settings.
     */
    Settings getSettings();
}
