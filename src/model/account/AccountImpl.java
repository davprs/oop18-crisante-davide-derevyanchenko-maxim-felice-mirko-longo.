package model.account;

/**
 * Implementation of Account Interface.
 *
 */
public final class AccountImpl implements Account {

    private final String username;
    private String nickname;
    private String password;
    private final int bestScore;
    private final Settings settings;

    private AccountImpl(final String username, final String nickname, final String password, final int bestScore, final Settings settings) {
        if (username == null || password == null) {
            throw new IllegalArgumentException();
        }
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.bestScore = bestScore;
        this.settings = settings;
    }

    /** 
     * Create a simple Account.
     * @param username the account username
     * @param password the account password
     * @return the Account
     */
    public static Account createSimpleAccount(final String username, final String password) {
        return new AccountImpl(username, username, password, 0, Settings.DEFAULT);
    }

    /**
     * Create an Account with nickname.
     * @param username the account username
     * @param password the account password
     * @param nickname the account nickname
     * @return the Account
     */
    public static Account createAccountWithNickname(final String username, final String password, final String nickname) {
        if (nickname == null) {
            throw new IllegalArgumentException();
        }
        return new AccountImpl(username, nickname, password, 0, Settings.DEFAULT);
    }

    /**
     * Create a complete Account.
     * @param username the account username
     * @param password the account password
     * @param nickname the account nickname
     * @param bestScore the account topScores
     * @param settings the account settings
     * @return the Account
     */
    public static Account createCompleteAccount(final String username, final String password, final String nickname, final int bestScore, final Settings settings) {
        if (nickname == null || bestScore < 0) {
            throw new IllegalArgumentException();
        }
        return new AccountImpl(username, nickname, password, bestScore, settings);
    }

    /**
     * {@inheritDoc}
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * {@inheritDoc}
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * {@inheritDoc}
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * {@inheritDoc}
     */
    public int getBestScore() {
        return this.bestScore;
    }

    /**
     * {@inheritDoc}
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Set the nickname.
     * @param nickname the nickname to set
     */
    public void setNickname(final String nickname) {
        if (nickname == null) {
            throw new IllegalArgumentException();
        }
        this.nickname = nickname;
    }

    /**
     * Set the password.
     * @param password the password to set
     */
    public void setPassword(final String password) {
        if (password == null) {
            throw new IllegalArgumentException();
        }
        this.password = password;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AccountImpl)) {
            return false;
        }
        final AccountImpl other = (AccountImpl) obj;
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AccountImpl [username=" + username + ", nickname=" + nickname + ", password=" + password + "]";
    }

}
