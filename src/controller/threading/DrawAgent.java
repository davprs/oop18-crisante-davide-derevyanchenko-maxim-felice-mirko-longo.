package controller.threading;

import java.util.LinkedList;
import java.util.List;

import controller.field.BulletController;
import controller.field.CameraController;
import controller.field.EnemyController;
import controller.field.EntityController;
import controller.field.FieldController;
import controller.field.GameController;
import controller.field.MeteorController;
import javafx.application.Platform;
import utilities.ErrorLog;

/**
 * Class that represents the thread that draws all the elements of the field.
 *
 */
public class DrawAgent extends Thread {

    private static final long WAITING_TIME = 10;
    private static final int FRAME_AMOUNT = 10;
    private static final int EXPLOSION_ANIMATION_UPDATE_RATIO = 100;
    private static final int EXPLOSION_DURATION = EXPLOSION_ANIMATION_UPDATE_RATIO * FRAME_AMOUNT;
    private final GameController gameController;
    private final CameraController cameraController;
    private final List<EntityController> explodingEntities;
    private final List<Long> explosionStartMoments;
    private final FieldController fieldController;

    /**
     *  Constructor for the DrawAgent.
     * 
     * @param gameController the controller of the game
     * @param fieldController the field controller of the game
     * @param cameraController the camera of the field
     */
    public DrawAgent(final GameController gameController, final FieldController fieldController, final CameraController cameraController) {
        this.gameController = gameController;
        this.cameraController = cameraController;
        this.explodingEntities = new LinkedList<>();
        this.explosionStartMoments = new LinkedList<>();
        this.fieldController = fieldController;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {

        while (!this.gameController.isEnded()) {
            try {
                synchronized (this.fieldController) {
                    if (!this.gameController.isInPause()) {
                        Platform.runLater(() -> {
                            this.gameController.getFieldView().drawBackground();
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
                            for (int i = 0; i < this.explodingEntities.size(); i++) {
                                final int timeFromExplosionStart = (int) (System.currentTimeMillis() - this.explosionStartMoments.get(i).longValue());
                                if (timeFromExplosionStart >= EXPLOSION_DURATION) {
                                    this.explodingEntities.remove(i);
                                    this.explosionStartMoments.remove(i);
                                    i--;
                                } else {
                                    final int frame = (int) (timeFromExplosionStart / EXPLOSION_ANIMATION_UPDATE_RATIO);
                                    this.gameController.getFieldView().drawExplosion(this.explodingEntities.get(i).getEntity().getBoundary(), frame);
                                }
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
        System.out.println("Game Over!!!");
    }

    /**
     * Method that adds an entity that is destroyed and must explode.
     * 
     * @param entity that is destroyed
     */
    public synchronized void addExplodingEntity(final EntityController entity) {
        this.explodingEntities.add(entity);
        this.explosionStartMoments.add(Long.valueOf(System.currentTimeMillis()));
    }
}
