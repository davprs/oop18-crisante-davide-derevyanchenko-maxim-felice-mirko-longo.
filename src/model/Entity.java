package model;

/**
 * an interface that represents an Entity.
 */
public interface Entity {

    /**
     * @param x the horizontal position.
     * @param y the vertical position.
     * 
     * method to move an Entity.
     */
    void move(int x, int y);
}
