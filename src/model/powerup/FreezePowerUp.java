package model.powerup;

import java.util.List;

import model.entity.ship.enemyship.EnemyShip;

/**
 * Power Up that freeze all enemies for a duration time.
 */
public class FreezePowerUp implements TemporaryPowerUp {

    private static final int DURATION = 10;
    private final List<EnemyShip> enemies;

    /**
     * Build the FreezePowerUp.
     * @param enemies the list of enemies
     */
    public FreezePowerUp(final List<EnemyShip> enemies) {
        this.enemies = enemies;
    }

    /**
     * Method that freezes all enemies.
     */
    @Override
    public void run() {
        for (final EnemyShip enemy : enemies) {
            enemy.changeFreeze();
        }
    }

    /**
     *  Method to invoke to stop freezing enemies.
     */
    @Override
    public void stop() {
        for (final EnemyShip enemy : enemies) {
            enemy.changeFreeze();
        }
        this.enemies.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDuration() {
        return DURATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Freeze PowerUp";
    }

}
