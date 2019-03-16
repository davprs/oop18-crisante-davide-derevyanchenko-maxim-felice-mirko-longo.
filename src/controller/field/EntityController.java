package controller.field;

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

}
