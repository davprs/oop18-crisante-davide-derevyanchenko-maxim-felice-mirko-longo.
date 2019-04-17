package view.menu;

import java.io.IOException;
import java.util.ResourceBundle;
import controller.StageController;
import controller.game.PauseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.SubScene;
import model.account.Account;
import utilities.ErrorLog;
import utilities.SystemUtils;

/**
 * 
 * View of the Pause window.
 *
 */
public class PauseView {

    private static final String PAUSE_VIEW = "pauseView.fxml";
    private static final String PAUSE_BUNDLE = "game.PauseBundle";
    private static final double WIDTH = 5.48;
    private static final double HEIGHT = 3.08;
    private SubScene subScene;

    /**
     * 
     * @param account the game account
     * @param stageController the controller class of the stage
     * @param pauseController the controller of this class
     */
    public PauseView(final Account account, final StageController stageController, final PauseController pauseController) {
        SystemUtils.setLocale(account.getSettings().getLanguage());
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(PAUSE_VIEW), ResourceBundle.getBundle(PAUSE_BUNDLE));
        loader.setController(pauseController);
        try {
            this.subScene = new SubScene(loader.load(), stageController.getScene().getWidth() / WIDTH, stageController.getScene().getHeight() / HEIGHT);
        } catch (IOException e) {
            ErrorLog.getLog().printError();
            System.exit(0);
        }
    }

    /**
     * Get the SubScene.
     * @return the SubScene
     */
    public SubScene getSubScene() {
        return this.subScene;
    }

}
