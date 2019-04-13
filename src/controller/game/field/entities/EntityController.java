package controller.game.field.entities;

import model.entity.Entity;

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
     *  Method that gives the entity of the controller.
     *
     *  @return the entity of the controller
     */
    Entity getEntity();

    /**
     * Method that executes the destroying animation.
     */
    void destroy();
}
