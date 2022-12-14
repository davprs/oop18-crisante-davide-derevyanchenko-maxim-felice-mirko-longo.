package controller.game.field.entities;

import controller.game.GameController;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.entity.ship.charactership.CharacterShipImpl;
import model.entity.ship.enemyship.EnemyShip;
import model.entity.ship.enemyship.EnemyShipImpl;

/**
 * Controller class of EnemyShip.
 */
public class EnemyController implements EntityController {

    private final Image image;
    private final CharacterController characterController;
    private final GameController gameController;
    private final EnemyShip enemy;
    private Dimension2D fieldSize;
    private double rad;


    /**
     * Build a new EnemyController and his EnemyShip.
     * @param gameController the fieldView
     * @param level the level of the new created Enemy.
     * @param characterController the entity representing the CharacterShip.
     * @param fieldSize the field width and height.
     */
    public EnemyController(final GameController gameController, final int level, final CharacterController characterController, final Dimension2D fieldSize) {
        this.fieldSize = fieldSize;
        this.image = utilities.ImageUtils.getEnemyShipImage(level);
        this.enemy = new EnemyShipImpl(level, fieldSize, characterController.getEntity().getCentralPosition());
        this.gameController = gameController;
        this.characterController = characterController;
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
        final double angle = Math.toDegrees(this.rad) + 180;
        this.gameController.getFieldView().drawEntity(image, angle, this.enemy.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        double movY, movX;
        final Point2D centralPosition = new Point2D((enemy.getBoundary().getMinX() + enemy.getBoundary().getMaxX()) / 2,
                (enemy.getBoundary().getMinY() + enemy.getBoundary().getMaxY()) / 2);
        this.rad = Math.atan2(centralPosition.getY() - ((CharacterShipImpl) (characterController.getEntity())).getCentralPosition().getY(), 
                centralPosition.getX() - ((CharacterShipImpl) (characterController.getEntity())).getCentralPosition().getX());

        movY = -this.enemy.getSpeed() * Math.sin(this.rad);
        movX = -this.enemy.getSpeed() * Math.cos(this.rad);
        enemy.update(new Point2D(movX, movY));
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
     * @return true if the enemy can shoot according to his shooting rateo.
     */
    public boolean canShoot() {
        return this.enemy.canShoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyShip getEntity() {
        return enemy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        this.gameController.getFieldController().removeEnemy(this);
        this.gameController.getScore().addScore(this.enemy.getScorePoints());
    }

}
