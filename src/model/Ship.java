package model;
/**
 * an interface that represents a Ship.
 *
 */
public interface Ship extends Entity, Shooter {

    /**
     * 
     * @param x the horizontal position of the bullet.
     * @param y the vertical position of the bullet.
     * @return true if the bullet hits the ship, false otherwise.
     */
    boolean isCollided(int x, int y);

    /**
     * method to destroy the ship.
     */
    void toDestroy();
}
