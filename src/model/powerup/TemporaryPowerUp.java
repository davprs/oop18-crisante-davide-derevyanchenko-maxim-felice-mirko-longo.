package model.powerup;

/**
 * This interface represents a Power Up that is enabled for a limited period.
 *
 */
public interface TemporaryPowerUp extends PowerUp {

    /**
     * Method to stop the power up.
     */
    void stop();

    /**
     * Method to get the duration of the power up (in seconds).
     * @return the duration
     */
    int getDuration();
}
