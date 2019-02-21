package model.account;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of Account Interface.
 *
 */
public final class AccountImpl implements Account {

    private final String username;
    private Optional<String> nickname;
    private String password;
    private Optional<List<Integer>> topScores;

    private AccountImpl(final String username, final Optional<String> nickname, final String password, final Optional<List<Integer>> topScores) {
        if (username == null || password == null) {
            throw new IllegalArgumentException();
        }
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.topScores = topScores;
    }

    /** 
     * Create a simple Account.
     * @param username the account username
     * @param password the account password
     * @return the Account
     */
    public static Account createSimpleAccount(final String username, final String password) {
        return new AccountImpl(username, Optional.empty(), password, Optional.empty());
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
        return new AccountImpl(username, Optional.of(nickname), password, Optional.empty());
    }

    /**
     * Create a complete Account.
     * @param username the account username
     * @param password the account password
     * @param nickname the account nickname
     * @param topScores the account topScores
     * @return the Account
     */
    public static Account createCompleteAccount(final String username, final String password, final String nickname, final List<Integer> topScores) {
        if (nickname == null || topScores == null) {
            throw new IllegalArgumentException();
        }
        return new AccountImpl(username, Optional.of(nickname), password, Optional.of(topScores));
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
    public Optional<String> getNickname() {
        return this.nickname;
    }

    /**
     * {@inheritDoc}
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Set the nickname.
     * @param nickname the nickname to set
     */
    public void setNickname(final String nickname) {
        if (nickname == null) {
            throw new IllegalArgumentException();
        }
        this.nickname = Optional.of(nickname);
    }

    /**
     * Set the password.
     * @param password the password to set
     */
    public void setPassword(final String password) {
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
