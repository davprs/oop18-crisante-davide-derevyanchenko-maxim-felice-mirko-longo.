package controller.threading;

import controller.field.EntityController;
import controller.field.FieldController;
import utilities.ErrorLog;

/**
 * Class that creates a thread for an entity that updates its moves and checks collisions.
 *
 */
public abstract class EntityAgent extends Thread {

    private static final long WAITING_TIME = 20;
    private final EntityController entity;
    private final FieldController fieldController;

    /**
     * Constructor of an EntityAgent.
     * 
     * @param entity the character controller to be executed
     * @param fieldController the FieldController of the game
     */
    public EntityAgent(final EntityController entity, final FieldController fieldController) {
        this.entity = entity;
        this.fieldController = fieldController;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        while (this.entity.isAlive()) {

            try {
                if (!this.fieldController.isInPause()) {
                    this.entity.update();
                    this.intersectChecker();
                }
                sleep(WAITING_TIME);
            } catch (InterruptedException e) {
                ErrorLog.getLog().printError();
                System.exit(0);
            }
        }
    }

    /**
     * Method that gets the EnityController of the entity that is executed by this thread.
     * 
     * @return the EntityController that this thread executes
     */
    protected EntityController getEntity() {
        return this.entity;
    }

    /**
     * Method that gets the generator of the entity, that is the opponent to the entity this thread executes.
     * 
     * @return the generator of an entity, that is the opponent to the entity that this thread executes
     */
    protected FieldController getFieldController() {
        return this.fieldController;
    }

    /**
     * Method that defines how the intersection between entities should be checked.
     */
    public abstract void intersectChecker();
}
