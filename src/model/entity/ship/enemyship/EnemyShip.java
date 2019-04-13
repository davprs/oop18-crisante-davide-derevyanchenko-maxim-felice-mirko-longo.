package model.entity.ship.enemyship;

import javafx.geometry.Point2D;
import model.entity.ship.Ship;

/**
 * An interface that represents an EnemyShip.
 *
 */
public interface EnemyShip extends Ship {

    /**
     * Get the speed.
     * @return the speed.
     */
    double getSpeed();

    /**
     * Get the enemy level.
     * @return the enemy level.
     */
    int getLevel();

    /**
     * Update the Enemy position.
     * @param addPosition the position to add at the current position.
     */
    void update(Point2D addPosition);

    /**
     * Return true if the EnemyShip can shoot.
     * @return true if the EnemyShip can shoot.
     */
    boolean canShoot();

    /**
     * Get the Score points after the death.
     * @return the int points
     */
    int getScorePoints();

    /**
     * Change the freeze status.
     */
    void changeFreeze();

    /**
     * Get the frozen status.
     * @return the value
     */
    boolean isFrozen();
}
