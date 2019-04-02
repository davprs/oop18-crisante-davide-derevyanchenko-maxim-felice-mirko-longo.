package controller.field;

import java.util.LinkedList;
import java.util.List;

import controller.StageController;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.account.Account;
import view.field.FieldView;
import view.field.OverlayView;

/**
 * The field controller's class.
 *
 */
public class FieldController {

    private static final Image SPACE_IMAGE = new Image("space.png");
    private final CharacterController shipController;
    private final CameraController camController;
    private final List<EnemyController> enemies;
    private final List<BulletController> enemyBullets;
    private final List<BulletController> characterBullets;

    /**
     * 
     * @param account 
     * @param stageController 
     */
    public FieldController(final Account account, final StageController stageController) {
        this.enemies = new LinkedList<>();
        this.enemyBullets = new LinkedList<>();
        this.characterBullets = new LinkedList<>();
        final FieldView fieldView = new FieldView();
        stageController.setScene(fieldView.getScene());
        try {
            new OverlayView(stageController).start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        stageController.setFullScreen(account.getSettings().isFullScreenOn());
        this.camController = new CameraController(stageController, fieldView.getCamera());
        this.shipController = new CharacterController(fieldView, this.camController, stageController);
        this.shipController.draw();
        final AnimationTimer loop =  new AnimationTimer() {

            public void handle(final long currentNanoTime) {

                fieldView.drawBackground(SPACE_IMAGE);
                if (shipController.isCamMoving()) {
                    shipController.update();
                    camController.update();
                }
                shipController.draw();
            }
        };
        loop.start();
    }

    /**
     * 
     * @return the ship controller
     */
    public CharacterController getShipController() {
        return this.shipController;
    }

    /**
     * 
     * @return the list of all enemy controllers
     */
    public List<EnemyController> getEnemies() {
        return this.enemies;
    }

    /**
     * 
     * @return the list of all enemy bullet controllers
     */
    public List<BulletController> getEnemyBullets() {
        return this.enemyBullets;
    }

    /**
     * 
     * @return the list of all character bullet controllers
     */
    public List<BulletController> getAllyBullets() {
        return this.characterBullets;
    }

    /**
     * 
     * @param enemy the enemy to be added
     */
    public void addEnemy(final EnemyController enemy) {
        this.enemies.add(enemy);
    }

    /**
     * 
     * @param enemyBullet the enemy bullet to be added
     */
    public void addEnemyBullet(final BulletController enemyBullet) {
        this.enemyBullets.add(enemyBullet);
    }

    /**
     * 
     * @param characterBullet the character bullet to be added
     */
    public void addCharacterBullet(final BulletController characterBullet) {
        this.characterBullets.add(characterBullet);
    }
}
