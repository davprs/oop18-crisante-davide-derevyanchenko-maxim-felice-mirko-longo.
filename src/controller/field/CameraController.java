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
    private double translationX;
    private double translationY;

    /**
     * Constructor of the CameraController.
     * 
     * @param camera the camera to control
     */
    public CameraController(final Camera camera) {
        this.camera = camera;
        this.cameraX = 0;
        this.cameraY = 0;
        this.translationX = 0;
        this.translationY = 0;
    }

    /**
     * Updates next camera position.
     * 
     * @param nextMove
     */
    public final void update() {
        this.cameraX += this.translationX;
        this.cameraY += this.translationY;
        this.resetTranslation();
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

    private void resetTranslation() {
        this.translationX = 0;
        this.translationY = 0;
    }

    /**
     * Method that sets the translation vector for the camera.
     * 
     * @param translationVector the translation vector of the camera
     */
    public final void setTranslation(final Point2D translationVector) {
        this.translationX += translationVector.getX();
        this.translationY += translationVector.getY();
    }
}
