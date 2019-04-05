package model.account;

import java.util.Optional;

/**
 * Implementation of Account Interface.
 *
 */
public final class AccountImpl implements Account {  // NOPMD by Mirko on 04/04/19 16.39 because is a false positive

    private final String username;
    private String password;
    private String nickname;
    private int bestScore;
    private Settings settings;

    private AccountImpl(final String username, final String nickname, final String password, final int bestScore, final Settings settings) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.bestScore = bestScore;
        this.settings = settings;
    }

    /**
     * Builder class for Account.
     *
     */
    public static class Builder {

        private final String username;
        private final String password;
        private Optional<String> nick = Optional.empty();
        private int score;
        private Optional<Settings> setting = Optional.empty();

        /**
         * Build the initial Builder.
         * @param username the necessary username
         * @param password the necessary password
         */
        public Builder(final String username, final String password) {
            if (username == null || password == null) {
                throw new IllegalArgumentException();
            }
            this.username = username;
            this.password = password;
        }

        /**
         * Set the nickname.
         * @param nickname the nickname to set
         * @return the Builder
         */
        public Builder withNickname(final String nickname) {
            this.nick = Optional.ofNullable(nickname);
            return this;
        }

        /**
         * Set the bestScore.
         * @param bestScore the bestScore to set
         * @return the Builder
         */
        public Builder bestScore(final int bestScore) {
            if (bestScore < 0) {
                throw new IllegalStateException();
            }
            this.score = bestScore;
            return this;
        }

        /**
         * Set the settings.
         * @param settings the settings to set
         * @return the Builder
         */
        public Builder addMySettings(final Settings settings) {
            this.setting = Optional.ofNullable(settings);
            return this; 
        }

        /**
         * Build the Account.
         * @return the account
         */
        public AccountImpl build() {
            if (this.nick.isPresent() && !this.nick.get().equals("")) {
                if (!this.setting.isPresent()) {
                    return new AccountImpl(this.username, this.nick.get(), this.password, this.score, SettingsImpl.DEFAULT);
                } else {
                    return new AccountImpl(this.username, this.nick.get(), this.password, this.score, this.setting.get());
                }
            } else {
                if (!this.setting.isPresent()) {
                    return new AccountImpl(this.username, this.username, this.password, this.score, SettingsImpl.DEFAULT);
                } else {
                    return new AccountImpl(this.username, this.username, this.password, this.score, this.setting.get());
                }
            }
        }
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
        return this.settings;
    }

    /**
     * {@inheritDoc}
     */
    public void setNickname(final String nickname) {
        if (nickname == null) {
            throw new IllegalArgumentException();
        }
        this.nickname = nickname;
    }

    /**
     * {@inheritDoc}
     */
    public void setPassword(final String password) {
        if (password == null) {
            throw new IllegalArgumentException();
        }
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBestScore(final int bestScore) {
        if (bestScore < 0 || bestScore < this.bestScore) {
            throw new IllegalArgumentException();
        }
        this.bestScore = bestScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSettings(final Settings settings) {
        if (settings == null) {
            throw new IllegalArgumentException();
        }
        this.settings = settings;
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

}
