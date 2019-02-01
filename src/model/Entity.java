package model;

/**
 * an interface that represents an Entity.
 */
public interface Entity {

    /**
     * method to move an Entity.
     * 
     * @param x the horizontal position.
     * @param y the vertical position.
     * 
     */
    void move(int x, int y);
}
