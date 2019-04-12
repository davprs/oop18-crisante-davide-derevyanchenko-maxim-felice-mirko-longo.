package controller.threading;

import java.util.List;

import controller.field.EnemyController;
import controller.field.FieldController;
import controller.field.GameController;

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
