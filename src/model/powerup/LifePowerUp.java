package model.powerup;

import model.game.Life;

/**
 *  Power Up that adds a life to the player.
 */
public class LifePowerUp implements PowerUp {

    private final Life life;

    /**
     * Build this PowerUp.
     * @param life is the Life of the CharacterShip.
     */
    public LifePowerUp(final Life life) {
        this.life = life;
    }

    /**
     * Method to invoke to add the life.
     */
    @Override
    public void run() {
        this.life.addLife();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Life PowerUp";
    }

}
