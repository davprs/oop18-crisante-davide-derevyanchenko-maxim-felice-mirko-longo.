package model.powerup;

import java.util.LinkedList;
import java.util.List;

import controller.EnemyController;
import controller.field.FieldController;

/**
 * Freeze all enemy for 10 seconds. 
 */
public class FreezePowerUp implements TemporaryPowerUp {
    private static final long DURATION = 10;
    private final FieldController field;
    private final List<EnemyController> enemies;

    /**
     * 
     * @param field the field power up.
     */
    public FreezePowerUp(final FieldController field) {
        this.field = field;
        this.enemies = new LinkedList<>();
    }
    /**
     * Method that delete all enemy .
     */
    @Override
    public void run() {
        this.enemies.addAll(field.getEnemies());
        for (final EnemyController enemy : enemies) {
            enemy.changeFreeze();
        }
        new TimeAgent(this, DURATION);
    }

    /**
     *   freeze the enemy for 10 seconds.
     */
    @Override
    public void stop() {
        for (final EnemyController enemy : enemies) {
            enemy.changeFreeze();
        }
        this.enemies.clear();
    }

}
