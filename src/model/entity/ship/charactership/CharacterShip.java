package model.entity.ship.charactership;

import javafx.geometry.Point2D;
import model.entity.ship.Ship;
import model.game.Life;

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
     * Method that gets the central point of the character ship.
     * 
     * @return the central point of the character ship
     */
    Point2D getCentralPosition();

    /**
     * Method that gets the speed of the character ship.
     * 
     * @return the speed of the character ship
     */
    double getSpeed();

    /**
     * Method that puts to zero the health and the lives to 0.
     */
    void endGame();

    /**
     * Method that gets the life of the character ship.
     * 
     * @return the Life of the character ship
     */
    Life getLife();

    /**
     * Method that sets the immunity status of the character ship.
     * @param value the value to set
     */
    void setImmunity(boolean value);
}
