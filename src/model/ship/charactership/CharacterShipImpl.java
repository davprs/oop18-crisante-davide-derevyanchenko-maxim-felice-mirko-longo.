package model.ship.charactership;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import model.Entity;
import model.ship.AbstractShip;

/**
 * Class that represents the character ship.
 *
 */
public class CharacterShipImpl extends AbstractShip implements CharacterShip {

    private static final int STARTING_LIVES = 3;
    private static final int STARTING_HEALTH = 1000;
    private static final double WIDTH = 100;
    private static final double HEIGHT = 100;
    private static final double SPEED = 0.5;
    private final Dimension2D dimension;
    private Point2D position;
    private boolean isMoving;

    /**
     * Constructor method of the CharacterShipImpl.
     * 
     * @param initialPosition the initial position of the character ship
     */
    public CharacterShipImpl(final Point2D initialPosition) {
        super(STARTING_LIVES, STARTING_HEALTH);
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
    public void endGame() {
        for (int i = this.getLife().getLives(); i > 0; i--) {
            this.destroy();
        }
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
        return this.dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D shoot() {
        return null;
    }
}
