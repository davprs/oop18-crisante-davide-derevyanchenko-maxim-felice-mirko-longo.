package model.account;

import java.io.IOException;
import java.util.Set;

import utilities.ErrorLog;
import utilities.FileUtils;

/**
 * Implementation of AccountManager Interface.
 *
 */
public class AccountManagerImpl implements AccountManager {

    private final Set<Account> accounts;

    /**
     * Build an AccountManager to manage the accounts.
     * @param accounts the accounts to manage
     */
    public AccountManagerImpl(final Set<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(final Account account) {
        this.accounts.add(account);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPresent(final Account account) {
        return this.accounts.contains(account);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkPassword(final Account account) {
        try {
            return isPresent(account) && account.getPassword().equals(FileUtils.getPassword(account.getUsername()));
        } catch (IOException e) {
            ErrorLog.getLog().printError();
            System.exit(0);
        }
        return false;
    }
}
