package controller.game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.StageController;
import controller.menu.FXMLController;
import controller.menu.MenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.account.Account;
import utilities.ErrorLog;
import utilities.FileUtils;
import utilities.GameUtils;
import view.game.GameOverView;

/**
 * The class controls when the game is over.
 *
 */
public class GameOverController implements FXMLController {

    private static final String LABEL_KEY = "gameOver";
    private static final String GO_TO_MENU_KEY = "menu";
    private static final String WIN_KEY = "win";
    private final Account account;
    private final StageController stageController;
    private final GameController gameController;
    private final GameOverView gameOverView;
    private ResourceBundle bundle;
    @FXML
    private Label gameOver;
    @FXML
    private Button menu;
    @FXML
    private GridPane grid;
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
     * This method bring to the Main menu.
     */
    @FXML
    public void goToMenu() {
        try {
            this.gameController.getGameView().getRoot().setEffect(GameUtils.getTransparentEffect());
            this.account.setBestScore(this.gameController.getScore().getScorePoints());
            FileUtils.printAccount(this.account);
            new MenuController(this.account, this.stageController).start();
        } catch (IOException e) {
            ErrorLog.getLog().printError();
            System.exit(0);
        }
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
        this.gameController.getGameView().getRoot().getChildren().add(gameOverView.getSubScene());
    }

    private void setLanguage() {
        if (!this.gameController.getFieldController().getCharacter().getEntity().isAlive()) {
            this.gameOver.setText(this.bundle.getString(LABEL_KEY));
        } else {
            this.gameOver.setText(this.bundle.getString(WIN_KEY));
        }
        this.menu.setText(bundle.getString(GO_TO_MENU_KEY));
    }

}
