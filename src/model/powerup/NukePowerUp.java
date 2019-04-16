package model.powerup;

import java.util.List;

import model.entity.ship.enemyship.EnemyShip;

/**
 * Power Up that destroys all enemies.
 */
public class NukePowerUp implements PowerUp {

    private final List<EnemyShip> enemies;

    /**
     * Build the NukePowerUp.
     * @param enemies the list of enemies
     */
    public NukePowerUp(final List<EnemyShip> enemies) {
        this.enemies = enemies;
    }

    /**
     * Method to invoke to destroy the enemies.
     */
    @Override
    public void run() {
        for (final EnemyShip enemy: enemies) {
            enemy.destroy();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Nuke PowerUp";
    }

}
