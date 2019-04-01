package model.meteor;

import model.Entity;

/**
 * An interface that represents a Meteor.
 *
 */
public interface Meteor extends Entity {

    /**
     * Update the Meteor position.
     */
    void update();

    /**
     * Get the angle of the direction (in radiant).
     * @return the angle of the direction.
     */
    double getAngle();

    /**
     * Get the damage the Meteor provokes when impacts with the ChacterShip.
     * @return the health-points it subtracts to the CharacterShip.
     */
    int getDamage();
}
