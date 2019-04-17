package test.charactership;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import model.entity.ship.charactership.CharacterShip;
import model.entity.ship.charactership.CharacterShipImpl;

/**
 * JUnit test for character ship class.
 *
 */
public class CharacterShipTest {

    private static final Dimension2D FIELD_DIMENSION = new Dimension2D(1000, 1000);
    private static final Point2D TRANSLATED_POINT = new Point2D(400, 400);
    private static final int INITIAL_HEALTH = 1000;
    private static final int DAMAGE_AMOUNT = 300;

    /**
     * Method that tests the movements of the character ship.
     */
    @Test
    public void moveTest() {
        final Point2D start = new Point2D(FIELD_DIMENSION.getWidth() / 2, FIELD_DIMENSION.getHeight() / 2);
        final CharacterShip ship = new CharacterShipImpl(start, FIELD_DIMENSION);
        ship.update(TRANSLATED_POINT.getX(), TRANSLATED_POINT.getY());
        assertTrue(ship.getBoundary().getMinX() == TRANSLATED_POINT.getX() && ship.getBoundary().getMinY() == TRANSLATED_POINT.getY());
    }

    /**
     * Method that tests the interaction with the character ship life.
     */
    @Test
    public void lifeTest() {
        final Point2D start = new Point2D(FIELD_DIMENSION.getWidth() / 2, FIELD_DIMENSION.getHeight() / 2);
        final CharacterShip ship = new CharacterShipImpl(start, FIELD_DIMENSION);

        ship.takeDamage(DAMAGE_AMOUNT);
        assertEquals(INITIAL_HEALTH - DAMAGE_AMOUNT, ship.getLife().getCurrentHealth());

        ship.destroy();
        assertEquals(2, ship.getLife().getLives());
        assertEquals(INITIAL_HEALTH, ship.getLife().getCurrentHealth());

        ship.endGame();
        assertEquals(0, ship.getLife().getLives());
        assertEquals(0, ship.getLife().getCurrentHealth());
    }

    /**
     * Method that tests the immunity ability of the character ship.
     */
    @Test
    public void immunityTest() {
        final Point2D start = new Point2D(FIELD_DIMENSION.getWidth() / 2, FIELD_DIMENSION.getHeight() / 2);
        final CharacterShip ship = new CharacterShipImpl(start, FIELD_DIMENSION);
        ship.setImmunity(true);

        ship.takeDamage(DAMAGE_AMOUNT);
        assertEquals(INITIAL_HEALTH, ship.getLife().getCurrentHealth());

        ship.destroy();
        assertEquals(3, ship.getLife().getLives());
        assertEquals(INITIAL_HEALTH, ship.getLife().getCurrentHealth());

        ship.endGame();
        assertEquals(0, ship.getLife().getLives());
        assertEquals(0, ship.getLife().getCurrentHealth());
    }

    /**
     * Method that tests the intersection of the character ship.
     */
    @Test
    public void intersectionTest() {
        final Point2D start = new Point2D(FIELD_DIMENSION.getWidth() / 2, FIELD_DIMENSION.getHeight() / 2);
        final CharacterShip ship = new CharacterShipImpl(start, FIELD_DIMENSION);
        final Point2D entityStart = ship.getCentralPosition();
        final CharacterShip entity = new CharacterShipImpl(entityStart, FIELD_DIMENSION);
        assertTrue(ship.intersects(entity));
    }
}
