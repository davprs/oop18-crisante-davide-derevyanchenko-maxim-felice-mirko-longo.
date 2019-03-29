package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.powerup.PowerUp;

/**
 * This class create a PowerUp to the game.
 */
public class PowerUpController {

    private static final int POWER_UP_RATE = 1000;
    private final List<PowerUp> powerUps;
    private int counter;

    /**
     * Build a powerUpController.
     */
    public PowerUpController() {
        this.powerUps = new ArrayList<>();
        this.counter = 1;
    }

    /**
     * Active a random PowerUp.
     * @param score the current score
     */
    public void active(final int score) {
        if (checkScore(score)) {
            this.powerUps.get((new Random().nextInt(this.powerUps.size()))).run();
            this.counter++;
        }
    }

    private boolean checkScore(final int score) {
        return score >= POWER_UP_RATE * counter;
    }
}
