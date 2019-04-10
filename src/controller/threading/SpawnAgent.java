package controller.threading;

import view.field.FieldView;
import controller.field.CharacterController;
import controller.field.EnemyController;
import controller.field.FieldController;
import controller.field.GameController;
import controller.field.MeteorController;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import model.ship.charactership.CharacterShip;

/**
 * 
 * Agent that administrates the enemies spawns.
 *
 */
public class SpawnAgent extends Thread {

    private static final int WAITING_TIME = 3000;
    private final CharacterController characterController;
    private final GameController gameController;
    private final int level;
    private final FieldView view;
    private final Dimension2D fieldSize;
    private final FieldController fieldController;
    private double meteorWaiting;

    /**
     * 
     * @param gameController .
     * @param level .
     * @param fieldView .
     * @param fieldController .
     * @param fieldSize .
     */
    public SpawnAgent(final GameController gameController, final int level, final FieldView fieldView, final FieldController fieldController, final Dimension2D fieldSize) {
        this.gameController = gameController;
        this.level = level;
        this.fieldSize = fieldSize;
        this.view = fieldView;
        this.fieldController = fieldController;
        this.characterController = fieldController.getCharacter();
        this.meteorWaiting = (Math.random() * 8000) + 3000;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while (!gameController.isInPause() && !gameController.isEnded()) {
            try {
//                Thread.currentThread().sleep(WAITING_TIME);
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.meteorWaiting -= WAITING_TIME;
            if (this.meteorWaiting < 0) {
                Platform.runLater(() -> fieldController.addMeteor(new MeteorController(view, this.level, ((CharacterShip) (this.characterController.getEntity())).getCentralPosition(), this.fieldSize)));
                this.meteorWaiting = (Math.random() * 8000) + 3000;
            }

//            fieldController.addEnemy(new EnemyController(view, this.level, characterController))
            Platform.runLater(() -> fieldController.addEnemy(new EnemyController(view, this.level, characterController, this.fieldSize)));
        }
    }
}
