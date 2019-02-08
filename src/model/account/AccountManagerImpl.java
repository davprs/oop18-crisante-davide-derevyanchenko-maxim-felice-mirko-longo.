package model.account;

import java.io.IOException;
import java.util.Set;

import controller.utilities.FileUtils;
import controller.utilities.StringUtils;

/**
 * Implementation of AccountManager Interface.
 *
 */
public class AccountManagerImpl implements AccountManager {

    private final Set<Account> accounts;

    /**
     * Build an AccountManager, reading accounts already present.
     */
    public AccountManagerImpl() {
       try {
           accounts = FileUtils.readAccounts();
        } catch (IOException e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            throw new IllegalStateException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(final Account account) {
        accounts.add(account);
        try {
            FileUtils.printAccount(account);
        } catch (IOException e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPresent(final Account account) {
        return accounts.contains(account);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkPassword(final Account account) {
        try {
            return isPresent(account) && account.getPassword().equals(FileUtils.readPassword(account.getUsername()));
        } catch (IOException e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            System.exit(0);
        }
        return false;
    }
}
