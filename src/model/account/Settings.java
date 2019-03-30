package model.account;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.geometry.Dimension2D;

/**
 * This class represents all the Settings an Account can set.
 *
 */
public final class Settings {

    private static final Dimension RES = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension2D RES_DEFAULT = new Dimension2D(RES.getWidth(), RES.getHeight());
    private static final String IMG_DEFAULT = "spaceship.png";
    private static final String LANGUAGE_DEFAULT = "en";
    private boolean isFullScreen;
    private Dimension2D resolution;
    private String language;
    private String imageName;
    private boolean sound;

    /**
     * Default Settings configuration. 
     */
    public static final Settings DEFAULT = new Settings(true, RES_DEFAULT, LANGUAGE_DEFAULT, IMG_DEFAULT, false);

    /**
     * Build a new Settings.
     * @param fullScreen the fullScreen value
     * @param res the resolution to set
     * @param language the String language to set
     * @param imageName the URL of the image to set
     * @param value the boolean value of the sound
     */
    public Settings(final boolean fullScreen, final Dimension2D res, final String language, final String imageName, final boolean value) {
        this.isFullScreen = fullScreen;
        this.resolution = res;
        this.language = language;
        this.imageName = imageName;
        this.sound = value;
    }

    /**
     * Get the FullScreen property.
     * @return the full screen value
     */
    public boolean isFullScreenOn() {
        return this.isFullScreen;
    }

    /**
     * Get the current resolution.
     * @return the resolution
     */
    public Dimension2D getResolution() {
        return this.resolution;
    }

    /**
     * Get the language.
     * @return the language
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * Get the Image Name.
     * @return the Image name
     */
    public String getImageName() {
        return this.imageName;
    }

    /**
     * Get the sound value.
     * @return the sound
     */
    public boolean isSoundOn() {
        return this.sound;
    }

    /**
     * Set the value to FullScreen.
     * @param isFullScreen the value to set
     */
    public void setFullScreen(final boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }

    /**
     * Set the resolution.
     * @param resolution the resolution to set
     */
    public void setResolution(final Dimension2D resolution) {
        this.resolution = resolution;
    }

    /**
     * Set the language.
     * @param language the language to set
     */
    public void setLanguage(final String language) {
        this.language = language;
    }

    /**
     * Set the image.
     * @param imageName the image to set
     */
    public void setImageName(final String imageName) {
        this.imageName = imageName;
    }

    /**
     * Set the sound.
     * @param value the sound to set
     */
    public void setSound(final boolean value) {
        this.sound = value;
    }
}
