package controller;

import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utilities.ImageUtils;
import utilities.SystemUtils;

/**
 * Controller class of the Stage(Window).
 */
public class StageController {

    private static final String F11 = "F11";
    private static final String FULLSCREEN_MESSAGE = "Press F11 to exit fullscreen and press ESC to pause";
    private final Stage stage;

    /**
     * Build the StageController.
     * @param stage the stage to control
     */
    public StageController(final Stage stage) {
        this.stage = stage;
        this.stage.getIcons().add(ImageUtils.getIconImage());
        this.stage.setFullScreenExitKeyCombination(KeyCombination.valueOf(F11));
        this.stage.setFullScreenExitHint(FULLSCREEN_MESSAGE);
    }

    /**
     * Get the Width of the window.
     * @return the width of the stage
     */
    public double getWidth() {
        return this.stage.getWidth();
    }

    /**
     * Get the Height of the window.
     * @return the height of the stage
     */
    public double getHeight() {
        return this.stage.getHeight();
    }

    /**
     * Get the current main Scene of the window.
     * @return the current scene of the stage
     */
    public Scene getScene() {
        return this.stage.getScene();
    }

    /**
     * Get the horizontal location of the Stage on the screen.
     * @return the horizontal location of the Stage on the screen
     */
    public double getX() {
        return this.stage.getX();
    }

    /**
     * Get the vertical location of the Stage on the screen.
     * @return the vertical location of the Stage on the screen
     */
    public double getY() {
        return this.stage.getY();
    }

    /**
     * Set the new main Scene.
     * @param scene the scene to set
     */
    public void setScene(final Scene scene) {
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
    }

    /**
     * Set the new Dimension of the window.
     * @param dimension the dimension to set
     */
    public void setDimension(final Dimension2D dimension) {
        if (!this.stage.isFullScreen()) {
            if (dimension.getWidth() == SystemUtils.getScreenResolution().getWidth() && dimension.getHeight() == SystemUtils.getScreenResolution().getHeight()) {
                this.stage.setMaximized(true);
            } else {
                this.stage.setMaximized(false);
            }
            this.autosize(dimension);
            this.stage.setWidth(dimension.getWidth());
            this.stage.setHeight(dimension.getHeight());
            this.stage.centerOnScreen();
        }
    }

    /**
     * Set the FullScreen value of the window.
     * @param value the value to set
     */
    public void setFullScreen(final boolean value) {
        if (this.stage.getWidth() == SystemUtils.getScreenResolution().getWidth() && this.stage.getHeight() == SystemUtils.getScreenResolution().getHeight()) {
            this.stage.setFullScreen(value);
        }
    }

    /**
     * Set a window EventHandler.
     * @param windowHandler the window event handler to set
     */
    public void setWindowHandler(final EventHandler<WindowEvent> windowHandler) {
        this.stage.setOnCloseRequest(windowHandler);
    }

    private void autosize(final Dimension2D dimension) {
        this.stage.setMinWidth(dimension.getWidth());
        this.stage.setMinHeight(dimension.getHeight());
    }
}

