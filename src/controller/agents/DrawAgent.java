package controller.agents;

import java.util.LinkedList;
import java.util.List;

import controller.game.GameController;
import controller.game.field.CameraController;
import controller.game.field.entities.BulletController;
import controller.game.field.entities.EnemyController;
import controller.game.field.entities.EntityController;
import controller.game.field.entities.MeteorController;
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

    /**
     *  Constructor for the DrawAgent.
     * 
     * @param gameController the controller of the game
     * @param cameraController the camera of the field
     */
    public DrawAgent(final GameController gameController, final CameraController cameraController) {
        this.gameController = gameController;
        this.cameraController = cameraController;
        this.explodingEntities = new LinkedList<>();
        this.explosionStartMoments = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {

        while (!this.gameController.isEnded()) {
            try {
                synchronized (this.gameController) {
                    if (!this.gameController.isInPause()) {
                        Platform.runLater(() -> {
                            this.gameController.getFieldView().drawBackground();
                            this.cameraController.update();
                            this.gameController.getFieldController().getCharacter().draw();
                            final List<EnemyController> enemies = this.gameController.getFieldController().getEnemies();
                            for (final EnemyController enemy : enemies) {
                                enemy.draw();
                            }
                            final List<BulletController> enemyBullets = this.gameController.getFieldController().getEnemyBullets();
                            for (final BulletController enemyBullet : enemyBullets) {
                                enemyBullet.draw();
                            }
                            final List<BulletController> characterBullets = this.gameController.getFieldController().getCharacterBullets();
                            for (final BulletController characterBullet : characterBullets) {
                                characterBullet.draw();
                            }
                            final List<MeteorController> meteors = this.gameController.getFieldController().getMeteors();
                            for (final MeteorController meteor : meteors) {
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
                ErrorLog.getLog().printError();
                System.exit(0);
            }
        }
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
