package model;

import java.awt.MouseInfo;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CharacterShipImpl implements CharacterShip {

    private static final double WIDTH = 0;
    private static final double HEIGHT = 0;
    private static final double START_X = 0;
    private static final double START_Y = 0;
    private final ImageView image;
    private Point2D position;
    private final Dimension2D dimension;
    private boolean isMoving;

    public CharacterShipImpl(final Image image) {
        this.image = new ImageView(image);
        this.dimension = new Dimension2D(WIDTH, HEIGHT);
        this.position = new Point2D(START_X, START_Y);
        this.isMoving = false;
    }
    
    @Override
    public void update(double x, double y) {
        if (isMoving) {
            position = new Point2D(x, y);
        }
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(position.getX(), position.getY(), dimension.getWidth(), dimension.getHeight());
    }

    @Override
    public boolean intersects(Entity entity) {
        return entity.getBoundary().intersects(this.getBoundary());
    }

    @Override
    public void shoot() {

    }
    
    @Override
    public Image getImage() {
        return this.image.getImage();
    }
    
    @Override
    public void changeMoving() {
        this.isMoving = !this.isMoving;
    }
    
    @Deprecated
    public void update(double time) {
        
    }

}
