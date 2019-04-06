package controller.field;

import javafx.geometry.Point2D;
import javafx.scene.Camera;

/**
 * Class that controls the camera.
 * 
 */
public class CameraController {

    private final Camera camera;
    private double cameraX;
    private double cameraY;
    private double camUpdateX;
    private double camUpdateY;

    /**
     * Constructor of the CameraController.
     * 
     * @param camera the camera to control
     */
    public CameraController(final Camera camera) {
        this.camera = camera;
        this.cameraX = 0;
        this.cameraY = 0;
        this.update();
    }

    /**
     * Updates next camera position.
     */
    public final void update() {
        this.cameraX += this.camUpdateX;
        this.cameraY += this.camUpdateY;
        this.camera.setTranslateX(this.cameraX);
        this.camera.setTranslateY(this.cameraY);
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
     * Gets the camera X translation value to get to the next position.
     * 
     * @return camera translation X value
     */
    public double getCameraTranslationX() {
        return this.camera.getTranslateX();
    }

    /**
     * Gets the camera Y translation value to get to the next position.
     * 
     * @return camera translation Y value
     */
    public double getCameraTranslationY() {
        return this.camera.getTranslateY();
    }

    /**
     * Sets the translation values of the camera.
     * 
     * @param nextMove the Point2D that represents the next position of the camera
     */
    public void setCamUpdate(final Point2D nextMove) {
        this.camUpdateX = nextMove.getX();
        this.camUpdateY = nextMove.getY();
    }
}
