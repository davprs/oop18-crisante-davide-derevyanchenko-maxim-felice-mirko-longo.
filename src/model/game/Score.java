package model.game;

/**
 * Interface that represents the Score game.
 *
 */
public interface Score {

    /**
     * Add a score.
     * @param value int value to set
     */
    void addScore(int value);

    /**
     * Get the Score.
     * @return the score
     */
    int getScorePoints();
}
