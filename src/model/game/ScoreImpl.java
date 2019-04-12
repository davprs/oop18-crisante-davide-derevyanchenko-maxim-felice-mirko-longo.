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
    public void addScore(final int value) {
        this.score += value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScorePoints() {
        return this.score;
    }

}
