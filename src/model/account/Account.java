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

    /**
     * Set the nickname.
     * @param nickname the nickname to set
     */
    void setNickname(String nickname);

    /**
     * Set the password.
     * @param password the password to set
     */
    void setPassword(String password);

    /**
     * Set the bestScore.
     * @param bestScore the bestScore to set
     */
    void setBestScore(int bestScore);

    /**
     * Set the Settings.
     * @param settings the settings to set
     */
    void setSettings(Settings settings);
}
