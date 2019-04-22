package controller.agents.entities;

import java.util.List;
import controller.game.GameController;
import controller.game.field.entities.EnemyController;

/**
 * 
 * Agent that administers the bullets spawns.
 *
 */
public class BulletAgent extends Thread {

    private static final int WAITING_TIME = 50;
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
            final List<EnemyController> enemies = this.gameController.getFieldController().getEnemies();
            enemies.stream().forEach(x->{
                if (x.canShoot()) {
                    this.gameController.getFieldController().addEnemyBullet(x.shoot());
                }
            });
            try {
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
