package model;

import javafx.geometry.Point2D;

/**
 * an interface that represents a shooter.
 *
 */
public interface Shooter {

    /**
     * method to shoot.
     * @return the position in which the Bullet appears.
     */
    Point2D shoot();
}
