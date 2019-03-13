package model.account;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 * This class represents all the Settings an Account can set.
 *
 */
public final class Settings {

    private static final Dimension RES = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension2D RES_DEFAULT = new Dimension2D(RES.getWidth(), RES.getHeight());
    private static final Image IMG_DEFAULT = null;
    private boolean sound;
    private Dimension2D resolution;
    private Image image;
    /**
     * Default Settings configuration. 
     */
    public static final Settings DEFAULT = new Settings(RES_DEFAULT, IMG_DEFAULT, false);

    private Settings(final Dimension2D res, final Image img, final boolean value) {
        this.resolution = res;
        this.image = img;
        this.sound = value;
    }

    /**
     * Builder class for Settings.
     *
     */
    public static class Builder {
        private Dimension2D res = RES_DEFAULT;
        private Image img = IMG_DEFAULT;
        private boolean soundOn;

        /**
         * Set the resolution.
         * @param res the resolution to set.
         * @return the Builder.
         */
        public Builder resolution(final Dimension2D res) {
            this.res = res;
            return this;
        }

        /**
         * Set the image.
         * @param image the image to set.
         * @return the Builder.
         */
        public Builder image(final Image image) {
            this.img = image;
            return this;
        }

        /**
         * Set the sound.
         * @param value the value to set.
         * @return the Builder.
         */
        public Builder sound(final boolean value) {
            this.soundOn = value;
            return this;
        }

        /**
         * Build a new Settings.
         * @return a Settings.
         */
        public Settings build() {
 //           if (this.res == null || this.img == null) {
            if (this.res == null){
                throw new IllegalArgumentException();
            }
            return new Settings(this.res, this.img, this.soundOn);
        }
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
     * Get the image.
     * @return the image
     */
    public Image getImage() {
        return this.image;
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
     * @param image the image to set
     */
    public void setImage(final Image image) {
        this.image = image;
    }

}
