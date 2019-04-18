package controller.agents.entities;

import java.util.List;

import controller.game.GameController;
import controller.game.field.entities.EnemyController;
import controller.game.field.entities.EntityController;
import controller.game.field.entities.MeteorController;

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
    protected void intersectChecker() {
        final List<EnemyController> enemies = this.getGameController().getFieldController().getEnemies();
        final List<MeteorController> meteors = this.getGameController().getFieldController().getMeteors();
        for (final EnemyController enemyController : enemies) {
            this.getEntity().intersects(enemyController.getEntity());
        }
        for (final MeteorController meteorController : meteors) {
            this.getEntity().intersects(meteorController.getEntity());
        }
    }

}
