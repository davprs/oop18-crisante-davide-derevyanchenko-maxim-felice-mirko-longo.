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
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import model.account.Account;
import utilities.AlertUtils;
import utilities.SoundUtils;
import view.menu.MenuView;

/**
 * class MenuController that controls the menu.
 */
public class MenuController implements FXMLController {

    private static final String PLAY_KEY = "play";
    private static final String HIGHSCORES_KEY = "highscore";
    private static final String OPTIONS_KEY = "options";
    private static final String EXIT_KEY = "exit";
    private static final int BLUR_EFFECT_RANGE = 5;
    private final BoxBlur blur = new BoxBlur(BLUR_EFFECT_RANGE, BLUR_EFFECT_RANGE, BLUR_EFFECT_RANGE);
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
    @FXML
    private GridPane grid;
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
//        this.stageController.autosize();
        this.stageController.setDimension(this.account.getSettings().getResolution());
        this.stageController.setFullScreen(this.account.getSettings().isFullScreenOn());
    }

    /**
     * 
     */
    @FXML
    public void playTheGame() {
        new FieldController(this.account, this.stageController);
        SoundUtils.BUTTON_CLICKED.play();
        SoundUtils.MAIN_THEME.stop();
        SoundUtils.GAMEPLAY_MUSIC.play();
    }

    /**
     * 
     */
    @FXML
    public void checkHighscore() {
        new HighscoreController(this.account, this.stageController).start();
        SoundUtils.BUTTON_CLICKED.play();
    }

    /**
     * 
     */
    @FXML
    public void enterOptions() {
        new OptionsController(this.account, this.stageController).start();
        SoundUtils.BUTTON_CLICKED.play();

    }

    /**
     * 
     */
    @FXML
    public void exitGame() { 
        grid.setEffect(blur);
        SoundUtils.BUTTON_CLICKED.play();
       final  Optional<ButtonType> exit = AlertUtils.createExitConfirmationDialog().showAndWait();
        if (exit.get() == ButtonType.YES) {
            Platform.exit();
            SoundUtils.BUTTON_CLICKED.play();
            SoundUtils.muteAllSounds();
        } else {
            grid.setEffect(null);
            SoundUtils.BUTTON_CLICKED.play();
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
