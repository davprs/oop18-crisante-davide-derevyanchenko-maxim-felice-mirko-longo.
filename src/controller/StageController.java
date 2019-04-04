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
     * 
     * @return the width of the stage
     */
    public double getWidth() {
        return this.stage.getWidth();
    }

    /**
     * 
     * @return the height of the stage
     */
    public double getHeight() {
        return this.stage.getHeight();
    }

    /**
     * 
     * @return the current scene of the stage
     */
    public Scene getScene() {
        return this.stage.getScene();
    }

    /**
     * 
     * @return the horizontal location of the Stage on the screen
     */
    public double getX() {
        return this.stage.getX();
    }

    /**
     * 
     * @return the vertical location of the Stage on the screen
     */
    public double getY() {
        return this.stage.getY();
    }

    /**
     * 
     * @param stage the stage in which set the owner
     */
    public void setOwner(final Stage stage) {
        stage.initOwner(this.stage);
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
        this.stage.setFullScreen(value);
    }
}
