package controller.field;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.Entity;
import model.meteor.Meteor;
import model.meteor.MeteorImpl;
import view.field.FieldView;

/**
 * Controller class of Meteor.
 *
 */
public class MeteorController implements EntityController {

    private final Image image;
    private final FieldView view;
    private final Meteor meteor;

    /**
     * Build a MeteorController and his Meteor.
     * @param view the fieldView.
     * @param level the level of the Meteor.
     * @param src the starting point of the Meteor.
     * @param target Meteor's target.
     */
    public MeteorController(final FieldView view, final int level, final Point2D src, final Point2D target) {
        this.image = utilities.EntitiesImageUtils.getMeteorImage(level);
        this.meteor = new MeteorImpl(level, src, target, new Dimension2D(this.image.getWidth(), this.image.getHeight()));
        this.view = view;
    }

    /**
     * Build a MeteorController and his easy-level Meteor.
     * @param view the fieldView.
     * @param src the starting point of the Meteor.
     * @param target Meteor's target.
     */
    public MeteorController(final FieldView view, final Point2D src, final Point2D target) {
        this(view, 1, src, target);
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
        this.view.drawEntity(image, angle, this.meteor.getBoundary());
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
