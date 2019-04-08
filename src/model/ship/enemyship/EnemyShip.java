package model.ship.enemyship;

import javafx.geometry.Point2D;
import model.ship.Ship;

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
}
