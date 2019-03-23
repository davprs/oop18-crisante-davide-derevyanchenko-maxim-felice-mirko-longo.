package model.ship;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Entity;
import model.bullet.Bullet;

/**
 * Class that represents the character ship.
 *
 */
public class CharacterShipImpl implements CharacterShip {

    private static final double WIDTH = 100;
    private static final double HEIGHT = 100;
    private static final double START_X = 10;
    private static final double START_Y = 10;
    private final ImageView image;
    private Point2D position;
    private final Dimension2D dimension;
    private boolean isMoving;

    /**
     * 
     * @param image the skin of the ship
     */
    public CharacterShipImpl(final Image image) {
        this.image = new ImageView();
        this.image.setImage(image);
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
    public ImageView getImageView() {
        return this.image;
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
}
