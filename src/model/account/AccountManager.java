package model.account;

/**
 * Represents an AccountManager.
 *
 */
public interface AccountManager {

    /**
     * Register a new account.
     * @param account the new account to register
     */
    void register(Account account);

    /**
     * Check if the account is present.
     * 
     * @param account the account to check
     * @return true only if the account is already present.
     */
    boolean isPresent(Account account);

    /**
     * Check the password of the account.
     * 
     * @param account the account to check whose password
     * @return true only if the account password is correct.
     */
    boolean checkPassword(Account account);
}
