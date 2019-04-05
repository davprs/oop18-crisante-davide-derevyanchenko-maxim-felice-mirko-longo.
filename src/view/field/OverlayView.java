package view.field;

import java.io.IOException;

import controller.StageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.SubScene;
import javafx.scene.layout.GridPane;
import utilities.ErrorLog;

/**
 * 
 *
 */
public class OverlayView {

    private static final String OVERLAY_VIEW = "overlayView.fxml";
    private final SubScene overlay;
    private GridPane root;

    /**
     * Build the OverlayView.
     * @param stageController the controller of the main stage
     */
    public OverlayView(final StageController stageController) {
        try {
            this.root = FXMLLoader.load(ClassLoader.getSystemResource(OVERLAY_VIEW));
        } catch (IOException e) {
            ErrorLog.getLog().printError();
            System.exit(0);
        }
        this.overlay = new SubScene(this.root, stageController.getScene().getWidth(), stageController.getScene().getHeight());
    }

    /**
     * 
     * @return the subScene 
     */
    public SubScene getSubScene() {
        return this.overlay;
    }

    /**
     * 
     * @return the root
     */
    public GridPane getRoot() {
        return this.root;
    }
}
