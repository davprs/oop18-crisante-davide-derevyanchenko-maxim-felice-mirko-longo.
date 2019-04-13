package controller.agents.entities;

import controller.game.GameController;
import controller.game.field.entities.EnemyController;

/**
 * 
 * Agent that administers the bullets spawns.
 *
 */
public class BulletAgent extends Thread {

    private final GameController gameController;

    /**
     * Build the BulletAgent.
     * @param gameController the controller of the game
     */
    public BulletAgent(final GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while (!this.gameController.isInPause() && !this.gameController.isEnded()) {
            for (final EnemyController enemy : this.gameController.getFieldController().getEnemies()) {
                if (enemy.canShoot()) {
                    this.gameController.getFieldController().addEnemyBullet(enemy.shoot());
                }
            }
        }
    }
}
