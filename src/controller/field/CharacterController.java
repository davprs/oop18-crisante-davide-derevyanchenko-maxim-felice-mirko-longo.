package controller.field;

import controller.StageController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.Entity;
import model.ship.CharacterShip;
import model.ship.CharacterShipImpl;
import view.field.FieldView;

/**
 * Class that controls the character ship moves.
 *
 */
public class CharacterController implements EntityController {

    private static final Image SHIP_IMAGE = new Image("spaceship.png");
    private final CharacterShip ship;
    private final FieldView view;
    private final CameraController camController;
    private boolean camMoving;
    private boolean immunity;
    private final StageController stageController;

    /**
     * 
     * @param view  the view in which the ship is moving
     * @param camController  the camera controller of the view
     * @param stageController the controller of the stage
     */
    public CharacterController(final FieldView view, final CameraController camController, final StageController stageController) {
        this.ship = new CharacterShipImpl(SHIP_IMAGE);
        this.view = view;
        this.camController = camController;
        this.immunity = false;
        final EventHandler<Event> eh = new EventHandler<Event>() {
            @Override
            public void handle(final Event event) {
                ship.changeMoving();
                camMoving = !camMoving;
            }
        };
        this.stageController = stageController;
        this.stageController.getScene().setOnMouseEntered(eh);
        this.stageController.getScene().setOnMouseExited(eh);
    }

    private Point2D getUpdatedPosition() {
        final Point2D camUpdate = camController.getCamUpdate();
        double shipUpdateX = this.camController.getCameraTranslationX() + this.stageController.getWidth() / 2 - this.ship.getBoundary().getWidth() / 2;
        double shipUpdateY = this.camController.getCameraTranslationY() + this.stageController.getHeight() / 2 - this.ship.getBoundary().getHeight() / 2;

        if (ship.getBoundary().getMinX() <= view.getCanvas().getLayoutX() && camUpdate.getX() <= 0) {
            shipUpdateX = view.getCanvas().getLayoutX();
            camController.resetCamUpdateX();
        } else if (ship.getBoundary().getMaxX() >= view.getCanvas().getLayoutX() + view.getCanvas().getWidth() && camUpdate.getX() >= 0) {
            shipUpdateX = view.getCanvas().getLayoutX() + view.getCanvas().getWidth() - ship.getBoundary().getWidth();
            camController.resetCamUpdateX();
        }
        if (ship.getBoundary().getMinY() <= view.getCanvas().getLayoutY() && camUpdate.getY() <= 0) {
            shipUpdateY = view.getCanvas().getLayoutY();
            camController.resetCamUpdateY();
        } else if (ship.getBoundary().getMaxY() >= view.getCanvas().getLayoutY() + view.getCanvas().getHeight() && camUpdate.getY() >= 0) {
            shipUpdateY = view.getCanvas().getLayoutY() + view.getCanvas().getHeight() - ship.getBoundary().getHeight();
            camController.resetCamUpdateY();
        }
        return new Point2D(shipUpdateX, shipUpdateY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        final Point2D camUpdate = camController.getCamUpdate();
        final double angle = Math.toDegrees(Math.atan2(camUpdate.getY(), camUpdate.getX()));
        this.view.drawEntity(this.ship.getImageView(), angle, this.ship.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        final Point2D updatedPosition = getUpdatedPosition();
        this.ship.update(updatedPosition.getX(), updatedPosition.getY());
    }

    /**
     * 
     * @return isCamMoving value
     */
    public boolean isCamMoving() {
        return this.camMoving;
    }

    /**
     * change the value of immunity. 
     */
    public void changeImmunity() {
        this.immunity = !this.immunity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final EntityController entityController) {
        return this.ship.intersects(entityController.getEntity());
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
    public boolean isAlive() {
        return this.ship.isAlive();
    }
}
