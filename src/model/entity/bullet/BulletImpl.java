package model.entity.bullet;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import model.entity.Entity;
import utilities.GameUtils;

/**
 * Implementation of Bullet interface.
 */

public class BulletImpl implements Bullet {

    private static final int DEFAULT_SCREEN_X_SIZE = 1920;
    private static final int DAMAGE_UNIT = 500;
    private static final double X_DIMENSION_PROPORTION = 0.003;
    private static final double POPORTION_BETWEEN_SIZES = 7;
    private static final double SPEED_UNIT = 3.5;
    private final double speed;
    private final int damage;
    private Point2D position;
    private final Dimension2D shipDimension;
    private Point2D target;
    private final double movX;
    private final double movY;
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
        this.damage = GameUtils.transform(DAMAGE_UNIT, level);
        this.speed = (fieldSize.getWidth() / DEFAULT_SCREEN_X_SIZE) * SPEED_UNIT;
        this.fieldSize = fieldSize;
        this.position = src;
        this.angle = Math.atan2(src.getY() - target.getY(), src.getX() - target.getX());
        this.movY = -speed * Math.sin(this.angle);
        this.movX = -speed * Math.cos(this.angle);
        this.target = new Point2D(src.getX() + movX, src.getY() + movY);
        final double xSize = fieldSize.getWidth() * X_DIMENSION_PROPORTION;
        this.shipDimension = new Dimension2D(xSize, xSize * POPORTION_BETWEEN_SIZES);
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
        return new Rectangle2D(position.getX(), position.getY(), this.shipDimension.getWidth(), this.shipDimension.getHeight());
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
