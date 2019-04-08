package controller.threading;

import controller.field.BulletController;
import controller.field.CameraController;
import controller.field.EnemyController;
import controller.field.FieldController;
import controller.field.MeteorController;
import utilities.ErrorLog;
import view.field.FieldView;

/**
 * Class that represents the thread that draws all the elements of the field.
 *
 */
public class DrawAgent extends Thread {

    private static final long WAITING_TIME = 20;
    private final FieldController fieldController;
    private final FieldView view;
    private final CameraController cameraController;

    /**
     *  Constructor for the DrawAgent.
     * 
     * @param fieldController the field controller of the game
     * @param view the field view of the game
     * @param cameraController the camera of the field
     */
    public DrawAgent(final FieldController fieldController, final FieldView view, final CameraController cameraController) {
        this.fieldController = fieldController;
        this.view = view;
        this.cameraController = cameraController;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {

        while (true) {
            try {
                synchronized (this.fieldController) {
                    if (!this.fieldController.isInPause()) {
                        this.view.drawBackground();
                        this.cameraController.update();
                        this.fieldController.getCharacter().draw();
                        for (final EnemyController enemy : this.fieldController.getEnemies()) {
                            enemy.draw();
                        }
                        for (final BulletController enemyBullet : this.fieldController.getEnemyBullets()) {
                            enemyBullet.draw();
                        }
                        for (final BulletController characterBullet : this.fieldController.getCharacterBullets()) {
                            characterBullet.draw();
                        }
                        for (final MeteorController meteor : this.fieldController.getMeteors()) {
                            meteor.draw();
                        }
                    }
                }
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException e) {
                ErrorLog.getLog().printError();
                System.exit(0);
            }
        }
    }
}
