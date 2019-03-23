package model;

import javafx.geometry.Point2D;
import model.bullet.Bullet;

/**
 * an interface that represents a shooter.
 *
 */
public interface Shooter {

    /**
     * method to shoot.
     * @param target the position of the target.
     * @return a Bullet.
     */
    Bullet shoot(Point2D target);
}
