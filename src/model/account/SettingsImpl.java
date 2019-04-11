package model.account;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.geometry.Dimension2D;

/**
 * This class represents all the Settings an Account can set.
 *
 */
public final class SettingsImpl implements Settings {

    private static final Dimension RES = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension2D RES_DEFAULT = new Dimension2D(RES.getWidth(), RES.getHeight());
    private static final String IMG_DEFAULT = "spaceship";
    private static final String LANGUAGE_DEFAULT = "en";
    private boolean isFullScreen;
    private Dimension2D resolution;
    private String language;
    private String imageName;
    private boolean sound;
    /**
     * Default Settings configuration. 
     */
    public static final SettingsImpl DEFAULT = new SettingsImpl(true, RES_DEFAULT, LANGUAGE_DEFAULT, IMG_DEFAULT, false);

    /**
     * Build a new Settings.
     * @param fullScreen the fullScreen value
     * @param res the resolution to set
     * @param language the String language to set
     * @param imageName the URL of the image to set
     * @param value the boolean value of the sound
     */
    public SettingsImpl(final boolean fullScreen, final Dimension2D res, final String language, final String imageName, final boolean value) {
        if (res == null || language == null || imageName == null || res.getWidth() < 0 || res.getHeight() < 0 || language.equals("") || imageName.equals("")) {
            throw new IllegalArgumentException();
        }
        this.isFullScreen = fullScreen;
        this.resolution = res;
        this.language = language;
        this.imageName = imageName;
        this.sound = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public boolean isFullScreenOn() {
        return this.isFullScreen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension2D getResolution() {
        return this.resolution;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLanguage() {
        return this.language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImageName() {
        return this.imageName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSoundOn() {
        return this.sound;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFullScreen(final boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResolution(final Dimension2D resolution) {
        if (resolution == null) {
            throw new IllegalArgumentException();
        }
        this.resolution = resolution;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLanguage(final String language) {
        if (language == null) {
            throw new IllegalArgumentException();
        }
        this.language = language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setImageName(final String imageName) {
        if (imageName == null) {
            throw new IllegalArgumentException();
        }
        this.imageName = imageName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSound(final boolean value) {
        this.sound = value;
    }
}
