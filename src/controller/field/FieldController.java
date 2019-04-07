package controller.field;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;

import controller.StageController;
import controller.threading.AntagonistsAgent;
import controller.threading.CharacterAgent;
import controller.threading.CharacterBulletAgent;
import controller.threading.DrawAgent;
import model.account.Account;
import utilities.ErrorLog;
import view.field.FieldView;
import view.field.GameView;
import view.field.OverlayView;

/**
 * The field controller's class.
 *
 */
public class FieldController {

    private static final Dimension RESOLUTION = Toolkit.getDefaultToolkit().getScreenSize();
    private final CharacterController characterController;
    private boolean inPause;
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
        this.inPause = false;
        this.enemies = new LinkedList<>();
        this.enemyBullets = new LinkedList<>();
        this.characterBullets = new LinkedList<>();
        this.meteors = new LinkedList<>();
        final GameView overlay = new GameView(stageController);
        final FieldView fieldView = new FieldView(stageController);
        final OverlayView ov = new OverlayView(stageController);
        stageController.setScene(overlay.getScene());
        stageController.setFullScreen(account.getSettings().isFullScreenOn());
        overlay.getRoot().getChildren().add(fieldView.getSubScene());
        overlay.getRoot().getChildren().add(ov.getSubScene());
        final CameraController camController = new CameraController(fieldView.getCamera());
        this.characterController = new CharacterController(fieldView, camController, stageController);
        this.startAgent(new CharacterAgent(this.characterController, this));
        this.startAgent(new DrawAgent(this, fieldView, camController));
        try {
            new Robot().mouseMove((int) RESOLUTION.getWidth() / 2, (int) RESOLUTION.getHeight() / 2);
        } catch (AWTException e) {
            ErrorLog.getLog().printError();
            System.exit(0);
        }
    }

    /**
     * Gets the ShipController.
     * 
     * @return the ship controller
     */
    public CharacterController getCharacter() {
        return this.characterController;
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

    private void startAgent(final Thread agent) {
        agent.start();
    }
    /**
     * Adds the enemy to the list of the EnemyControllers.
     * 
     * @param enemy the enemy to be added
     */
    public void addEnemy(final EnemyController enemy) {
        this.enemies.add(enemy);
        this.startAgent(new AntagonistsAgent(enemy, this));
    }

    /**
     * Adds the Bullet to the list of the EnemyBulletControllers.
     * 
     * @param enemyBullet the enemy bullet to be added
     */
    public void addEnemyBullet(final BulletController enemyBullet) {
        this.enemyBullets.add(enemyBullet);
        this.startAgent(new AntagonistsAgent(enemyBullet, this));
    }

    /**
     * Adds the bullet to the list of the CharacterBulletControllers.
     * 
     * @param characterBullet the character bullet to be added
     */
    public void addCharacterBullet(final BulletController characterBullet) {
        this.characterBullets.add(characterBullet);
        this.startAgent(new CharacterBulletAgent(characterBullet, this));
    }

    /**
     * Adds the meteor to the list of the MeteorControllers.
     * 
     * @param meteor the meteor to be added
     */
    public void addMeteor(final MeteorController meteor) {
        this.meteors.add(meteor);
        this.startAgent(new AntagonistsAgent(meteor, this));
    }

    /**
     * Sets the value of the inPause state.
     * 
     * @param inPause the value that says if the game is paused or not
     */
    public void setInPause(final boolean inPause) {
        this.inPause = inPause;
    }

    /**
     * Method that says if the game is in pause or not.
     * 
     * @return true if the game is in pause, false otherwise
     */
    public boolean isInPause() {
        return this.inPause;
    }
}
