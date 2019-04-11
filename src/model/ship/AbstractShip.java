package model.ship;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import model.Entity;
import model.Life;
import model.LifeImpl;

/**
 * Class that represents an abstract Ship.
 *
 */
public abstract class AbstractShip implements Ship {

    private final Life life;

    /**
     * The constructor for the abstract Ship.
     * @param lives the number of lives
     * @param startingHealth the number of initial health points
     */
    public AbstractShip(final int lives, final int startingHealth) {
        this.life = LifeImpl.createCustomizedLife(lives, startingHealth);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Rectangle2D getBoundary() {
        return new Rectangle2D(getPosition().getX(), getPosition().getY(), getDimension().getWidth(), getDimension().getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean intersects(Entity entity);

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
    public void takeDamage(final int damage) {
        this.life.loseHealth(damage);
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
    public abstract Point2D shoot();

    /**
     * Gets the Life.
     * @return the Life
     */
    public Life getLife() {
        return this.life;
    }

    /**
     * Gets the position of the ship.
     * @return the position of the ship
     */
    protected abstract Point2D getPosition();

    /**
     * Gets the dimension of the ship.
     * @return the dimension of the ship
     */
    protected abstract Dimension2D getDimension();
}
