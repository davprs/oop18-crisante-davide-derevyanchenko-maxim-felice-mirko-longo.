package model.powerup;

import controller.field.CharacterController;

/**
 * 
 * Makes the spaceship immune to enemies.
 *
 */
public class ImmunityPowerUp implements TemporaryPowerUp {
    private static final long DURATION = 10;
    private final CharacterController character;

    /**
     * 
     * @param character the character controller.
     */
    public ImmunityPowerUp(final CharacterController character) {
        this.character = character;
    }

    /**
     * Method that sets on true the value of the immunity of the character ship.
     */
    @Override
    public void run() {
        this.character.changeImmunity();
        new TimeAgent(this, DURATION);
    }

    /**
     * Method that sets on false the value of the immunity of the character ship.
     */
    @Override
    public void stop() {
        this.character.changeImmunity();
    }

}
