package controller.field;

import java.awt.MouseInfo;

import controller.StageController;
import javafx.geometry.Point2D;
import javafx.scene.Camera;

/**
 * Class that controls the camera.
 * 
 */
public class CameraController {

    private static final int SPEED_SCALING = 100;
    private final StageController stageController;
    private final Camera camera;
    private double cameraX;
    private double cameraY;
    private double camUpdateX;
    private double camUpdateY;

    /**
     * 
     * @param stageController the stageController of the stage in which the camera is used
     * @param camera the camera to control
     */
    public CameraController(final StageController stageController, final Camera camera) {
        this.stageController = stageController;
        this.camera = camera;
        this.cameraX = 0;
        this.cameraY = 0;
        this.getCamUpdate();
    }

    /**
     * Updates next camera position.
     */
    public void update() {
        this.cameraX += this.camUpdateX;
        this.cameraY += this.camUpdateY;
        this.camera.setTranslateX(cameraX);
        this.camera.setTranslateY(cameraY);
    }

    /**
     * Sets the appropriate distance of the camera from the battle field.
     * 
     * @param scaling  the value by which the camera should be scaled
     */
    public void setCam(final double scaling) {
        this.camera.setScaleX(scaling);
        this.camera.setScaleY(scaling);
    }

    /**
     * @return camera translation X value
     */
    public double getCameraTranslationX() {
        return this.camera.getTranslateX();
    }

    /**
     * @return camera translation Y value
     */
    public double getCameraTranslationY() {
        return this.camera.getTranslateY();
    }

    /**
     * @return updated position of the next move of the camera
     */
    public final Point2D getCamUpdate() {
        final Point2D mousePosition = new Point2D(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());
        final Point2D mouseWindowPosition = new Point2D(mousePosition.getX() - this.stageController.getX() - this.stageController.getScene().getX(), mousePosition.getY() - this.stageController.getY() - this.stageController.getScene().getY());
        this.camUpdateX = (mouseWindowPosition.getX() - this.stageController.getWidth() / 2) / SPEED_SCALING;
        this.camUpdateY = (mouseWindowPosition.getY() - this.stageController.getHeight() / 2) / SPEED_SCALING;
        return new Point2D(camUpdateX, camUpdateY);
    }

    /**
     * Blocks the X value of the camera translation.
     */
    public void resetCamUpdateX() {
        this.camUpdateX = 0;
    }

    /**
     * Blocks the Y value of the camera translation.
     */
    public void resetCamUpdateY() {
        this.camUpdateY = 0;
    }
}
