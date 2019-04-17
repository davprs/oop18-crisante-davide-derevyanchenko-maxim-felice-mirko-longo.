package controller.agents;

import controller.game.GameController;
import controller.game.field.FieldController;
import controller.game.field.entities.CharacterController;
import controller.game.field.entities.EnemyController;
import controller.game.field.entities.MeteorController;
import javafx.geometry.Dimension2D;
import model.entity.ship.charactership.CharacterShip;
import utilities.ErrorLog;

/**
 * 
 * Agent that administers the enemies spawns.
 *
 */
public class SpawnAgent extends Thread {

    private static final int WAITING_TIME = 1500;
    private static final int BOUND = 8000;
    private static final int SCORE_LEVEL_MULTIPLIER = 70000;
    private final int scoreLimit;
    private final CharacterController characterController;
    private final GameController gameController;
    private final int level;
    private final Dimension2D fieldSize;
    private final FieldController fieldController;
    private double meteorWaiting;

    /**
     * Build the SpawnAgent.
     * @param gameController the game controller.
     * @param level the game level.
     * @param fieldSize the field width and height.
     */
    public SpawnAgent(final GameController gameController, final int level, final Dimension2D fieldSize) {
        this.gameController = gameController;
        this.level = level;
        this.scoreLimit = this.level * SCORE_LEVEL_MULTIPLIER;
        this.fieldSize = fieldSize;
        this.fieldController = this.gameController.getFieldController();
        this.characterController = fieldController.getCharacter();
        this.meteorWaiting = (Math.random() * BOUND) + WAITING_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while (!this.gameController.isEnded() && this.gameController.getScore().getScorePoints() < this.scoreLimit) {
            try {
                if (!this.gameController.isInPause()) {
                    this.meteorWaiting -= WAITING_TIME;
                    if (this.meteorWaiting < 0) {
                        this.fieldController.addMeteor(new MeteorController(gameController, this.level, ((CharacterShip) (characterController.getEntity())).getCentralPosition(), this.fieldSize));
                        this.meteorWaiting = (Math.random() * BOUND) + WAITING_TIME;
                    }
                    final EnemyController enemyController = new EnemyController(gameController, this.level, characterController, this.fieldSize);
                    enemyController.getEntity().setFreeze(this.gameController.isFrozen());
                    this.fieldController.addEnemy(enemyController);
                }
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException e) {
                ErrorLog.getLog().printError();
                System.exit(0);
            }
        }
        if (this.gameController.getGameLevel() < 4) {
            this.gameController.setEnded(true);
        }
    }
}
