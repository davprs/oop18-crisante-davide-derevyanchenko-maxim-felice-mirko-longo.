package controller.menu;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import controller.StageController;
import controller.game.LevelController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import model.account.Account;
import utilities.AlertUtils;
import utilities.GameUtils;
import view.menu.MenuView;

/**
 * class MenuController that controls the menu.
 */
public class MenuController implements FXMLController {

    private static final String PLAY_KEY = "play";
    private static final String HIGHSCORES_KEY = "highscore";
    private static final String OPTIONS_KEY = "options";
    private static final String EXIT_KEY = "exit";
    private final MenuView menuView;
    private final Account account;
    private final StageController stageController;
    private ResourceBundle bundle;
    @FXML
    private GridPane grid;
    @FXML
    private Button playBtn;
    @FXML
    private Button highscoreBtn;
    @FXML
    private Button optionsBtn;
    @FXML
    private Button exitBtn;

    /**
     * Build the MenuController.
     * @param account the account of the player
     * @param stageController the controller of the stage
     */
    public MenuController(final Account account, final StageController stageController) {
        this.account = account;
        this.stageController = stageController;
        this.menuView = new MenuView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(this.menuView.getScene());
        this.stageController.setDimension(this.account.getSettings().getResolution());
        if (account.getSettings().isSoundOn()) {
            GameUtils.MAIN_THEME.play();
        } else {
            GameUtils.muteAllSounds();
        }
    }

    /**
     * 
     */
    @FXML
    public void playTheGame() {
        new LevelController(this.account, this.stageController).start();
    }

    /**
     * 
     */
    @FXML
    public void checkHighscore() {
        new HighscoreController(this.account, this.stageController).start();
    }

    /**
     * 
     */
    @FXML
    public void enterOptions() {
        new OptionsController(this.account, this.stageController).start();
    }

    /**
     * 
     */
    @FXML
    public void exitGame() { 
        this.grid.setEffect(GameUtils.getBlurEffect());
        final Optional<ButtonType> exit = AlertUtils.createExitConfirmationDialog().showAndWait();
        if (exit.get() == ButtonType.YES) {
            GameUtils.muteAllSounds();
            Platform.exit();
        } else {
            this.grid.setEffect(GameUtils.getTransparentEffect());
        }
    }

    /**
     * Get the game account.
     * @return the account
     */
    public Account getAccount() {
        return this.account;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.bundle = resources;
        setLanguage();
    }

    private void setLanguage() {
        this.playBtn.setText(this.bundle.getString(PLAY_KEY));
        this.highscoreBtn.setText(this.bundle.getString(HIGHSCORES_KEY));
        this.optionsBtn.setText(this.bundle.getString(OPTIONS_KEY));
        this.exitBtn.setText(this.bundle.getString(EXIT_KEY));
    }
    /**
     * 
     * @return this grid.
     */
    public GridPane getRoot() {
        return this.grid;
    }

}
