package test.account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import model.account.Account;
import model.account.AccountImpl;
import model.account.AccountManager;
import model.account.AccountManagerImpl;

/**
 * Class of JUnit Test for AccountManager.
 *
 */
public class AccountManagerTest {

    private static final String USERNAME = "prova";
    private static final String PASSWORD = "prova";
    private static final Account ACCOUNT1 = new AccountImpl.Builder(USERNAME, PASSWORD).build();
    private static final Set<Account> SET = new HashSet<>();
    private final AccountManager accManager = new AccountManagerImpl(SET);

    /**
     * Simple Test for the AccountManager. 
     */
    @Test
    public void testRegister() {
        assertFalse(this.accManager.isPresent(ACCOUNT1));
        this.accManager.register(ACCOUNT1);
        assertTrue(this.accManager.isPresent(ACCOUNT1));
        assertTrue(this.accManager.checkPassword(new AccountImpl.Builder(USERNAME, PASSWORD).build()));
    }
}
