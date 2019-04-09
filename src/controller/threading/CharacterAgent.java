package controller.threading;

import controller.field.EntityController;
import controller.field.GameController;

/**
 * Class that creates a thread for the character and updates its moves.
 */
public class CharacterAgent extends EntityAgent {

    /**
     * Constructor of a CharacterAgent.
     * 
     * @param entity the character controller to be executed
     * @param gameController the controller of the game
     */
    public CharacterAgent(final EntityController entity, final GameController gameController) {
        super(entity, gameController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void intersectChecker() { }
}
