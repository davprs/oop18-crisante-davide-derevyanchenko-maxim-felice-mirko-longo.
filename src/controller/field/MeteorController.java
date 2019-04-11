package controller.field;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.Entity;
import model.meteor.Meteor;
import model.meteor.MeteorImpl;

/**
 * Controller class of Meteor.
 */
public class MeteorController implements EntityController {

    private final Image image;
    private final GameController gameController;
    private final Meteor meteor;

    /**
     * Build a MeteorController and his Meteor.
     * @param gameController the gameController.
     * @param level the level of the Meteor.
     * @param target Meteor's target.
     * @param fieldSize the filed's width and height.
     */
    public MeteorController(final GameController gameController, final int level, final Point2D target, final Dimension2D fieldSize) {
        this.image = utilities.EntitiesImageUtils.getMeteorImage(level);
        final double meteorSpacing = Math.random() * 100;
        Point2D src;
        if (Math.random() * 10 < 5) {
            if (Math.random() * 10 < 5) {
                src = new Point2D(-meteorSpacing, -meteorSpacing);
            } else {
                src = new Point2D(-meteorSpacing, fieldSize.getHeight() + meteorSpacing);
            }
        } else {
            if (Math.random() * 10 < 5) {
                src = new Point2D(fieldSize.getWidth() + meteorSpacing, fieldSize.getHeight() + meteorSpacing);
            } else {
                src = new Point2D(fieldSize.getWidth() + meteorSpacing, -meteorSpacing);
            }
        }
        this.meteor = new MeteorImpl(level, src, target, fieldSize);
        this.gameController = gameController;
    }

    /**
     * Build a MeteorController and his easy-level Meteor.
     * @param gameController the gameController.
     * @param target Meteor's target.
     * @param fieldSize the filed's width and height.
     */
    public MeteorController(final GameController gameController, final Point2D target, final Dimension2D fieldSize) {
        this(gameController, 1, target, fieldSize);
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
        this.gameController.getFieldView().drawEntity(image, angle, this.meteor.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.meteor.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return this.meteor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        this.gameController.getFieldView().drawExplosion(this.meteor.getBoundary());
        this.gameController.getFieldController().removeMeteor(this);
    }
}
