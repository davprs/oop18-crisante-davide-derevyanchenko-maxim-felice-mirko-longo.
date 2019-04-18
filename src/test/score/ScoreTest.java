package test.score;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.game.Score;
import model.game.ScoreImpl;

/**
 * 
 * Class of Junit Test for score.
 *
 */
public class ScoreTest {
    private Score score;
    private static final int VALUE = 100; 
    private static final int NEGATIVE_VALUE = -100;
    /**
     * Test getter.
     */
    @Test
    public void testGetter() {
        this.score = new ScoreImpl();
        assertEquals(0, this.score.getScorePoints());
    }
    /**
     * 
     */
    @Test
    public void testAddScore() {
        this.score = new ScoreImpl();
        this.score.addScore(VALUE);
        assertEquals(VALUE, this.score.getScorePoints());
    }
    /**
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOnlyPositiveNumbers() {
        this.score = new ScoreImpl();
        this.score.addScore(NEGATIVE_VALUE);
    }

    /**
     * 
     */
    @Test
    public void testMultipleAddScore() {
        this.score = new ScoreImpl();
        for (int i = 0; i < VALUE; i++) {
            this.score.addScore(i);
        }
        assertEquals((VALUE * (VALUE - 1)) / 2, this.score.getScorePoints());
    }
}
