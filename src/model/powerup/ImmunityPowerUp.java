package model.powerup;

import model.entity.ship.charactership.CharacterShip;

/**
 * PowerUp that makes the spaceship immune to any damage.
 *
 */
public class ImmunityPowerUp implements TemporaryPowerUp {

    private static final int DURATION = 10;
    private final CharacterShip characterShip;

    /**
     * Build this PowerUp.
     * @param characterShip is the Ship of the player
     */
    public ImmunityPowerUp(final CharacterShip characterShip) {
       this.characterShip = characterShip;
    }

    /**
     * Method to invoke that makes the Character Ship immune to any damage.
     */
    @Override
    public void run() {
        this.characterShip.setImmunity(true);
    }

    /**
     * Method to invoke that makes the CharacterShip not immune to any damage.
     */
    @Override
    public void stop() {
        this.characterShip.setImmunity(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDuration() {
        return DURATION;
    }

}
