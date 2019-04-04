package model.bullet;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import model.Entity;

/**
 * Implementation of Bullet interface.
 *
 */

public class BulletImpl implements Entity, Bullet {

    private static final int DAMAGE_UNIT = 400;
    private final int damage;
    private final double speed;
    private Point2D position;
    private Point2D target;
    private double movX;
    private double movY;
    private final double angle;
    private final Dimension2D imageDimension;

    /**
     * Build a new Bullet.
     * @param level defines the speed.
     * @param src the starting position of the Bullet.
     * @param target the target position.
     * @param imageDimension the width and height of the image on screen. 
     */
    public BulletImpl(final int level, final Point2D src, final Point2D target, final Dimension2D imageDimension) {
        this.imageDimension = imageDimension;
        this.speed = level * 2;
        this.damage = level * BulletImpl.DAMAGE_UNIT;
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
    public Rectangle2D getBoundary() {
        return new Rectangle2D(position.getX(), position.getY(), imageDimension.getWidth(), imageDimension.getHeight());
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
    public void update() {
        position = new Point2D(position.getX() + (movX * speed), position.getY() + (movY * speed));
        target = new Point2D(target.getX() + (movX * speed), target.getY() + (movY * speed));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        final Dimension canvasSize = Toolkit.getDefaultToolkit().getScreenSize().getSize();
        return !(position.getX() > canvasSize.getWidth() || position.getY() > canvasSize.getHeight()
                || position.getX() < 0 || position.getY() < 0);
    }

}
