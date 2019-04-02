package model.meteor;

import java.awt.Dimension;
import java.awt.Toolkit;


import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import model.Entity;

/**
 * Implementation of Meteor interface.
 *
 */
public class MeteorImpl implements Meteor {

    private static final int DAMAGE_UNIT = 300;
    private final int damage;
    private final ImageView image;
    private final double speed;
    private Point2D position;
    private final double angle;
    private double movX;
    private double movY;
    private Point2D target;

    /**
     * Build a new Meteor.
     * @param level defines the speed and power.
     * @param src the starting position of the Meteor.
     * @param target the target position.
     */
    public MeteorImpl(final int level, final Point2D src, final Point2D target) {
        this.image = new ImageView(utilities.EntitiesImageUtils.getMeteorImage(level));
        this.speed = level * 3;
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
    public Rectangle2D getBoundary() {
        return new Rectangle2D(position.getX(), position.getY(), image.getImage().getWidth(), image.getImage().getHeight());
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
    public ImageView getImageView() {
        return this.image;
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
