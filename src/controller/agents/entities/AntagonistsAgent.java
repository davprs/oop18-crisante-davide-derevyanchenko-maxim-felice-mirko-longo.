package controller.agents.entities;

import controller.game.GameController;
import controller.game.field.entities.EntityController;

/**
 * Class that creates a thread for the enemy ships, meteors and enemy bullets, that updates its moves and checks collisions with the character ship.
 *
 */
public class AntagonistsAgent extends EntityAgent {

    /**
     * 
     * @param entityController the character controller to be executed
     * @param gameController the controller of the game
     */
    public AntagonistsAgent(final EntityController entityController, final GameController gameController) {
        super(entityController, gameController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void intersectChecker() {
        this.getEntity().intersects(this.getGameController().getFieldController().getCharacter().getEntity());
    }
}
