package controller;

import java.net.URL;
import java.util.ResourceBundle;
import utilities.StringUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.account.Account;
import view.menu.MenuView;
import view.field.FieldView;

/**
 * class MenuController that controls the menu.
 */
public class MenuController implements Initializable {

    private static final String PLAY_KEY = "play";
    private static final String HIGHSCORES_KEY = "highscore";
    private static final String OPTIONS_KEY = "options";
    private static final String EXIT_KEY = "exit";
    private final MenuView view;
    private final Account account;
    private ResourceBundle bundle;
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
     */
    public MenuController(final Account account) {
        this.account = account;
        this.view = new MenuView(account.getSettings().getLanguage(), this);
    }
    /**
     * 
     */
    @FXML
    public void playTheGame() {
        final Stage stage = (Stage) this.playBtn.getScene().getWindow();
        stage.close();
        try {
            new FieldView().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    @FXML
    public void checkHighscore() {
        final Stage stage = (Stage) this.playBtn.getScene().getWindow();
        stage.close();
        new HighscoreController(account).start();
    }

    /**
     * 
     */
    @FXML
    public void enterOptions() {
        final Stage stage = (Stage) this.playBtn.getScene().getWindow();
        stage.close();
        new OptionsController(account).start();
    }

    /**
     * 
     */
    @FXML
    public void exitGame() {
        Platform.exit();
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

    /**
     * 
     */
    public void start() {
        try {
            this.view.start(new Stage());
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            System.exit(0);
        }
    }

}






