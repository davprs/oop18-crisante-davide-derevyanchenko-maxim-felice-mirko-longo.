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
     * 
     * @return the amount of health left to the life
     */
    int getHealth();

    /**
     * 
     * @return the amount of lives left
     */
    int getLives();
}
