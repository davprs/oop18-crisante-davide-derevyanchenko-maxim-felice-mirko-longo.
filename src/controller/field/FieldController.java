package controller.field;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import view.field.FieldView;

/**
 * The field controller's class.
 *
 */
public class FieldController {

    private static final Image SPACE_IMAGE = new Image("space.png");
    private final CharacterController shipController;
    private final CameraController camController;

    /**
     * Method that initialize the field controller.
     * 
     * @param view the view of the field
     */
    public FieldController(final FieldView view) {
        this.camController = new CameraController(view.getStage(), view.getCamera());
        this.shipController = new CharacterController(view, camController);
        this.shipController.draw();
        final AnimationTimer loop =  new AnimationTimer() {

            public void handle(final long currentNanoTime) {

                view.drawBackground(SPACE_IMAGE);
                if (shipController.isCamMoving()) {
                    shipController.update();
                    camController.update();
                }
                shipController.draw();
            }
        };
        loop.start();
    }

}
