package model;

import java.util.Optional;

/**
 * an interface that represents the battle field.
 *
 */
public interface Field {

    /**
     * method that returns the entity that stays in a particular position of the field.
     * 
     * @param x the x coordinate of the position to analyze
     * @param y the y coordinate of the position to analyze
     * @return the Optional of the entity that is in this position
     */
    Optional<Entity> entityAt(int x, int y);

    /**
     * method that adds an entity on the battle field.
     * 
     * @param entity the entity to add
     * @param x the x coordinate of the position where to add the entity
     * @param y the y coordinate of the position where to add the entity
     * 
     * @return true if the entity was created, false otherwise
     */
    boolean addEntity(Entity entity, int x, int y);

    /**
     * method that destroys the entity that is located in coordinates passed as parameters.
     * 
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * 
     * @return true if the cell is occupied by an entity, false otherwise
     */
    boolean destrotyEntity(int x, int y);
}
