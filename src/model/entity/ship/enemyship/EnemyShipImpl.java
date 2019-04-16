package model.entity.ship.enemyship;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import model.entity.Entity;
import model.entity.ship.AbstractShip;
import utilities.GameUtils;

/**
 * Implementation of EnemyShip interface.
 */
public class EnemyShipImpl extends AbstractShip implements EnemyShip {

    private static final int DELAY = (int) (Math.random() * 600 + 200);
    private static final double DIMENSION_PROPORTION = 0.04;
    private static final int STARTING_LIVES = 1;
    private static final int STARTING_HEALTH = 1000;
    private static final int SCORE_POINTS = 500;
    private static final double SPEED_UNIT = 1;
    private Point2D position;
    private final Dimension2D shipDimension;
    private final double speed;
    private int framesFromShoot;
    private int level;
    private int framesToShoot;
    private boolean shootingAvailable;
    private boolean frozen;

    /**
     * Build a new EnemyShip.
     * @param level is the level (fastness, power of bullets)
     * @param timeToShoot frames passing from one bullet-shot to the next one
     * @param fieldSize the field width and height.
     */
    public EnemyShipImpl(final int level, final int timeToShoot, final Dimension2D fieldSize) {
        super(STARTING_LIVES, (int) GameUtils.transform(STARTING_HEALTH, level));
        this.level = level;
        this.speed = GameUtils.transform(SPEED_UNIT, level);
        this.framesToShoot = timeToShoot;
        this.position = new Point2D(Math.random() * fieldSize.getWidth(), Math.random() * fieldSize.getHeight());
        final double xSize = fieldSize.getWidth() * DIMENSION_PROPORTION;
        this.shipDimension = new Dimension2D(xSize, xSize);
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
     * {@inheritDoc}
     */
    @Override
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
    public synchronized Point2D shoot() {
        this.framesFromShoot = 0;
        this.shootingAvailable = false;
        return this.position.add(this.shipDimension.getWidth() / 2, this.shipDimension.getHeight() / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Point2D position) {
        if (!frozen) {
            this.position = this.position.add(position);
            this.framesFromShoot++;
            if (framesFromShoot >= framesToShoot) {
                this.shootingAvailable = true;
            }
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
    protected synchronized Point2D getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Dimension2D getDimension() {
        return new Dimension2D(this.shipDimension.getWidth(), this.shipDimension.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScorePoints() {
        return (int) GameUtils.transform(SCORE_POINTS, this.level);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeFreeze() {
        this.frozen = !this.frozen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized boolean isFrozen() {
        return this.frozen;
    }
}
