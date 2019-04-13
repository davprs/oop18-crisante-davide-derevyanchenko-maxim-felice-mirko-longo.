package controller.agents.entities;

import controller.game.GameController;
import controller.game.field.entities.EntityController;

/**
 * Class that creates a thread for the character and updates its moves.
 */
public class CharacterAgent extends EntityAgent {

    /**
     * Constructor of a CharacterAgent.
     * 
     * @param entityController the character controller to be executed
     * @param gameController the controller of the game
     */
    public CharacterAgent(final EntityController entityController, final GameController gameController) {
        super(entityController, gameController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void intersectChecker() { }
}
