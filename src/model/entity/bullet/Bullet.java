package model.entity.bullet;

import model.entity.Entity;

/**
 * An interface that represents a Bullet.
 *
 */
public interface Bullet extends Entity {

    /**
     * Update the Bullet position.
     */
    void update();

    /**
     * Get the angle of the direction (in radiant).
     * @return the angle of the direction.
     */
    double getAngle();

    /**
     * Get the damage the Bullet provokes when impacts with an enemy.
     * @return the health-points it subtracts to the enemy it hits.
     */
    int getDamage();

}
