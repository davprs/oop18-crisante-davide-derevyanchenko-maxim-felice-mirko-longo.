package model;

/**
 * Represents the life in game.
 *
 */
public interface Life {

    /**
     * Lose a certain quantity of health.
     * @param health the health to lose
     */
    void loseHealth(int health);

    /**
     * Add a certain quantity of health.
     * @param health the health to add
     */
    void addHealth(int health);

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
