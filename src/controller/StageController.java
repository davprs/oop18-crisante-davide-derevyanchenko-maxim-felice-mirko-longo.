package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller class of the Stage.
 */
public class StageController {

    private final Stage stage;

    /**
     * Build the StageController.
     * @param stage the stage to control
     */
    public StageController(final Stage stage) {
        this.stage = stage;
    }

    /**
     * Set the new Scene.
     * @param scene the scene to set
     */
    public void setScene(final Scene scene) {
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
    }

    /**
     * Set the FullScreen.
     * @param value the value to set
     */
    public void setFullScreen(final boolean value) {
        this.stage.setFullScreen(true);
    }
}
