package controller.menu;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.FXMLController;
import controller.StageController;
import controller.field.FieldController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import model.account.Account;
import model.sounds.Sound;
import utilities.AlertUtils;
import view.menu.MenuView;

/**
 * class MenuController that controls the menu.
 */
public class MenuController implements FXMLController {

    private static final String PLAY_KEY = "play";
    private static final String HIGHSCORES_KEY = "highscore";
    private static final String OPTIONS_KEY = "options";
    private static final String EXIT_KEY = "exit";
    private final Account account;
    private ResourceBundle bundle;
    private final StageController stageController;

    @FXML
    private Button playBtn;
    @FXML
    private Button highscoreBtn;
    @FXML
    private Button optionsBtn;
    @FXML
    private Button exitBtn;

    /**
     * 
     * @param account 
     * @param stageController 
     */
    public MenuController(final Account account, final StageController stageController) {
        this.account = account;
        this.stageController = stageController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(new MenuView(this.account, this).getScene());
    }

    /**
     * 
     */
    @FXML
    public void playTheGame() {
        new FieldController(this.account, this.stageController);
        Sound.BUTTON_CLIKED.play();
        Sound.MAIN_THEME.stop();
        //Sound.GAMEPLAY_MUSIC.play();
        Sound.GAMEPLAY_MUSIC.play();
    }

    /**
     * 
     */
    @FXML
    public void checkHighscore() {
        new HighscoreController(this.account, this.stageController).start();
        Sound.BUTTON_CLIKED.play();
    }

    /**
     * 
     */
    @FXML
    public void enterOptions() {
        new OptionsController(this.account, this.stageController).start();
        Sound.BUTTON_CLIKED.play();
    }

    /**
     * 
     */
    @FXML
    public void exitGame() { 
        Sound.BUTTON_CLIKED.play();
       final  Optional<ButtonType> exit = AlertUtils.createExitConfirmationDialog().showAndWait();
        if (exit.get() == ButtonType.YES) {
            Platform.exit();
            Sound.MAIN_THEME.stop();
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

    private void setLanguage() {
        this.playBtn.setText(bundle.getString(PLAY_KEY));
        this.highscoreBtn.setText(bundle.getString(HIGHSCORES_KEY));
        this.optionsBtn.setText(bundle.getString(OPTIONS_KEY));
        this.exitBtn.setText(bundle.getString(EXIT_KEY));
    }

}
