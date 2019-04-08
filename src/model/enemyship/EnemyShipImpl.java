package model.enemyship;

import java.awt.Toolkit;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import model.Entity;
import model.Life;
import model.LifeImpl;

/**
 * Implementation of EnemyShip interface.
 */
public class EnemyShipImpl implements EnemyShip {

    private static final int DELAY = (int) (Math.random() * 800 + 200);
    private static final double WIDTH = 100;
    private static final double HEIGHT = 100;
    private Point2D position;
    private final double speed;
    private int framesFromShoot;
    private int level;
    private int framesToShoot;
    private final Life life;
    private boolean shootingAvailable;

    /**
     * Build a new EnemyShip.
     * @param level is the level (fastness, power of bullets)
     * @param timeToShoot frames passing from one bullet-shot to the next one
     */
    public EnemyShipImpl(final int level, final int timeToShoot) {
        this.level = level;
        this.life = LifeImpl.createDefaultLife();
        this.speed = level;
        this.framesToShoot = timeToShoot;
        this.position = new Point2D(Math.random() * Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Math.random() * Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    }

    /**
     * Build a customizable level Bullet.
     * @param level is the level (fastness, power of bullets)
     */
    public EnemyShipImpl(final int level) {
        this(level, DELAY);
    }

    /**
     * Build a simple EnemyShip.
     */
    public EnemyShipImpl() {
        this(1);
    }

    /**
     * Get the difficulty level.
     * @return the level.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Return true if the EnemyShip can shoot.
     * @return true if the EnemyShip can shoot.
     */
    public boolean canShoot() {
        return this.shootingAvailable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Rectangle2D getBoundary() {
        return new Rectangle2D(position.getX(), position.getY(), WIDTH, HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final Entity entity) {
        final boolean isIntersected = entity.getBoundary().intersects(this.getBoundary());
        if (isIntersected) {
            entity.destroy();
        }
        return isIntersected;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public Point2D shoot() {
        this.framesFromShoot = 0;
        this.shootingAvailable = false;
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void update(final Point2D addPosition) {
        this.position.add(addPosition);
        this.framesFromShoot++;
        if (framesFromShoot >= framesToShoot) {
            this.shootingAvailable = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return !(this.life.getHealth() <= 0 && this.life.getLives() <= 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        this.takeDamage(this.life.getHealth());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void takeDamage(final int damage) {
        this.life.loseHealth(damage);
    }
}
