package test.life;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.game.Life;
import model.game.LifeImpl;

/**
 * Class that test the functionality of the Life class.
 *
 */
public class LifeTest {

    private static final int INITIAL_LIVES = 3;
    private static final int INITIAL_HEALTH = 1000;
    private static final int DAMAGE_AMOUNT = 300;
    private static final int RECOVER_AMOUNT = 150;

    /**
     * Method that tests the health functionality.
     */
    @Test
    public void healthTest() {
        final Life life = LifeImpl.createCustomizedLife(INITIAL_LIVES, INITIAL_HEALTH);

        life.loseHealth(DAMAGE_AMOUNT);
        assertEquals(INITIAL_HEALTH - DAMAGE_AMOUNT, life.getCurrentHealth());
        assertEquals(INITIAL_HEALTH, life.getHealth());
        life.addHealth(RECOVER_AMOUNT);
        assertEquals(INITIAL_HEALTH - DAMAGE_AMOUNT + RECOVER_AMOUNT, life.getCurrentHealth());
        assertEquals(INITIAL_HEALTH, life.getHealth());
        life.addHealth(RECOVER_AMOUNT);
        assertEquals(INITIAL_HEALTH, life.getCurrentHealth());
        assertEquals(INITIAL_HEALTH, life.getHealth());
    }

    /**
     * Method that tests the lives functionality.
     */
    @Test
    public void lifeTest() {
        final Life life = LifeImpl.createCustomizedLife(INITIAL_LIVES, INITIAL_HEALTH);

        life.loseHealth(DAMAGE_AMOUNT);
        assertEquals(INITIAL_LIVES, life.getLives());
        life.addHealth(RECOVER_AMOUNT);
        assertEquals(INITIAL_LIVES, life.getLives());

        life.loseLife();
        assertEquals(INITIAL_LIVES - 1, life.getLives());
        life.addLife();
        assertEquals(INITIAL_LIVES, life.getLives());

        life.loseHealth(life.getHealth());
        assertEquals(INITIAL_LIVES - 1, life.getLives());
        assertEquals(INITIAL_HEALTH, life.getCurrentHealth());
        life.loseHealth(life.getCurrentHealth());
        assertEquals(INITIAL_LIVES - 1 - 1, life.getLives());
        assertEquals(INITIAL_HEALTH, life.getCurrentHealth());
        life.loseLife();
        assertEquals(0, life.getLives());
        assertEquals(0, life.getCurrentHealth());
    }
}
