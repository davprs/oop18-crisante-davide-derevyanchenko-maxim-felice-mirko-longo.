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
    private static final double DIMENSION_PROPORTION = 0.04;
    private static final double SPEED = 0.25;
    private final Dimension2D dimension;
    private Point2D position;

    /**
     * Constructor method of the CharacterShipImpl.
     * 
     * @param initialPosition the initial position of the character ship
     * @param fieldDimension the dimension of the field which is considered while giving a dimension to the ship
     */
    public CharacterShipImpl(final Point2D initialPosition, final Dimension2D fieldDimension) {
        super(STARTING_LIVES, STARTING_HEALTH);
        final double sizeDimension = fieldDimension.getWidth() * DIMENSION_PROPORTION;
        this.dimension = new Dimension2D(sizeDimension, sizeDimension);
        this.position = initialPosition.subtract(this.dimension.getWidth() / 2, this.dimension.getHeight() / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void update(final double x, final double y) {
        this.position = new Point2D(x, y);
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
        return this.position;
    }
}
