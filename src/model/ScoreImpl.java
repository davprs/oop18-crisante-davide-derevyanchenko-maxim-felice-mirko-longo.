package model;

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
    public int getScore() {
        return this.score;
    }

}
