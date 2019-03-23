package controller;

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
     * 
     */
    public void draw() {
        double angle = Math.toDegrees(this.bullet.getAngle());
        this.view.drawEntity(this.bullet.getImageView(), angle, this.bullet.getBoundary());
    }

    /**
     * 
     */
    public void update() {
        if (isInField()) {
            this.bullet.update();
        }
    }

    private boolean isInField() {
        return false;
    }
}
