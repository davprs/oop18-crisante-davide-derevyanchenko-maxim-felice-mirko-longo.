package model;

/**
 * Implementation of Life interface.
 */
public final class LifeImpl implements Life {

    private static final int DEFAULT_HEALTH = 1000;
    private static final int DEFAULT_LIVES = 1;
    private final int health;
    private int lives;
    private int currentHealth;

    private LifeImpl(final int lives, final int startingHealth) { 
        this.lives = lives;
        this.currentHealth = startingHealth;
        this.health = startingHealth;
    }

    /**
     * Return a Life with default configuration. 
     * @return a default Life
     */
    public static Life createDefaultLife() {
        return new LifeImpl(DEFAULT_LIVES, DEFAULT_HEALTH);
    }

    /**
     * Return a Life with starting health specified from the parameter.
     * @param startingHealth the desired starting health
     * @return a Life
     */
    public static Life createLifeFromStartingHealth(final int startingHealth) {
        return new LifeImpl(DEFAULT_LIVES, startingHealth);
    }

    /**
     * Return a Life with number of lives specified from the parameter.
     * @param lives the desired number of lives
     * @return a powered Life 
     */
    public static Life createLifeWithMoreLives(final int lives) {
        return new LifeImpl(lives, DEFAULT_HEALTH);
    }

    /**
     * Return a customized Life with number of lives and starting health specified from the parameters.
     * @param lives the desired number of lives
     * @param startingHealth the desired starting health
     * @return a customized Life 
     */
    public static Life createCustomizedLife(final int lives, final int startingHealth) {
        return new LifeImpl(lives, startingHealth);
    }

    /**
     * {@inheritDoc}
     */
    public void loseHealth(final int health) {
        if (health < 0) {
            throw new IllegalArgumentException();
        }
        this.currentHealth -= health;
        if (this.currentHealth <= 0) {
            loseLife();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void addHealth(final int health) {
        if (health < 0) {
            throw new IllegalArgumentException();
        }
        this.currentHealth += health;
        if (this.currentHealth >= this.health) {
            addLife();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLife() {
        this.lives++;
        this.currentHealth = this.health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loseLife() {
        this.lives--;
        this.currentHealth = this.health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return this.lives > 0 && this.currentHealth > 0;
    }

}
