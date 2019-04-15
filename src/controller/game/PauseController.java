
package controller.game;

import java.net.URL;
import java.util.ResourceBundle;

import controller.StageController;
import controller.menu.FXMLController;
import controller.menu.MenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.account.Account;
import utilities.GameUtils;
import view.menu.PauseView;

/**
 * 
 * the class controls the game when is paused.
 *
 */
public class PauseController implements FXMLController {

    private static final String LABEL_KEY = "label";
    private static final String RESUME_KEY = "resume";
    private static final String GO_TO_MENU_KEY = "menu";
    private final Account account;
    private final StageController stageController;
    private final GameController gameController;
    private final PauseView pauseView;
    private ResourceBundle bundle;
    @FXML
    private Label label;
    @FXML
    private Button resumeBtn;
    @FXML
    private Button menu;

    /**
     * 
     * @param account acount
     * @param stageController stageController
     * @param gameController the controller of the game
     */
    public PauseController(final Account account, final StageController stageController, final GameController gameController) {
        this.account = account;
        this.stageController = stageController;
        this.gameController = gameController;
        this.pauseView = new PauseView(this.account, this.stageController, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.bundle = resources;
        setLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.gameController.getFieldView().getRoot().setEffect(GameUtils.getBlurEffect());
        this.gameController.getOverlayController().getView().getRoot().setEffect(GameUtils.getBlurEffect());
        this.gameController.getGameView().getRoot().getChildren().add(pauseView.getSubScene());
    }

    /**
     * 
     */
    @FXML
    public void resume() {
        this.gameController.getFieldView().getRoot().setEffect(GameUtils.getTransparentEffect());
        this.gameController.getOverlayController().getView().getRoot().setEffect(GameUtils.getTransparentEffect());
        this.gameController.getGameView().getRoot().getChildren().remove(this.pauseView.getSubScene());
        this.gameController.getFieldController().getCharacter().setLastUpdate(System.currentTimeMillis());
        this.gameController.setInPause(false);
        if (account.getSettings().isSoundOn()) {
            GameUtils.GAMEPLAY_MUSIC.loop();
        }
    }

    /**
     * 
     */
    @FXML
    public void goBackToMenu() {
        this.gameController.getGameView().getRoot().setEffect(GameUtils.getTransparentEffect());
        this.gameController.setEnded(true);
        new MenuController(this.account, this.stageController).start();
    }

    /**
     * 
     */
    private void setLanguage() {
        this.label.setText(bundle.getString(LABEL_KEY));
        this.resumeBtn.setText(bundle.getString(RESUME_KEY));
        this.menu.setText(bundle.getString(GO_TO_MENU_KEY));
    }

}
