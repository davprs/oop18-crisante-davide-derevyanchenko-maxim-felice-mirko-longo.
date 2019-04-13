package view.game;

import java.io.IOException;
import java.util.ResourceBundle;

import controller.StageController;
import controller.game.OverlayController;
import javafx.fxml.FXMLLoader;
import javafx.scene.SubScene;
import javafx.scene.layout.GridPane;
import model.account.Account;
import utilities.BundleUtils;
import utilities.ErrorLog;

/**
 * 
 *
 */
public class OverlayView {

    private static final String OVERLAY_VIEW = "overlayView.fxml";
    private static final String OVERLAY_BUNDLE = "game.OverlayBundle";
    private final SubScene overlay;
    private GridPane root;

    /**
     * Build the OverlayView.
     * @param account the game account
     * @param stageController the controller of the main stage
     * @param overlayController the controller of this class
     */
    public OverlayView(final Account account, final StageController stageController, final OverlayController overlayController) {
        BundleUtils.setLocale(account.getSettings().getLanguage());
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(OVERLAY_VIEW), ResourceBundle.getBundle(OVERLAY_BUNDLE));
        loader.setController(overlayController);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            ErrorLog.getLog().printError(e);
            System.exit(0);
        }
        this.overlay = new SubScene(this.root, stageController.getScene().getWidth(), stageController.getScene().getHeight());
    }

    /**
     * Get the SubScene.
     * @return the subScene 
     */
    public SubScene getSubScene() {
        return this.overlay;
    }

    /**
     * Get the root.
     * @return the root
     */
    public GridPane getRoot() {
        return this.root;
    }
}
