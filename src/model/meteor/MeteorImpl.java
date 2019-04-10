package model.meteor;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import model.Entity;

/**
 * Implementation of Meteor interface.
 */
public class MeteorImpl implements Meteor {

    private static final int DAMAGE_UNIT = 300;
    private static final double WIDTH = 100;
    private static final double HEIGHT = 100;
    private final int damage;
    private final double speed;
    private Point2D position;
    private final double angle;
    private double movX;
    private double movY;
    private Point2D target;
    private Dimension2D fieldSize;

    /**
     * Build a new Meteor.
     * @param level defines the speed and power.
     * @param src the starting position of the Meteor.
     * @param target the target position.
     * @param fieldSize the field width and height.
     */
    public MeteorImpl(final int level, final Point2D src, final Point2D target, final Dimension2D fieldSize) {
        this.speed = level * 3;
        this.fieldSize = fieldSize;
        this.damage = level * MeteorImpl.DAMAGE_UNIT;
        this.position = src;
        this.angle = Math.atan2(Math.max(src.getX(), target.getX()) - Math.min(src.getX(), target.getX()), Math.max(src.getY(), target.getY()) - Math.min(src.getY(), target.getY()));
        this.movY = Math.abs(-Math.pow(Math.sin(angle), 2) + 1);
        this.movX = Math.abs(-Math.pow(Math.cos(angle), 2) + 1);
        if (target.getX() < src.getX()) {
            movX *= -1;
        }
        if (target.getY() < src.getY()) {
            movY *= -1;
        }
        this.target = new Point2D(src.getX() + movX, src.getY() + movY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getAngle() {
        return this.angle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Rectangle2D getBoundary() {
        return new Rectangle2D(position.getX(), position.getY(), MeteorImpl.WIDTH, MeteorImpl.HEIGHT);
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
    public synchronized void update() {
        position = new Point2D(position.getX() + (movX * speed), position.getY() + (movY * speed));
        target = new Point2D(target.getX() + (movX * speed), target.getY() + (movY * speed));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return !(position.getX() > this.fieldSize.getWidth() || position.getY() > this.fieldSize.getHeight()
                || position.getX() < 0 || position.getY() < 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void takeDamage(final int damage) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        this.position = new Point2D(-1, -1);
    }
}
