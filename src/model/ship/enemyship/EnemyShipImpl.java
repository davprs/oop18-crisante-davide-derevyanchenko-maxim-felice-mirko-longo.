package model.ship.enemyship;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import model.Entity;
import model.ship.AbstractShip;

/**
 * Implementation of EnemyShip interface.
 */
public class EnemyShipImpl extends AbstractShip implements EnemyShip {

    private static final int DELAY = (int) (Math.random() * 800 + 200);
    private static final double WIDTH = 80;
    private static final double HEIGHT = 80;
    private static final int STARTING_LIVES = 1;
    private static final int STARTING_HEALTH = 1000;
    private Point2D position;
    private final double speed;
    private int framesFromShoot;
    private int level;
    private int framesToShoot;
    private boolean shootingAvailable;

    /**
     * Build a new EnemyShip.
     * @param level is the level (fastness, power of bullets)
     * @param timeToShoot frames passing from one bullet-shot to the next one
     * @param fieldSize the field width and height.
     */
    public EnemyShipImpl(final int level, final int timeToShoot, final Dimension2D fieldSize) {
        super(STARTING_LIVES, STARTING_HEALTH);
        this.level = level;
        this.speed = level;
        this.framesToShoot = timeToShoot;
        this.position = new Point2D(Math.random() * fieldSize.getWidth(), Math.random() * fieldSize.getHeight());
    }

    /**
     * Build a customizable level Bullet.
     * @param level is the level (fastness, power of bullets)
     * @param fieldSize the field width and height.
     */
    public EnemyShipImpl(final int level, final Dimension2D fieldSize) {
        this(level, DELAY, fieldSize);
    }

    /**
     * Build a simple EnemyShip.
     * @param fieldSize the field width and height.
     */
    public EnemyShipImpl(final Dimension2D fieldSize) {
        this(1, fieldSize);
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
    public boolean intersects(final Entity entity) {
        final boolean isIntersected = entity.getBoundary().intersects(this.getBoundary());
        if (isIntersected) {
            this.destroy();
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
    public synchronized void update(final Point2D position) {
        this.position = this.position.add(position);
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
    protected Point2D getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Dimension2D getDimension() {
        return new Dimension2D(WIDTH, HEIGHT);
    }
}
