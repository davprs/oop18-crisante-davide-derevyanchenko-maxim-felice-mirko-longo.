package model.ship;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import model.Entity;
import model.Life;
import model.LifeImpl;

/**
 * Class that represents the character ship.
 *
 */
public class CharacterShipImpl implements CharacterShip {

    private static final int STARTING_LIVES = 3;
    private static final int STARTING_HEALTH = 1000;
    private static final double WIDTH = 100;
    private static final double HEIGHT = 100;
    private static final double SPEED = 0.5;
    private Point2D position;
    private final Dimension2D dimension;
    private boolean isMoving;
    private final Life life;

    /**
     * Constructor method of the CharacterShipImpl.
     * 
     * @param initialPosition the initial position of the character ship
     */
    public CharacterShipImpl(final Point2D initialPosition) {
        this.life = LifeImpl.createCustomizedLife(STARTING_LIVES, STARTING_HEALTH);
        this.dimension = new Dimension2D(WIDTH, HEIGHT);
        this.position = initialPosition.subtract(this.dimension.getWidth() / 2, this.dimension.getHeight() / 2);
        this.isMoving = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void update(final double x, final double y) {
        if (this.isMoving) {
            this.position = new Point2D(x, y);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Rectangle2D getBoundary() {
        return new Rectangle2D(this.position.getX(), this.position.getY(), this.dimension.getWidth(), this.dimension.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    public Point2D getCentralPosition() {
        final Point2D maxPos = new Point2D(this.getBoundary().getMaxX(), this.getBoundary().getMaxY());
        return this.position.midpoint(maxPos);
    }

    /**
     * {@inheritDoc}
     */
    public double getSpeed() {
        return SPEED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final Entity entity) {
        return entity.getBoundary().intersects(this.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeMoving() {
        this.isMoving = !this.isMoving;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D shoot() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return !(this.life.getHealth() <= 0 && this.life.getLives() <= 0);
    }
}
