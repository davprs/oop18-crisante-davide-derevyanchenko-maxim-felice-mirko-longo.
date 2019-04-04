package controller.field;

import java.util.LinkedList;
import java.util.List;

import controller.StageController;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.account.Account;
import utilities.ErrorLog;
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
    private final List<MeteorController> meteors;

    /**
     * Constructor of the FieldController.
     * 
     * @param account 
     * @param stageController 
     */
    public FieldController(final Account account, final StageController stageController) {
        this.enemies = new LinkedList<>();
        this.enemyBullets = new LinkedList<>();
        this.characterBullets = new LinkedList<>();
        this.meteors = new LinkedList<>();
        final FieldView fieldView = new FieldView();
        stageController.setScene(fieldView.getScene());
        try {
            new OverlayView(stageController).start(new Stage());
        } catch (Exception e) {
            ErrorLog.getLog().printError();
            System.exit(0);
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
     * Gets the ShipController.
     * 
     * @return the ship controller
     */
    public CharacterController getCharacter() {
        return this.shipController;
    }

    /**
     * Gets the list of the EnemyControllers that are in the field.
     * 
     * @return the list of all enemy controllers
     */
    public List<EnemyController> getEnemies() {
        return this.enemies;
    }

    /**
     * Gets the list of the EnemyBulletControllers that are in the field.
     * 
     * @return the list of all enemy bullet controllers
     */
    public List<BulletController> getEnemyBullets() {
        return this.enemyBullets;
    }

    /**
     * Gets the list of the CharacterBulletControllers that are in the field.
     * 
     * @return the list of all character bullet controllers
     */
    public List<BulletController> getCharacterBullets() {
        return this.characterBullets;
    }

    /**
     * Gets the list of the MeteorControllers that are in the field.
     * 
     * @return the list of all meteor controllers
     */
    public List<MeteorController> getMeteors() {
        return this.meteors;
    }

    /**
     * Adds the enemy to the list of the EnemyControllers.
     * 
     * @param enemy the enemy to be added
     */
    public void addEnemy(final EnemyController enemy) {
        this.enemies.add(enemy);
    }

    /**
     * Adds the Bullet to the list of the EnemyBulletControllers.
     * 
     * @param enemyBullet the enemy bullet to be added
     */
    public void addEnemyBullet(final BulletController enemyBullet) {
        this.enemyBullets.add(enemyBullet);
    }

    /**
     * Adds the bullet to the list of the CharacterBulletControllers.
     * 
     * @param characterBullet the character bullet to be added
     */
    public void addCharacterBullet(final BulletController characterBullet) {
        this.characterBullets.add(characterBullet);
    }

    /**
     * Adds the meteor to the list of the MeteorControllers.
     * 
     * @param meteor the meteor to be added
     */
    public void addMeteor(final MeteorController meteor) {
        this.meteors.add(meteor);
    }
}
