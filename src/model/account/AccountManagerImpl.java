package model.account;

import java.util.Optional;
import java.util.Set;

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
        final Optional<Account> acc = this.accounts.stream().filter(a -> a.equals(account)).findFirst();
        if (acc.isPresent()) {
            return acc.get().getPassword().equals(account.getPassword());
        }
        return false;
    }
}
