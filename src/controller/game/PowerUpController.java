package controller.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import controller.agents.TimeAgent;
import model.entity.ship.enemyship.EnemyShip;
import model.powerup.FreezePowerUp;
import model.powerup.ImmunityPowerUp;
import model.powerup.LifePowerUp;
import model.powerup.NukePowerUp;
import model.powerup.PowerUp;
import model.powerup.TemporaryPowerUp;

/**
 * This class create a PowerUp to the game.
 */
public class PowerUpController {

    private static final int POWER_UP_RATE = 1000;
    private static final List<PowerUp> POWER_UPS = new ArrayList<>();
    private static final List<TemporaryPowerUp> TEMPORARY_POWER_UPS = new ArrayList<>();
    private final GameController gameController;
    private final Random random = new Random(); 
    private int counter;
    private boolean available;

    /**
     * Build a powerUpController.
     * @param gameController the controller of the game
     */
    public PowerUpController(final GameController gameController) {
        this.gameController = gameController;
        this.counter = 1;
        this.available = true;
    }

    /**
     * Get the available value.
     * @return the value
     */
    public synchronized boolean isAvailable() {
        return this.available;
    }

    /**
     * Set the available status.
     * @param value the value to set
     */
    public synchronized void setAvailable(final boolean value) {
        this.available = value;
    }

    /**
     * Active a random PowerUp.
     */
    public void active() {
        if (checkScore(this.gameController.getScore().getScorePoints())) {
            this.available = false;
            final List<EnemyShip> enemies = this.gameController.getFieldController().getEnemies().stream()
                                                                                                 .map(ec -> ec.getEntity())
                                                                                                 .collect(Collectors.toList());
            POWER_UPS.addAll(Arrays.asList(new LifePowerUp(this.gameController.getFieldController().getCharacter().getLife()), new NukePowerUp(enemies)));
            TEMPORARY_POWER_UPS.addAll(Arrays.asList(new FreezePowerUp(enemies), new ImmunityPowerUp(this.gameController.getFieldController().getCharacter().getEntity())));
            if (this.random.nextInt(2) == 0) {
                final PowerUp powerUp = POWER_UPS.get((this.random.nextInt(POWER_UPS.size())));
                powerUp.run();
                new TimeAgent(powerUp, gameController).start();
            } else {
                final TemporaryPowerUp tempPowerUp = TEMPORARY_POWER_UPS.get(this.random.nextInt(TEMPORARY_POWER_UPS.size()));
                tempPowerUp.run();
                new TimeAgent(tempPowerUp, gameController).start();
            }
        }
    }

    private boolean checkScore(final int score) {
        final boolean checkReturn = score / POWER_UP_RATE >= counter;
        if (checkReturn) {
            this.counter = (score / POWER_UP_RATE) + 1;
        }
        return checkReturn;
    }
}
