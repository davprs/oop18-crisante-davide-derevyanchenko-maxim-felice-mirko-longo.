package controller.threading;

import controller.field.BulletController;
import controller.field.CameraController;
import controller.field.EnemyController;
import controller.field.GameController;
import controller.field.MeteorController;
import javafx.application.Platform;
import utilities.ErrorLog;
import view.field.FieldView;

/**
 * Class that represents the thread that draws all the elements of the field.
 *
 */
public class DrawAgent extends Thread {

    private static final long WAITING_TIME = 10;
    private final GameController gameController;
    private final FieldView view;
    private final CameraController cameraController;

    /**
     *  Constructor for the DrawAgent.
     * 
     * @param gameController the controller of the game
     * @param view the field view of the game
     * @param cameraController the camera of the field
     */
    public DrawAgent(final GameController gameController, final FieldView view, final CameraController cameraController) {
        this.gameController = gameController;
        this.view = view;
        this.cameraController = cameraController;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {

        while (true) {
            try {
                synchronized (this.gameController) {
                    if (!this.gameController.isInPause()) {
                        Platform.runLater(() -> {
                            this.view.drawBackground();
                            this.cameraController.update();
                            this.gameController.getFieldController().getCharacter().draw();
                            for (final EnemyController enemy : this.gameController.getFieldController().getEnemies()) {
                                enemy.draw();
                            }
                            for (final BulletController enemyBullet : this.gameController.getFieldController().getEnemyBullets()) {
                                enemyBullet.draw();
                            }
                            for (final BulletController characterBullet : this.gameController.getFieldController().getCharacterBullets()) {
                                characterBullet.draw();
                            }
                            for (final MeteorController meteor : this.gameController.getFieldController().getMeteors()) {
                                meteor.draw();
                            }
                        });
                    }
                }
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException e) {
                ErrorLog.getLog().printError(e);
                System.exit(0);
            }
        }
    }
}
