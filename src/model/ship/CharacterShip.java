package model.ship;

/**
 * An interface that represents a Ship.
 *
 */
public interface CharacterShip extends Ship {

    /**
     * Moves the character ship in the position received as parameters.
     * 
     * @param x the X value of the new position
     * @param y the Y value of the new position
     */
    void update(double x, double y);

    /**
     * When mouse enters or exits the window the moving variable is switched.
     */
    void changeMoving();
}
