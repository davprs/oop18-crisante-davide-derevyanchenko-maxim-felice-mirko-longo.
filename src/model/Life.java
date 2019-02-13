package model;

/**
 * Represents the life in game.
 *
 */
public interface Life {

    /**
     * Add a life.
     */
    void addLife();

    /**
     * Lose a life.
     */
    void loseLife();

    /**
     * Return true only if the entity is alive.
     * @return true if is alive.
     */
    boolean isAlive();
}
