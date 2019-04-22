package test.bullet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import model.entity.bullet.Bullet;
import model.entity.bullet.BulletImpl;
import model.entity.ship.charactership.CharacterShipImpl;

/**
 * JUnit test for bullet class.
 *
 */
public class BulletTest {

    private static final int X_POSITION = -100;
    private static final int Y_POSITION = -100;
    /**
     * Method that tests the movements of the bullet.
     */
    @Test
    public void moveTest() {
        Bullet bullet = new BulletImpl(1, new Point2D(0, 0), new Point2D(100, 100), new Dimension2D(1000, 1000));
        assertTrue(bullet.getBoundary().getMinX() == 0 && bullet.getBoundary().getMinY() == 0);
        bullet.update();
        assertTrue(bullet.getBoundary().getMinX() > 0 && bullet.getBoundary().getMinY() > 0);

        bullet = new BulletImpl(1, new Point2D(0, 0), new Point2D(X_POSITION, Y_POSITION), new Dimension2D(1000, 1000));
        assertTrue(bullet.getBoundary().getMinX() == 0 && bullet.getBoundary().getMinY() == 0);
        bullet.update();
        assertTrue(bullet.getBoundary().getMinX() < 0 && bullet.getBoundary().getMinY() < 0);

        bullet = new BulletImpl(1, new Point2D(0, 0), new Point2D(X_POSITION, 100), new Dimension2D(1000, 1000));
        assertTrue(bullet.getBoundary().getMinX() == 0 && bullet.getBoundary().getMinY() == 0);
        bullet.update();
        assertTrue(bullet.getBoundary().getMinX() < 0 && bullet.getBoundary().getMinY() > 0);

        bullet = new BulletImpl(1, new Point2D(0, 0), new Point2D(100, Y_POSITION), new Dimension2D(1000, 1000));
        assertTrue(bullet.getBoundary().getMinX() == 0 && bullet.getBoundary().getMinY() == 0);
        bullet.update();
        assertTrue(bullet.getBoundary().getMinX() > 0 && bullet.getBoundary().getMinY() < 0);
    }

    /**
     * Method that tests if the bullet can check if it's intersecting an entity.
     */
    @Test
    public void intersects() {
        Bullet bullet = new BulletImpl(1, new Point2D(0, 0), new Point2D(100, 100), new Dimension2D(1000, 1000));
        assertTrue(bullet.intersects(new CharacterShipImpl(new Point2D(0, 0), new Dimension2D(1000, 1000))));
    }

    /**
     * Method that tests if the bullet is "alive" (active) when it hits an entity or when it goes off from the canvas.
     */
    @Test
    public void isAlive() {
        Bullet bullet = new BulletImpl(1, new Point2D(0, 0), new Point2D(-1, -1), new Dimension2D(1000, 1000));
        assertTrue(bullet.isAlive());
        bullet.update();
        assertFalse(bullet.isAlive());
        bullet = new BulletImpl(1, new Point2D(0, 0), new Point2D(-1, -1), new Dimension2D(1000, 1000));
        bullet.intersects(new CharacterShipImpl(new Point2D(0, 0), new Dimension2D(1000, 1000)));
        assertFalse(bullet.isAlive());
    }
}
