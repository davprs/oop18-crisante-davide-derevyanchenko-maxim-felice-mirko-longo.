package view.game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;
import controller.StageController;
import javafx.geometry.Rectangle2D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

/**
 * The class that represents the view of the whole field.
 *
 */
public class FieldView {

    private static final Image SPACE_IMAGE = new Image("space.png");
    private static final String EXPLOSION_FILE_NAME = "explosion";
    private static final String EXTENSION = ".png";
    private static final List<Image> EXPLOSION = new LinkedList<>();
    private static final int FRAME_AMOUNT = 10;
    private final Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
    private final Camera cam = new ParallelCamera();
    private final Canvas canvas = new Canvas(res.getWidth(), res.getHeight());
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    private final SubScene subScene;
    private final Group root;

    /**
     * Build the MenuView.
     * @param stageController the controller of the stage 
     */
    public FieldView(final StageController stageController) {
        this.root = new Group();
        this.subScene = new SubScene(this.root, stageController.getWidth(), stageController.getHeight());
        //this.subScene.setFill(new ImagePattern(SPACE_IMAGE, 1000, 1000, 1000, 1000, true));
        this.subScene.setCamera(this.cam);
        this.root.getChildren().add(this.canvas);
        for (int i = 0; i < FRAME_AMOUNT; i++) {
            EXPLOSION.add(new Image(EXPLOSION_FILE_NAME + i + EXTENSION));
        }
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
        this.gc.save();
        this.gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        this.gc.drawImage(image, boundary.getMinX(), boundary.getMinY(), boundary.getWidth(), boundary.getHeight());
        this.gc.restore();
    }

    /**
     * Method that draws the background of the battlefield.
     */
    public void drawBackground() {
        this.gc.drawImage(SPACE_IMAGE, 0, 0);
    }

    /**
     * Method that create an explosion animation in a specified boundary.
     * 
     * @param boundary the boundary in which show the explosion
     * @param frame the frame of the explosion to be drawn
     */
    public void drawExplosion(final Rectangle2D boundary, final int frame) {
        this.gc.drawImage(EXPLOSION.get(frame), boundary.getMinX(), boundary.getMinY(), boundary.getWidth(), boundary.getHeight());
    }

    /**
     * Method that gets the Canvas that represents the field.
     * 
     * @return the canvas of the window
     */
    public Canvas getCanvas() {
        return this.canvas;
    }

    /**
     * Method that gets the camera of the battle field scene.
     * 
     * @return the camera of the scene
     */
    public Camera getCamera() {
        return this.cam;
    }

    /**
     * Method that gets the subscene of the field.
     * 
     * @return the subscene
     */
    public SubScene getSubScene() {
        return this.subScene;
    }

    /**
     * Method that gets the parent root of the battle field.
     * 
     * @return the root
     */
    public Group getRoot() {
        return this.root;
    }
}
