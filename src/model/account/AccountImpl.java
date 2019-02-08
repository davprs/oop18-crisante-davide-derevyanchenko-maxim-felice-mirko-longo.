package model.account;

/**
 * Implementation of Account Interface.
 *
 */
public class AccountImpl implements Account {

    private final String username;
    private String nickname;
    private String password;

    /**
     * Build a complete Account.
     * @param username the Account username
     * @param nickname the Account nickname
     * @param password the Account password
     */
    public AccountImpl(final String username, final String nickname, final String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }

    /**
     * Build a simple Account.
     * @param username the Account username
     * @param password the Account password
     */
    public AccountImpl(final String username, final String password) {
        this(username, null, password);
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    /**
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
