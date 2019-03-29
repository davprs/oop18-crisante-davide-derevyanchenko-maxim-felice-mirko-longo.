package controller.field;

import model.Entity;

/**
 * Class that represents a generic entity controller interface.
 *
 */
public interface EntityController {

    /**
     *  Method that updates the position of the entity.
     */
    void update();

    /**
     *  Method that orders to draw the entity to the view.
     */
    void draw();

    /**
     *  Method that controls if this entity intersects another entity.
     *
     *  @param entityController the entity controller which the entity must be controlled with
     *
     *  @return true if the entities intersects, false otherwise
     */
    boolean intersects(EntityController entityController);

    /**
     *  Method that gives the entity of the controller.
     *
     *  @return the entity of the controller
     */
    Entity getEntity();

    /**
     * Checks if the entity is still alive or not.
     * 
     * @return true if the entity was not destroyed, false otherwise
     */
    boolean isAlive();
}
