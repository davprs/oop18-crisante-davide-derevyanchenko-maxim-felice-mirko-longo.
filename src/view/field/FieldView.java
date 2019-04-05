package view.field;

import java.awt.Dimension;
import java.awt.Toolkit;

import controller.StageController;
import javafx.geometry.Rectangle2D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 * The class that represents the view of the whole field.
 *
 */
public class FieldView {

    private final Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
    private final Camera cam = new PerspectiveCamera();
    private final Canvas canvas = new Canvas(res.getWidth(), res.getHeight());
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    private final SubScene scene;
    private final Group root;

    /**
     * Build the MenuView.
     * @param stageController the controller of the stage 
     */
    public FieldView(final StageController stageController) {
        this.root = new Group();
        this.scene = new SubScene(this.root, stageController.getWidth(), stageController.getHeight());
        this.scene.setFill(Color.BLACK);
        this.scene.setCamera(this.cam);
        this.root.getChildren().add(this.canvas);
    }

    /**
     * Method that draws an entity on the battle field.
     * 
     * @param image the image to be drawn
     * @param angle the angle by which the entity should be rotated
     * @param boundary the rectangle in which the entity should be drawn
     */
    public void drawEntity(final Image image, final double angle, final Rectangle2D boundary) {
        final double modifiedAngle = angle + 90;
        final Rotate r = new Rotate(modifiedAngle, boundary.getMinX() + boundary.getWidth() / 2, boundary.getMinY() + boundary.getHeight() / 2);
        gc.save();
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        gc.drawImage(image, boundary.getMinX(), boundary.getMinY(), boundary.getWidth(), boundary.getHeight());
        gc.restore();
    }

    /**
     * Method that draws the background of the battlefield.
     * 
     * @param image the background image
     */
    public void drawBackground(final Image image) {
        gc.drawImage(image, 0, 0);
    }

    /**
     * 
     * @return the canvas of the window
     */
    public Canvas getCanvas() {
        return this.canvas;
    }

    /**
     * 
     * @return the camera of the scene
     */
    public Camera getCamera() {
        return this.cam;
    }

    /**
     * 
     * @return the scene
     */
    public SubScene getScene() {
        return this.scene;
    }

    /**
     * 
     * @return the root
     */
    public Group getRoot() {
        return this.root;
    }
}
