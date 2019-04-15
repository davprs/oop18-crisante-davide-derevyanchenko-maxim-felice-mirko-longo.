package model.account;

import javafx.geometry.Dimension2D;

/**
 * Interface for all Account Settings.
 *
 */
public interface Settings {

    /**
     * Get the current resolution.
     * @return the resolution
     */
    Dimension2D getResolution();

    /**
     * Get the language.
     * @return the language
     */
    String getLanguage();

    /**
     * Get the Image Name.
     * @return the Image name
     */
    String getImageName();

    /**
     * Get the sound value.
     * @return the sound
     */
    boolean isSoundOn();

    /**
     * Set the resolution.
     * @param resolution the resolution to set
     */
    void setResolution(Dimension2D resolution);

    /**
     * Set the language.
     * @param language the language to set
     */
    void setLanguage(String language);

    /**
     * Set the image.
     * @param imageName the image to set
     */
    void setImageName(String imageName);

    /**
     * Set the sound.
     * @param value the sound to set
     */
    void setSound(boolean value);

}
