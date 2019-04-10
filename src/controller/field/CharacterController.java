package controller.field;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Toolkit;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.Entity;
import model.ship.charactership.CharacterShip;
import model.ship.charactership.CharacterShipImpl;

/**
 * Class that controls the character ship moves.
 *
 */
public class CharacterController implements EntityController {

    private static final Dimension RESOLUTION = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Image SHIP_IMAGE = new Image("spaceship.png");
    private final CharacterShip ship;
    private final GameController gameController;
    private final CameraController camController;
    private double angle;
    private boolean immunity;
    private long lastUpdate;

    /**
     * Constructor of the CharacterController.
     * 
     * @param gameController  the view in which the ship is moving
     * @param camController  the camera controller of the view
     */
    public CharacterController(final GameController gameController, final CameraController camController) {
        this.ship = new CharacterShipImpl(new Point2D(RESOLUTION.getWidth() / 2, RESOLUTION.getHeight() / 2));
        this.gameController = gameController;
        this.camController = camController;
        this.immunity = false;
        this.lastUpdate = System.currentTimeMillis();
    }

    private Point2D getUpdatedPosition() {
        final Point2D mouseOnScreen = new Point2D(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());
        final Point2D mousePosition = this.gameController.getFieldView().getCanvas().screenToLocal(mouseOnScreen);
        final Point2D vector = mousePosition.subtract(RESOLUTION.getWidth() / 2, RESOLUTION.getHeight() / 2);
        final long time = System.currentTimeMillis();
        final long timeFromLastUpdate = time - this.lastUpdate;
        this.lastUpdate = time;
        final double rad = Math.atan2(vector.getY(), vector.getX());
        this.angle = Math.toDegrees(rad);
        double shipUpdateX = this.ship.getBoundary().getMinX() + (timeFromLastUpdate * this.ship.getSpeed() * Math.cos(rad));
        double shipUpdateY = this.ship.getBoundary().getMinY() + (timeFromLastUpdate * this.ship.getSpeed() * Math.sin(rad));
        if (shipUpdateX < 0) {
            shipUpdateX = 0;
        } else if (shipUpdateX > (this.gameController.getFieldView().getCanvas().getWidth() - this.ship.getBoundary().getWidth())) {
            shipUpdateX = this.gameController.getFieldView().getCanvas().getWidth() - this.ship.getBoundary().getWidth();
        }
        if (shipUpdateY < 0) {
            shipUpdateY = 0;
        } else if (shipUpdateY > (this.gameController.getFieldView().getCanvas().getHeight() - this.ship.getBoundary().getHeight())) {
            shipUpdateY = this.gameController.getFieldView().getCanvas().getHeight() - this.ship.getBoundary().getHeight();
        }
        return new Point2D(shipUpdateX, shipUpdateY);
    }

    /**
     * Gets the angle, in degrees, by which the ship is rotated.
     * 
     * @return the angle, in degrees, by which the ship is rotated
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        this.gameController.getFieldView().drawEntity(SHIP_IMAGE, this.angle, this.ship.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        final Point2D updatedPosition = getUpdatedPosition();
        this.camController.setTranslation(new Point2D(updatedPosition.getX() - this.ship.getBoundary().getMinX(), updatedPosition.getY() - this.ship.getBoundary().getMinY()));
        this.ship.update(updatedPosition.getX(), updatedPosition.getY());
    }

    /**
     * Method that changes the value of immunity. 
     */
    public void changeImmunity() {
        this.immunity = !this.immunity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return this.ship;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        this.gameController.getFieldView().drawExplosion(this.ship.getBoundary());
    }
}
