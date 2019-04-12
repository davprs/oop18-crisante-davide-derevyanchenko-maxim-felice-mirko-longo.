package model.bullet;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import model.Entity;

/**
 * Implementation of Bullet interface.
 */

public class BulletImpl implements Bullet {

    private static final int DAMAGE_UNIT = 400;
    private static final double WIDTH = 50;
    private static final double HEIGHT = 50;
    private final int damage;
    private final double speed;
    private Point2D position;
    private Point2D target;
    private double movX;
    private double movY;
    private final double angle;
    private final Dimension2D fieldSize;

    /**
     * Build a new Bullet.
     * @param level defines the speed.
     * @param src the starting position of the Bullet.
     * @param target the target position.
     * @param fieldSize the field width and height.
     */
    public BulletImpl(final int level, final Point2D src, final Point2D target, final Dimension2D fieldSize) {
        this.speed = level * 2;
        this.damage = level * BulletImpl.DAMAGE_UNIT;
        this.fieldSize = fieldSize;
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
        return new Rectangle2D(position.getX(), position.getY(), BulletImpl.WIDTH, BulletImpl.HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final Entity entity) {
        final boolean isIntersected = entity.getBoundary().intersects(this.getBoundary());
        if (isIntersected) {
            this.destroy();
            entity.takeDamage(this.damage);
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
