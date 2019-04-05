package controller.threading;

import controller.field.EnemyController;
import controller.field.EntityController;
import controller.field.FieldController;
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
     * @param entity the character controller to be executed
     * @param fieldController the FieldController of the game
     */
    public CharacterBulletAgent(final EntityController entity, final FieldController fieldController) {
        super(entity, fieldController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void intersectChecker() {
        for (final EnemyController enemy : this.getFieldController().getEnemies()) {
            this.getEntity().intersects(enemy);
        }
        for (final MeteorController meteor : this.getFieldController().getMeteors()) {
            this.getEntity().intersects(meteor);
        }
    }

}
