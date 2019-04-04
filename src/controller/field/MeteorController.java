package controller.field;

import javafx.scene.image.Image;
import model.Entity;
import model.meteor.Meteor;
import view.field.FieldView;

/**
 * Controller class of Meteor.
 *
 */
public class MeteorController implements EntityController {

    private static final Image IMAGE = new Image("meteor.png");
    private final FieldView view;
    private final Meteor meteor;

    /**
     * Build a MeteorController.
     * @param view the fieldView
     * @param meteor the controlled Meteor.
     */
    public MeteorController(final FieldView view, final Meteor meteor) {
        this.view = view;
        this.meteor = meteor;
    }

    /**
     * Get the damage the Meteor provokes when impacts with the CharacterShip.
     * @return the health-points it subtracts to the CharacterShip.
     */
    public int getDamage() {
        return this.meteor.getDamage();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        final double angle = Math.toDegrees(this.meteor.getAngle());
        this.view.drawEntity(IMAGE, angle, this.meteor.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (isAlive()) {
            this.meteor.update();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public  boolean isAlive() {
        return this.meteor.isAlive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final EntityController entityController) {
        return this.meteor.intersects(entityController.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return this.meteor;
    }
}
