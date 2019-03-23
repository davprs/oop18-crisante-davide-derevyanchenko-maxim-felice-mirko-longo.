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
    private static final String IMAGES = "res" + System.getProperty("file.separator") + "images";
    private static final String IMG_DEFAULT = IMAGES + "spaceship.png";
    private boolean sound;
    private Dimension2D resolution;
    private String urlImage;
    /**
     * Default Settings configuration. 
     */
    public static final Settings DEFAULT = new Settings(RES_DEFAULT, IMG_DEFAULT, false);

    /**
     * Build a new Settings.
     * @param res the resolution to set
     * @param urlImg the URL of the image to set
     * @param value the boolean value of the sound
     */
    public Settings(final Dimension2D res, final String urlImg, final boolean value) {
        this.resolution = res;
        this.urlImage = urlImg;
        this.sound = value;
    }

    /**
     * Get the sound value.
     * @return the sound
     */
    public boolean isSoundOn() {
        return this.sound;
    }

    /**
     * Get the current resolution.
     * @return the resolution
     */
    public Dimension2D getResolution() {
        return this.resolution;
    }

    /**
     * Get the URL Image.
     * @return the URL Image
     */
    public String getURLImage() {
        return this.urlImage;
    }

    /**
     * Set the sound.
     * @param value the sound to set
     */
    public void setSound(final boolean value) {
        this.sound = value;
    }

    /**
     * Set the resolution.
     * @param resolution the resolution to set
     */
    public void setResolution(final Dimension2D resolution) {
        this.resolution = resolution;
    }

    /**
     * Set the image.
     * @param ulrImage the image to set
     */
    public void setImage(final String ulrImage) {
        this.urlImage = ulrImage;
    }

}
