package test.powerup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Test;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import model.entity.ship.charactership.CharacterShipImpl;
import model.entity.ship.enemyship.EnemyShip;
import model.entity.ship.enemyship.EnemyShipImpl;
import model.game.Life;
import model.game.LifeImpl;
import model.powerup.FreezePowerUp;
import model.powerup.ImmunityPowerUp;
import model.powerup.LifePowerUp;
import model.powerup.NukePowerUp;

/**
 * 
 * 
 * Class that test the functionality of the powerUp class.
 */
public class PowerUp {
    private static final Dimension2D DIMENSION = new Dimension2D(100, 150);
    private static final Point2D POINT2D = new Point2D(10, 15);
    private static final Point2D POINT2D2 = new Point2D(15, 20);

    /**
     * Method that the freeze powerUp.
     */
    @Test
    public void testFreeze() {
        ArrayList<EnemyShip> enemy = new ArrayList<EnemyShip>();
        enemy.add(new EnemyShipImpl(DIMENSION, POINT2D));
        enemy.add(new EnemyShipImpl(DIMENSION, POINT2D));
        enemy.add(new EnemyShipImpl(DIMENSION, POINT2D));
        FreezePowerUp freeze = new FreezePowerUp(enemy);
        enemy.get(0);
        enemy.get(1);
        enemy.get(2);
        final Point2D positionInitial0 = new Point2D(enemy.get(0).getBoundary().getMinX(),
                enemy.get(0).getBoundary().getMinY());
        final Point2D positionInitial1 = new Point2D(enemy.get(1).getBoundary().getMinX(),
                enemy.get(1).getBoundary().getMinY());
        final Point2D positionInitial2 = new Point2D(enemy.get(2).getBoundary().getMinX(),
                enemy.get(2).getBoundary().getMinY());
        freeze.run();
        enemy.get(0).update(POINT2D2);
        enemy.get(1).update(POINT2D2);
        enemy.get(2).update(POINT2D2);
        final Point2D position0 = new Point2D(enemy.get(0).getBoundary().getMinX(),
                enemy.get(0).getBoundary().getMinY());
        final Point2D position1 = new Point2D(enemy.get(1).getBoundary().getMinX(),
                enemy.get(1).getBoundary().getMinY());
        final Point2D position2 = new Point2D(enemy.get(2).getBoundary().getMinX(),
                enemy.get(2).getBoundary().getMinY());
        assertTrue(positionInitial0.getX() == position0.getX() && positionInitial0.getY() == position0.getY());
        assertTrue(positionInitial1.getX() == position1.getX() && positionInitial1.getY() == position1.getY());
        assertTrue(positionInitial2.getX() == position2.getX() && positionInitial2.getY() == position2.getY());
    }

    /**
     * Method that the nuke powerUp.
     */
    @Test
    public void nuke() {
        ArrayList<EnemyShip> enemy = new ArrayList<EnemyShip>();
        enemy.add(new EnemyShipImpl(DIMENSION, POINT2D));
        enemy.add(new EnemyShipImpl(DIMENSION, POINT2D));
        enemy.add(new EnemyShipImpl(DIMENSION, POINT2D));
        NukePowerUp nuke = new NukePowerUp(enemy);
        enemy.get(0);
        enemy.get(1);
        enemy.get(2);
        nuke.run();
        assertFalse(enemy.get(0).isAlive());
        assertFalse(enemy.get(1).isAlive());
        assertFalse(enemy.get(2).isAlive());
    }

    /**
     * Method that the life powerUp.
     */
    @Test

    public void life() {
        Life life = LifeImpl.createDefaultLife();
        LifePowerUp lifePowerUp = new LifePowerUp(life);
        final int initialLife = life.getLives();
        lifePowerUp.run();
        assertEquals(initialLife + 1, life.getLives());

    }

    /**
     * Method that the immunity powerUp.
     */
    @Test
    public void immunity() {
        final Point2D point2D = new Point2D(10, 15);
        final int damage = 5;
        final Dimension2D dimension = new Dimension2D(100, 150);
        CharacterShipImpl ship = new CharacterShipImpl(point2D, dimension);
        ImmunityPowerUp immunity = new ImmunityPowerUp(ship);
        Life life = ship.getLife();
        immunity.run();
        ship.destroy();
        ship.takeDamage(damage);
        assertEquals(life.getLives(), ship.getLife().getLives());
        assertEquals(life.getCurrentHealth(), ship.getLife().getCurrentHealth());
    }
}
