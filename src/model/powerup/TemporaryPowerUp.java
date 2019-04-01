package model.powerup;
/**
 * 
 * This interface represents a power up that is enabled for a limited period.
 *
 */
public interface TemporaryPowerUp extends PowerUp {
    /**
     * stopped the power up.
     */
    void stop();
}
