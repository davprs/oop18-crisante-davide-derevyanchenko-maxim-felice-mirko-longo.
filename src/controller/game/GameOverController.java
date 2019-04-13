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
import view.game.GameOverView;
/**
 * 
 *  the class controls when the game is over.
 *
 */
public class GameOverController implements FXMLController {
    private static final String LABEL_KEY = "gameOver";
    private static final String GO_TO_MENU_KEY = "menu";
    private final Account account;
    private final StageController stageController;
    private final GameController gameController;
    private final GameOverView gameOverView;
    private ResourceBundle bundle;
    @FXML
    private Label gameOver;
    @FXML
    private Button menu;
    /**
     * 
     * @param account account
     * @param stageController stageController
     * @param gameController after game over
     */
    public GameOverController(final Account account, final StageController stageController, final GameController gameController) {
        this.account = account;
        this.stageController = stageController;
        this.gameController = gameController;
        this.gameOverView = new GameOverView(this.account, this.stageController, this);
    }

    /**
     * 
     */
    @FXML
    public void goToMenu() {
        this.gameController.getView().getRoot().setEffect(GameUtils.getTransparentEffect());
        new MenuController(this.account, this.stageController).start();
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
        this.gameController.getView().getRoot().getChildren().add(gameOverView.getSubScene());
    }

    private void setLanguage() {
        this.gameOver.setText(bundle.getString(LABEL_KEY));
        this.menu.setText(bundle.getString(GO_TO_MENU_KEY));
    }

}
