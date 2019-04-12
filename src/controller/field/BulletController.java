package controller.field;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.Entity;
import model.bullet.Bullet;
import model.bullet.BulletImpl;

/**
 * Controller class of Bullet.
 */
public class BulletController implements EntityController {

    private final Image image;
    private final GameController gameController;
    private final Bullet bullet;

    /**
     * Build a BulletController and his Bullet.
     * @param gameController the gameController.
     * @param level the level of the Bullet to create.
     * @param src the starting point of the Bullet to create.
     * @param target the target point of the Bullet.
     * @param fieldSize the field width and height.
     */
    public BulletController(final GameController gameController, final int level, final Point2D src, final Point2D target, final Dimension2D fieldSize) {
        this.image = utilities.EntitiesImageUtils.getBulletImage(level);
        System.out.println("bullet done");
        this.bullet = new BulletImpl(level, src, target, fieldSize);
        this.gameController = gameController;
    }

    /**
     * Build a BulletController and his easy-level Bullet.
     * @param gameController the gameController.
     * @param src the starting point of the Bullet to create.
     * @param target the target point of the Bullet.
     * @param fieldSize the field width and height.
     */
    public BulletController(final GameController gameController, final Point2D src, final Point2D target, final Dimension2D fieldSize) {
        this(gameController, 1, src, target, fieldSize);
    }

    /**
     * Get the damage the Bullet provokes when impacts with an enemy.
     * @return the health-points it subtracts to the enemy it hits.
     */
    public int getDamage() {
        return this.bullet.getDamage();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        final double angle = Math.toDegrees(this.bullet.getAngle());
        this.gameController.getFieldView().drawEntity(image, angle, this.bullet.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.bullet.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return this.bullet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        this.gameController.getFieldController().removeCharacterBullet(this);
        this.gameController.getFieldController().removeEnemyBullet(this);
    }

}
