package test.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javafx.geometry.Dimension2D;
import model.account.Settings;
import model.account.SettingsImpl;
import utilities.SystemUtils;

/**
* Class of JUnit Test for Settings.
*
*/
public class SettingsTest {

    private static final Dimension2D RES = new Dimension2D(SystemUtils.getScreenResolution().getWidth(), SystemUtils.getScreenResolution().getHeight());
    private static final Dimension2D RES_TO_SET = new Dimension2D(1280, 720);
    private static final String LANGUAGE = "en";
    private static final String LANGUAGE_TO_SET = "it";
    private static final String IMAGE = "spaceship";
    private static final String IMAGE_TO_SET = "GreenEvil";
    private Settings settings;

    /**
     * Test Settings getters.
     * 
     */
    @Test
    public void testGetters() {
        this.settings = new SettingsImpl(RES, LANGUAGE, IMAGE, false);
        assertEquals(LANGUAGE, this.settings.getLanguage());
        assertEquals(IMAGE, this.settings.getImageName());
        assertEquals(RES, this.settings.getResolution());
        assertFalse(this.settings.isSoundOn());
    }

    /**
     * Test Settings setters.
     * 
     */
    @Test
    public void testSetters() {
        this.settings = new SettingsImpl(RES, LANGUAGE, IMAGE, false);
        this.settings.setLanguage(LANGUAGE_TO_SET);
        assertEquals(LANGUAGE_TO_SET, this.settings.getLanguage());
        this.settings.setImageName(IMAGE_TO_SET);
        assertEquals(IMAGE_TO_SET, this.settings.getImageName());
        this.settings.setResolution(RES_TO_SET);
        assertEquals(RES_TO_SET, this.settings.getResolution());
        this.settings.setSound(true);
        assertTrue(this.settings.isSoundOn());
    }

    /**
     * Test the throwing of IllegalArgumentException in constructor.
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullResolution() {
        this.settings = new SettingsImpl(null, LANGUAGE, IMAGE, false);
    }

    /**
     * Test the throwing of IllegalArgumentException in constructor.
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullLanguage() {
        this.settings = new SettingsImpl(RES, null, IMAGE, false);
    }

    /**
     * Test the throwing of IllegalArgumentException in constructor.
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullImageName() {
        this.settings = new SettingsImpl(RES, LANGUAGE, null, false);
    }

    /**
     * Test the throwing of IllegalArgumentException in constructor.
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalResolution() {
        this.settings = new SettingsImpl(new Dimension2D(-1, -1), LANGUAGE, IMAGE, false);
    }

    /**
     * Test the throwing of IllegalArgumentException in constructor.
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyLanguage() {
        this.settings = new SettingsImpl(RES, "", IMAGE, false);
    }

    /**
     * Test the throwing of IllegalArgumentException in constructor.
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyImageName() {
        this.settings = new SettingsImpl(RES, LANGUAGE, "", false);
    }

    /**
     * Test the throwing of IllegalArgumentException in setter.
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNullResolution() {
        this.settings = new SettingsImpl(RES, LANGUAGE, IMAGE, false);
        this.settings.setResolution(null);
    }

    /**
     * Test the throwing of IllegalArgumentException in setter.
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNullLanguage() {
        this.settings = new SettingsImpl(RES, LANGUAGE, IMAGE, false);
        this.settings.setLanguage(null);
    }

    /**
     * Test the throwing of IllegalArgumentException in setter.
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNullImageName() {
        this.settings = new SettingsImpl(RES, LANGUAGE, IMAGE, false);
        this.settings.setImageName(null);
    }

    /**
     * Test the throwing of IllegalArgumentException in setter.
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetIllegalResolution() {
        this.settings = new SettingsImpl(RES, LANGUAGE, IMAGE, false);
        this.settings.setResolution(new Dimension2D(-1, -1));
    }

    /**
     * Test the throwing of IllegalArgumentException in setter.
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyLanguage() {
        this.settings = new SettingsImpl(RES, LANGUAGE, IMAGE, false);
        this.settings.setLanguage("");
    }
    /**
     * Test the throwing of IllegalArgumentException in setter.
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyImageName() {
        this.settings = new SettingsImpl(RES, LANGUAGE, IMAGE, false);
        this.settings.setImageName("");
    }

}
