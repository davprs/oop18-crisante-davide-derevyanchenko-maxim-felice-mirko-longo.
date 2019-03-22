package controller;

import java.util.function.Consumer;

/**
 * This class create a PowerUp to the game.
 * @param <T> type of PowerUp
 */
public class PowerUpController<T> {

    private Consumer<T> currentPowerUp;

    /**
     * Build a powerUpController.
     * @param powerUp consumer
     */
    public PowerUpController(final Consumer<T> powerUp) {
        this.currentPowerUp = powerUp;
    }

    /**
     * accept the current PowerUp.
     * @param current argument to accept
     */
    public void accept(final T current) {
        this.currentPowerUp.accept(current);
    }

    /**
     * Set currentConsumer.
     * @param powerUp the new consumer
     */
    public void setConsumer(final Consumer<T> powerUp) {
        this.currentPowerUp = powerUp;
    }

}
