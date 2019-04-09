package controller.threading;

import controller.field.EntityController;
import controller.field.GameController;

/**
 * Class that creates a thread for the enemy ships, meteors and enemy bullets, that updates its moves and checks collisions with the character ship.
 *
 */
public class AntagonistsAgent extends EntityAgent {

    /**
     * 
     * @param entity the character controller to be executed
     * @param gameController the controller of the game
     */
    public AntagonistsAgent(final EntityController entity, final GameController gameController) {
        super(entity, gameController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void intersectChecker() {
        this.getEntity().intersects(this.getFieldController().getCharacter());
    }

}
