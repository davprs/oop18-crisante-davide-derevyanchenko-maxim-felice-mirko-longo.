package controller.agents.entities;

import java.util.List;

import controller.game.GameController;
import controller.game.field.FieldController;
import controller.game.field.entities.EnemyController;

/**
 * 
 * Agent that administrates the enemies spawns.
 *
 */
public class BulletAgent extends Thread{

    private final GameController gameController;
    private final FieldController fieldController;
    private List<EnemyController> enemyList;
    
    public BulletAgent (final GameController gameController, final FieldController fieldController) {
        this.gameController = gameController;
        this.fieldController = fieldController;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while(!gameController.isInPause() && !gameController.isEnded()) {
            this.enemyList = fieldController.getEnemies();
            
            for (EnemyController enemy : this.enemyList) {
                if (enemy.canShoot()) {
                    this.fieldController.addEnemyBullet(enemy.shoot());
                }
            }
        }
    }
}
