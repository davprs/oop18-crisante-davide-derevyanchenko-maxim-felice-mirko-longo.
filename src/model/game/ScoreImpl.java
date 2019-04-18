package model.game;

/**
 * Implementation of Score interface.
 *
 */
public class ScoreImpl implements Score {

    private int score;

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void addScore(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Only positive numbers");
        }
        this.score += value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized int getScorePoints() {
        return this.score;
    }

}
