package test.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import javafx.geometry.Dimension2D;
import model.account.Account;
import model.account.AccountImpl;
import model.account.Settings;
import model.account.SettingsImpl;

/**
 * Class of JUnit Test for Account.
 *
 */
public class AccountTest {

    private static final int ILLEGAL_SCORE = -10;
    private static final double WIDTH = 1920;
    private static final double HEIGHT = 1080;
    private static final String DEFAULT_USERNAME = "mirko";
    private static final String DEFAULT_PASSWORD = "Mirko";
    private static final String PASSWORD_TO_SET = "mirko";
    private static final Dimension2D RESOLUTION = new Dimension2D(WIDTH, HEIGHT);
    private static final Settings DEFAULT_SETTINGS = new SettingsImpl(RESOLUTION, "it", "mySpaceship.png", false);
    private static final Settings SETTINGS_TO_SET = new SettingsImpl(RESOLUTION, "en", "spaceship.png", true);
    private static final Account DEFAULT_SIMPLE_ACCOUNT = new AccountImpl.Builder(DEFAULT_USERNAME, DEFAULT_PASSWORD)
                                                                         .build();
    private static final Account DEFAULT_COMPLETE_ACCOUNT = new AccountImpl.Builder(DEFAULT_USERNAME, DEFAULT_PASSWORD)
                                                                           .withNickname("")
                                                                           .bestScore(100)
                                                                           .addMySettings(DEFAULT_SETTINGS)
                                                                           .build();
    private Account account;

    /**
     * Test getters of a SimpleAccount.
     */
    @Test
    public void testGettersSimpleAccount() {
        account = new AccountImpl.Builder(DEFAULT_USERNAME, DEFAULT_PASSWORD).build();
        assertEquals(DEFAULT_USERNAME, account.getUsername());
        assertEquals(DEFAULT_PASSWORD, account.getPassword());
        assertEquals(DEFAULT_USERNAME, account.getNickname());
        assertEquals(0, account.getBestScore());
        assertEquals(SettingsImpl.getDefaultSettings(), account.getSettings());
    }

    /**
     * Test getters of a Complete Account.
     */
    @Test
    public void testGettersCompleteAccount() {
        account =  new AccountImpl.Builder(DEFAULT_USERNAME, DEFAULT_PASSWORD)
                                  .withNickname("")
                                  .bestScore(100)
                                  .addMySettings(DEFAULT_SETTINGS)
                                  .build();
        final Settings settings = account.getSettings();
        assertEquals("it", settings.getLanguage());
        assertEquals(RESOLUTION, settings.getResolution());
        assertFalse(settings.isSoundOn());
    }

    /**
     * Test setters of a SimpleAccount.
     */
    @Test
    public void testSettersSimpleAccount() {
        account = DEFAULT_SIMPLE_ACCOUNT;
        assertEquals(DEFAULT_USERNAME, account.getUsername());
        account.setPassword(PASSWORD_TO_SET);
        assertNotEquals(DEFAULT_PASSWORD, account.getPassword());
        assertEquals(PASSWORD_TO_SET, account.getPassword());
    }

    /**
     * Test setters of a CompleteAccount.
     */
    @Test
    public void testSettersCompleteAccount() {
        account = DEFAULT_COMPLETE_ACCOUNT;
        account.setSettings(SETTINGS_TO_SET);
        final Settings settings = account.getSettings();
        assertEquals(SETTINGS_TO_SET, settings);
    }

    /**
     * Test the throwing of IllegalStateException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        account = new AccountImpl.Builder(DEFAULT_USERNAME, null)
                                 .build();
    }

    /**
     * Test the throwing of IllegalStateException.
     */
    @Test(expected = IllegalStateException.class)
    public void testIllegalStateExceptionWithNullSettings() {
        account = new AccountImpl.Builder(DEFAULT_USERNAME, DEFAULT_PASSWORD)
                                 .addMySettings(null)
                                 .build();
    }

    /**
     * Test the throwing of IllegalStateException.
     */
    @Test(expected = IllegalStateException.class)
    public void testIllegalStateExceptionWithIllegalBestScore() {
        account = new AccountImpl.Builder(DEFAULT_USERNAME, DEFAULT_PASSWORD)
                                 .bestScore(ILLEGAL_SCORE)
                                 .build();
    }

    /**
     * Test the throwing of IllegalStateException.
     */
    @Test(expected = IllegalStateException.class)
    public void testIllegalStateExceptionWithNullNickname() {
        account = new AccountImpl.Builder(DEFAULT_USERNAME, DEFAULT_PASSWORD)
                                 .withNickname(null)
                                 .build();
    }

    /**
     * Test if account.equals and consequently hashcode works.
     */
    @Test
    public void testEqualsAndHashCode() {
        account = DEFAULT_SIMPLE_ACCOUNT;
        final Account account = DEFAULT_SIMPLE_ACCOUNT;
        assertEquals(account, this.account);
    }
}
