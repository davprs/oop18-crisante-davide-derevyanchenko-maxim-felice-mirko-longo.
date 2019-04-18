package controller.game.field.entities;

import controller.game.GameController;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.entity.Entity;
import model.entity.meteor.Meteor;
import model.entity.meteor.MeteorImpl;

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
        this.image = utilities.ImageUtils.getMeteorImage(level);
        final double meteorSpacingX = Math.random() * fieldSize.getWidth();
        final double meteorSpacingY = Math.random() * fieldSize.getWidth();
        Point2D src;
        final double wall = Math.random() * fieldSize.getWidth() / 100;
        if (wall < 25) {
            src = new Point2D(-100, meteorSpacingY);
        } else if (wall < 50) {
            src = new Point2D(meteorSpacingX, fieldSize.getHeight());
        } else if (wall < 75) {
            src = new Point2D(fieldSize.getWidth(), meteorSpacingY);
        } else {
            src = new Point2D(meteorSpacingX, -100);
        }
        
//        if (Math.random() * 10 < RANGE) {
//            if (Math.random() * 10 < RANGE) {
//                src = new Point2D(-meteorSpacing, -meteorSpacing);
//            } else {
//                src = new Point2D(-meteorSpacing, fieldSize.getHeight());
//            }
//        } else {
//            if (Math.random() * 10 < RANGE) {
//                src = new Point2D(fieldSize.getWidth() + meteorSpacing, fieldSize.getHeight() + meteorSpacing);
//            } else {
//                src = new Point2D(fieldSize.getWidth() + meteorSpacing, -meteorSpacing);
//            }
//        }
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
        this.gameController.getFieldController().removeMeteor(this);
        this.gameController.getScore().addScore(this.meteor.getScorePoints());
    }
}
