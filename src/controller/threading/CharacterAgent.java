package controller.threading;

import controller.field.EntityController;
import controller.field.FieldController;

/**
 * Class that creates a thread for the character and updates its moves.
 */
public class CharacterAgent extends EntityAgent {

    /**
     * Constructor of a CharacterAgent.
     * 
     * @param entity the character controller to be executed
     * @param fieldController the FieldController of the game
     */
    public CharacterAgent(final EntityController entity, final FieldController fieldController) {
        super(entity, fieldController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void intersectChecker() { }
}
