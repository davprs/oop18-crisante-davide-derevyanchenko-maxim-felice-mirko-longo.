package model;

/**
 * An interface that represents a Ship.
 *
 */
public interface CharacterShip extends Entity, Shooter {

    void update(double x, double y);
    
    void changeMoving();
    
}
