package controller.field;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;

import controller.threading.AntagonistsAgent;
import controller.threading.CharacterAgent;
import controller.threading.CharacterBulletAgent;
import controller.threading.DrawAgent;
import utilities.ErrorLog;
import view.field.FieldView;

/**
 * The field controller's class.
 *
 */
public class FieldController {

    private static final Dimension RESOLUTION = Toolkit.getDefaultToolkit().getScreenSize();
    private final CharacterController characterController;
    private final List<EnemyController> enemies;
    private final List<BulletController> enemyBullets;
    private final List<BulletController> characterBullets;
    private final List<MeteorController> meteors;
    private final GameController gameController;

    /**
     * Constructor of the FieldController.
     * 
     * @param gameController the GameController of this session
     * @param fieldView the view of this field
     */
    public FieldController(final GameController gameController, final FieldView fieldView) {
        this.gameController = gameController;
        this.enemies = new LinkedList<>();
        this.enemyBullets = new LinkedList<>();
        this.characterBullets = new LinkedList<>();
        this.meteors = new LinkedList<>();
        final CameraController camController = new CameraController(fieldView.getCamera());
        this.characterController = new CharacterController(fieldView, camController);
        this.startAgent(new CharacterAgent(this.characterController, this.gameController));
        this.startAgent(new DrawAgent(this.gameController, fieldView, camController));
        try {
            new Robot().mouseMove((int) RESOLUTION.getWidth() / 2, (int) RESOLUTION.getHeight() / 2);
        } catch (AWTException e) {
            ErrorLog.getLog().printError();
            System.exit(0);
        }
    }

    /**
     * Gets the GameController of this session.
     * 
     * @return the GameController
     */
    public GameController getGameController() {
        return this.gameController;
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
    public synchronized List<EnemyController> getEnemies() {
        return this.enemies;
    }

    /**
     * Gets the list of the EnemyBulletControllers that are in the field.
     * 
     * @return the list of all enemy bullet controllers
     */
    public synchronized List<BulletController> getEnemyBullets() {
        return this.enemyBullets;
    }

    /**
     * Gets the list of the CharacterBulletControllers that are in the field.
     * 
     * @return the list of all character bullet controllers
     */
    public synchronized List<BulletController> getCharacterBullets() {
        return this.characterBullets;
    }

    /**
     * Gets the list of the MeteorControllers that are in the field.
     * 
     * @return the list of all meteor controllers
     */
    public synchronized List<MeteorController> getMeteors() {
        return this.meteors;
    }

    /**
     * Adds the enemy to the list of the EnemyControllers.
     * 
     * @param enemy the enemy to be added
     */
    public synchronized void addEnemy(final EnemyController enemy) {
        this.enemies.add(enemy);
        this.startAgent(new AntagonistsAgent(enemy, this.gameController));
    }

    /**
     * Adds the Bullet to the list of the EnemyBulletControllers.
     * 
     * @param enemyBullet the enemy bullet to be added
     */
    public synchronized void addEnemyBullet(final BulletController enemyBullet) {
        this.enemyBullets.add(enemyBullet);
        this.startAgent(new AntagonistsAgent(enemyBullet, this.gameController));
    }

    /**
     * Adds the bullet to the list of the CharacterBulletControllers.
     * 
     * @param characterBullet the character bullet to be added
     */
    public synchronized void addCharacterBullet(final BulletController characterBullet) {
        this.characterBullets.add(characterBullet);
        this.startAgent(new CharacterBulletAgent(characterBullet, this.gameController));
    }

    /**
     * Adds the meteor to the list of the MeteorControllers.
     * 
     * @param meteor the meteor to be added
     */
    public synchronized void addMeteor(final MeteorController meteor) {
        this.meteors.add(meteor);
        this.startAgent(new AntagonistsAgent(meteor, this.gameController));
    }

    /**
     * Removes a destroyed enemy from the list.
     * 
     * @param enemy the enemy destroyed
     */
    public void removeEnemy(final EnemyController enemy) {
        this.enemies.remove(enemy);
    }

    /**
     * Removes a destroyed bullet of an enemy from the list.
     * 
     * @param enemyBullet the enemy's bullet destroyed
     */
    public void removeEnemyDullet(final BulletController enemyBullet) {
        this.enemyBullets.remove(enemyBullet);
    }

    /**
     * Removes a destroyed bullet of the character from the list.
     * 
     * @param characterBullet the character's bullet destroyed
     */
    public void removeCharacterBullet(final BulletController characterBullet) {
        this.characterBullets.remove(characterBullet);
    }

    /**
     * Removes a destroyed meteor from the list.
     * 
     * @param meteor the meteor destroyed
     */
    public void removeMeteor(final MeteorController meteor) {
        this.meteors.remove(meteor);
    }

    private void startAgent(final Thread agent) {
        agent.start();
    }
}
