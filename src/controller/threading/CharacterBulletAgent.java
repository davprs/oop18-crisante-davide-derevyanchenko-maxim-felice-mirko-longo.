package controller.threading;

import controller.field.EnemyController;
import controller.field.EntityController;
import controller.field.GameController;
import controller.field.MeteorController;

/**
 * Class that creates a thread for the character bullets that updates its moves and checks collisions with enemies and meteors.
 *
 */
public class CharacterBulletAgent extends EntityAgent {

    /**
     * 
     * Constructor of a CharacterBulletAgent.
     * 
     * @param entityController the character controller to be executed
     * @param gameController the controller of the game
     */
    public CharacterBulletAgent(final EntityController entityController, final GameController gameController) {
        super(entityController, gameController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void intersectChecker() {
        for (final EnemyController enemyController : this.getFieldController().getEnemies()) {
            this.getEntity().intersects(enemyController.getEntity());
        }
        for (final MeteorController meteorController : this.getFieldController().getMeteors()) {
            this.getEntity().intersects(meteorController.getEntity());
        }
    }

}
