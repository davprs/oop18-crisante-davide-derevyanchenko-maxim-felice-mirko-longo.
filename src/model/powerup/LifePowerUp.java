package model.powerup;

import model.Life;

/**
 *  adds a life powerup.
 */
public class LifePowerUp implements PowerUp {

    private final Life life;

    /**
     * Build a LifePowerUp.
     * @param life is the Life of the CharacterShip.
     */
    public LifePowerUp(final Life life) {
        this.life = life;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        this.life.addLife();
    }

}
