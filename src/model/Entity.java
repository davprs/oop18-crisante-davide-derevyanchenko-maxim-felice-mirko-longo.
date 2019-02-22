package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * An interface that represents an Entity.
 */
public interface Entity {
    
    Rectangle2D getBoundary();
    
    boolean intersects(Entity entity);
    
    Image getImage();
    
    void update(double time);
}
