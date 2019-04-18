package model.account;

import javafx.geometry.Dimension2D;
import utilities.SystemUtils;

/**
 * Implementation of Settings Interface.
 *
 */
public final class SettingsImpl implements Settings {

    private static final Dimension2D DEFAULT_RESOLUTION = new Dimension2D(SystemUtils.getScreenResolution().getWidth(), SystemUtils.getScreenResolution().getHeight());
    private static final String DEFAULT_IMAGE = "spaceship";
    private static final String DEFAULT_LANGUAGE = "en";
    private static final Settings DEFAULT = new SettingsImpl(DEFAULT_RESOLUTION, DEFAULT_LANGUAGE, DEFAULT_IMAGE, false);
    private Dimension2D resolution;
    private String language;
    private String imageName;
    private boolean sound;

    /**
     * Build a new Settings.
     * @param res the resolution to set
     * @param language the String language to set
     * @param imageName the URL of the image to set
     * @param value the boolean value of the sound
     */
    public SettingsImpl(final Dimension2D res, final String language, final String imageName, final boolean value) {
        if (res == null || language == null || imageName == null || res.getWidth() < 0 || res.getHeight() < 0 || language.equals(SystemUtils.getEmptyString()) || imageName.equals(SystemUtils.getEmptyString())) {
            throw new IllegalArgumentException();
        }
        this.resolution = res;
        this.language = language;
        this.imageName = imageName;
        this.sound = value;
    }

    /**
     * Get the Default Settings.
     * @return the default settings
     */
    public static Settings getDefaultSettings() {
        return DEFAULT;
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
    public void setResolution(final Dimension2D resolution) {
        if (resolution == null || resolution.getWidth() < 0 || resolution.getHeight() < 0) {
            throw new IllegalArgumentException();
        }
        this.resolution = resolution;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLanguage(final String language) {
        if (language == null || language.equals(SystemUtils.getEmptyString())) {
            throw new IllegalArgumentException();
        }
        this.language = language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setImageName(final String imageName) {
        if (imageName == null || imageName.equals(SystemUtils.getEmptyString())) {
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
