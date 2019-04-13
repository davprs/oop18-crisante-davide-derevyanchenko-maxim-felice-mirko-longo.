package view.game;

import controller.StageController;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

/**
 * View of the Game.
 *
 */
public class GameView {

    private final Scene overlay;
    private final StackPane root;

    /**
     * Build the OverlayView.
     * @param stageController the controller of the main stage
     */
    public GameView(final StageController stageController) {
        this.root = new StackPane();
        this.overlay = new Scene(this.root, stageController.getWidth(), stageController.getHeight());
    }

    /**
     * 
     * @return the scene
     */
    public Scene getScene() {
        return this.overlay;
    }

    /**
     * 
     * @return the root
     */
    public StackPane getRoot() {
        return this.root;
    }
}
