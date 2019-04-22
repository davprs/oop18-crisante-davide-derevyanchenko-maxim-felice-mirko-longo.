package test.enemyship;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import model.entity.ship.charactership.CharacterShipImpl;
import model.entity.ship.enemyship.EnemyShip;
import model.entity.ship.enemyship.EnemyShipImpl;
import model.game.Life;

/**
 * JUnit test for character ship class.
 *
 */
public class EnemyShipTest {

    private static final Point2D INITIAL_POSITION = new Point2D(500, 500);
    private static final Point2D TRANSLATE_VECTOR = new Point2D(-200, -200);
    private static final Point2D DESTINATION = new Point2D(-100, -100);
    private static final int DAMAGE_VALUE = 500;

    /**
     * Method that tests the movements of the enemyship.
     */
    @Test
    public void moveTest() {
        final EnemyShip enemy = new EnemyShipImpl(new Dimension2D(1000, 1000), INITIAL_POSITION);
        enemy.setPosition(new Point2D(0, 0));
        enemy.update(new Point2D(100, 100));
        assertTrue(enemy.getBoundary().getMinX() == 100 && enemy.getBoundary().getMinY() == 100);
        enemy.update(TRANSLATE_VECTOR);
        assertTrue(enemy.getBoundary().getMinX() == DESTINATION.getX() && enemy.getBoundary().getMinY() == DESTINATION.getY());
    }

    /**
     * Method that tests if the enemyship can check if it's intersecting an entity.
     */
    @Test
    public void intersectTest() {
        final EnemyShip enemy = new EnemyShipImpl(new Dimension2D(1000, 1000), INITIAL_POSITION);
        enemy.setPosition(new Point2D(0, 0));
        assertTrue(enemy.intersects(new CharacterShipImpl(new Point2D(0, 0), new Dimension2D(1000, 1000))));
        assertFalse(enemy.intersects(new CharacterShipImpl(new Point2D(1000, 1000), new Dimension2D(1000, 1000))));
    }

    /**
     * Method that tests the freeze powerup effect on the enemyships.
     */
    @Test
    public void frozenTest() {
        final EnemyShip enemy = new EnemyShipImpl(new Dimension2D(1000, 1000), INITIAL_POSITION);
        enemy.setPosition(new Point2D(0, 0));
        enemy.setFreeze(true);
        enemy.update(new Point2D(100, 100));
        assertTrue(enemy.getBoundary().getMinX() == 0 && enemy.getBoundary().getMinY() == 0);
    }

    /**
     * Method that tests if the enemyship can take damage.
     */
    @Test
    public void lifeTest() {
        final EnemyShipImpl enemy = new EnemyShipImpl(new Dimension2D(1000, 1000), INITIAL_POSITION);
        enemy.setPosition(new Point2D(0, 0));
        final Life startingLife = enemy.getLife();
        enemy.takeDamage(DAMAGE_VALUE);
        assertTrue(enemy.getLife().getCurrentHealth() < startingLife.getHealth() || enemy.getLife().getLives() < enemy.getLife().getLives());
    }
}
