package model.ship;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import model.Entity;
import model.Life;
import model.LifeImpl;
import model.bullet.Bullet;

/**
 * Class that represents the character ship.
 *
 */
public class CharacterShipImpl implements CharacterShip {

    private static final int STARTING_LIVES = 3;
    private static final int STARTING_HEALTH = 1000;
    private static final double WIDTH = 100;
    private static final double HEIGHT = 100;
    private static final double START_X = 10;
    private static final double START_Y = 10;
    private Point2D position;
    private final Dimension2D dimension;
    private boolean isMoving;
    private final Life life;

    /**
     * Constructor method of the CharacterShipImpl.
     */
    public CharacterShipImpl() {
        this.life = LifeImpl.createCustomizedLife(STARTING_LIVES, STARTING_HEALTH);
        this.dimension = new Dimension2D(WIDTH, HEIGHT);
        this.position = new Point2D(START_X, START_Y);
        this.isMoving = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double x, final double y) {
        if (isMoving) {
            position = new Point2D(x, y);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(position.getX(), position.getY(), dimension.getWidth(), dimension.getHeight());
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
    public Bullet shoot(final Point2D target) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return (this.life.getHealth() <= 0 && this.life.getLives() <= 0);
    }
}
