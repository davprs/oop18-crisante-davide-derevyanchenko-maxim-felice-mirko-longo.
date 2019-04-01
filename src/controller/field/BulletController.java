package controller.field;

import model.Entity;
import model.bullet.Bullet;
import view.field.FieldView;

/**
 * Controller class of Bullet.
 *
 */
public class BulletController {

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
     * draws the bullet on canvas.
     */
    public void draw() {
        final double angle = Math.toDegrees(this.bullet.getAngle());
        this.view.drawEntity(this.bullet.getImageView(), angle, this.bullet.getBoundary());
    }

    /**
     * 
     * @param entity is the entity to check for intersection with bullet.
     * @return true if the bullet boundary intersects entity.
     */
    public boolean intersects(final Entity entity) {
        return entity.getBoundary().intersects(bullet.getBoundary());
    }

    /**
     * updates the bullet position (if the bullet has to be shown or if it's visible).
     */
    public void update() {
        if (isAlive()) {
            this.bullet.update();
        }
    }

    /**
     * 
     * @return true if the bullet is alive (has to be shown).
     */
    private boolean isAlive() {
        return this.bullet.isAlive();
    }
}
