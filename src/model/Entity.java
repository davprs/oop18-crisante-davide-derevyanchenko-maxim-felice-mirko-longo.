package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

/**
 * An interface that represents an Entity.
 */
public interface Entity {

    /**
     * 
     * @return the rectangle that represents the boundary of the entity
     */
    Rectangle2D getBoundary();

    /**
     * 
     * @param entity the entity which should be checked the intersection with
     * 
     * @return true if the entity is intersected, false otherwise
     */
    boolean intersects(Entity entity);

    /**
     * 
     * @return the ImageView that represents that entity
     */
    ImageView getImageView();

    /**
     * @return true if the entity is still alive, false otherwise
     */
    boolean isAlive();
}
