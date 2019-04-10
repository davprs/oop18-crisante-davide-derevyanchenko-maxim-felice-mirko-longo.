package controller.field;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.Entity;
import model.ship.charactership.CharacterShipImpl;
import model.ship.enemyship.EnemyShip;
import model.ship.enemyship.EnemyShipImpl;

/**
 * Controller class of EnemyShip.
 */
public class EnemyController implements EntityController {

    private final Image image;
    private final CharacterController characterController;
    private final GameController gameController;
    private final EnemyShip enemy;
    private boolean freeze;
    private Dimension2D fieldSize;
    private Point2D centralPosition;

    /**
     * Build a new EnemyController and his EnemyShip.
     * @param gameController the fieldView
     * @param level the level of the new created Enemy.
     * @param characterController the entity representing the CharacterShip.
     * @param fieldSize the field width and height.
     */
    public EnemyController(final GameController gameController, final int level, final CharacterController characterController, final Dimension2D fieldSize) {
        this.fieldSize = fieldSize;
        this.image = utilities.EntitiesImageUtils.getEnemyShipImage(level);
        this.enemy = new EnemyShipImpl(level, fieldSize);
        this.gameController = gameController;
        this.characterController = characterController;
        this.freeze = false;
    }

    /**
     * Build a new EnemyController and his easy-level EnemyShip.
     * @param gameController the gameController.
     * @param characterController the entity representing the CharacterShip.
     * @param fieldSize the field width and height.
     */
    public EnemyController(final GameController gameController, final CharacterController characterController, final Dimension2D fieldSize) {
        this(gameController, 1, characterController, fieldSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        final double angle = Math.toDegrees(Math.atan2(this.centralPosition.getY()- ((CharacterShipImpl) (characterController.getEntity())).getCentralPosition().getY(),
                this.centralPosition.getX() - ((CharacterShipImpl) (characterController.getEntity())).getCentralPosition().getX())) + 180;
        this.gameController.getFieldView().drawEntity(image, angle, this.enemy.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        double rad;
        double movY, movX;
        this.centralPosition = new Point2D((enemy.getBoundary().getMinX() + enemy.getBoundary().getMaxX()) / 2,
                (enemy.getBoundary().getMinY() + enemy.getBoundary().getMaxY()) / 2);
        rad = Math.atan2(this.centralPosition.getY() - ((CharacterShipImpl) (characterController.getEntity())).getCentralPosition().getY(), 
                this.centralPosition.getX() - ((CharacterShipImpl) (characterController.getEntity())).getCentralPosition().getX());

        movY = - this.enemy.getSpeed() * Math.sin(rad);
        movX = - this.enemy.getSpeed() * Math.cos(rad);
        enemy.update(new Point2D(movX, movY));
    }

    /**
     * Method that changes the value of the freeze enemies. 
     */
    public void changeFreeze() {
        this.freeze = !this.freeze;
    }

    /**
     * 
     * @return the new Bullet.
     */
    public BulletController shoot() {
        return new BulletController(this.gameController, this.enemy.getLevel(), this.enemy.shoot(), 
                new Point2D(this.characterController.getEntity().getBoundary().getMinX(),
                    this.characterController.getEntity().getBoundary().getMinY()), this.fieldSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return enemy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        this.gameController.getFieldView().drawExplosion(this.enemy.getBoundary());
        this.gameController.getFieldController().removeEnemy(this);
    }

}
