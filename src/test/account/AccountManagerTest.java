package test.account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
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

    private static final String USERNAME_1 = "prova";
    private static final String USERNAME_2 = "test";
    private static final String PASSWORD = "prova";
    private static final String PASSWORD_2 = "test";
    private static final Account ACCOUNT_1 = new AccountImpl.Builder(USERNAME_1, PASSWORD).build();
    private static final Account ACCOUNT_2 = new AccountImpl.Builder(USERNAME_2, PASSWORD).build();
    private static final Set<Account> SET = new HashSet<>(Arrays.asList(ACCOUNT_1));
    private AccountManager accManager;

    /**
     * Simple Test for the AccountManager. 
     */
    @Test
    public void testIsPresent() {
        this.accManager = new AccountManagerImpl(SET);
        assertTrue(this.accManager.isPresent(ACCOUNT_1));
        assertFalse(this.accManager.isPresent(ACCOUNT_2));
    }

    /**
     * Simple Test for the AccountManager. 
     */
    @Test
    public void testRegister() {
        this.accManager = new AccountManagerImpl(SET);
        this.accManager.register(ACCOUNT_2);
        assertTrue(this.accManager.isPresent(ACCOUNT_2));
    }

    /**
     * Simple Test for the AccountManager. 
     */
    @Test
    public void testCheckPassword() {
        this.accManager = new AccountManagerImpl(SET);
        assertTrue(this.accManager.checkPassword(new AccountImpl.Builder(USERNAME_1, PASSWORD).build()));
        assertFalse(this.accManager.checkPassword(new AccountImpl.Builder(USERNAME_1, PASSWORD_2).build()));
    }
}
