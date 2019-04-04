package controller.field;

import javafx.scene.image.Image;
import model.Entity;
import model.bullet.Bullet;
import view.field.FieldView;

/**
 * Controller class of Bullet.
 *
 */
public class BulletController implements EntityController {

    private static final Image IMAGE = new Image("bullet.png");
    private final FieldView view;
    private final Bullet bullet;

    /**
     * Build a BulletController.
     * @param view the fieldView
     * @param bullet the controlled Bullet.
     */
    public BulletController(final FieldView view, final Bullet bullet) {
        this.view = view;
        this.bullet = bullet;
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
        this.view.drawEntity(IMAGE, angle, this.bullet.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (isAlive()) {
            this.bullet.update();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public  boolean isAlive() {
        return this.bullet.isAlive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final EntityController entityController) {
        return this.bullet.intersects(entityController.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return this.bullet;
    }

}
