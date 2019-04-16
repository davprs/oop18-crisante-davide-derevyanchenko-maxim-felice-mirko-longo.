package view.game;

import java.io.IOException;
import java.util.ResourceBundle;
import controller.StageController;
import controller.game.LevelController;
import javafx.fxml.FXMLLoader;
import javafx.scene.SubScene;
import model.account.Account;
import utilities.BundleUtils;
/**
 * 
 * View of the Level View.
 *
 */
public class LevelView {

    private static final String LEVEL_VIEW = "levelView.fxml";
    private static final String LEVEL_BUNDLE = "game.LevelBundle";
    private static final double WIDTH = 2.02;
    private static final double HEIGHT = 1.70;
    private SubScene subScene;

    /**
     * 
     * @param account this account
     * @param stageController this stage controller
     * @param levelController of this controller
     */
    public LevelView(final Account account, final StageController stageController, final LevelController levelController) {
        BundleUtils.setLocale(account.getSettings().getLanguage());
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(LEVEL_VIEW), ResourceBundle.getBundle(LEVEL_BUNDLE));
        loader.setController(levelController);
        try {
            this.subScene = new SubScene(loader.load(), stageController.getScene().getWidth() / WIDTH, stageController.getScene().getHeight() / HEIGHT);
        } catch (IOException e) {
            System.out.println(e);
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
