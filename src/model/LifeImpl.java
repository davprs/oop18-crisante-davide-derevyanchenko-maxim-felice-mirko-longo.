package model;

/**
 * Implementation of Life interface.
 */
public class LifeImpl implements Life {

    private int life = 1;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLife() {
        this.life++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loseLife() {
        this.life--;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return this.life > 0;
    }

}
