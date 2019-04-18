package controller.agents.entities;

import controller.game.GameController;
import controller.game.field.entities.EntityController;
import model.entity.Entity;
import utilities.ErrorLog;

/**
 * Class that creates a thread for an entity that updates its moves and checks collisions.
 *
 */
public abstract class EntityAgent extends Thread {

    private static final long WAITING_TIME = 10;
    private final EntityController entityController;
    private final GameController gameController;

    /**
     * Constructor of an EntityAgent.
     * 
     * @param entity the character controller to be executed
     * @param gameController the GameController of the game
     */
    public EntityAgent(final EntityController entity, final GameController gameController) {
        this.entityController = entity;
        this.gameController = gameController;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        while (this.entityController.getEntity().isAlive() && !this.gameController.isEnded()) {

            try {
                if (!this.gameController.isInPause()) {
                    this.entityController.update();
                    this.intersectChecker();
                }
                sleep(WAITING_TIME);
            } catch (InterruptedException e) {
                ErrorLog.getLog().printError();
                System.exit(0);
            }
        }
        this.entityController.destroy();
    }

    /**
     * Method that gets the EnityController of the entity that is executed by this thread.
     * 
     * @return the EntityController that this thread executes
     */
    protected Entity getEntity() {
        return this.entityController.getEntity();
    }

    /**
     * Method that gets the game controller.
     * 
     * @return the game controller
     */
    protected GameController getGameController() {
        return this.gameController;
    }

    /**
     * Method that defines how the intersection between entities should be checked.
     */
    protected abstract void intersectChecker();
}
