package model;

import javafx.geometry.Rectangle2D;

/**
 * An interface that represents an Entity.
 */
public interface Entity {

    /**
     * Method that gets the boundary of this entity.
     * 
     * @return the rectangle that represents the boundary of the entity
     */
    Rectangle2D getBoundary();

    /**
     * Method that checks the intersection between this entity and the entity received as parameter.
     * 
     * @param entity the entity which should be checked the intersection with
     * 
     * @return true if the entity is intersected, false otherwise
     */
    boolean intersects(Entity entity);

    /**
     * Method that says if this entity is still alive or not.
     * 
     * @return true if the entity is still alive, false otherwise
     */
    boolean isAlive();
}
