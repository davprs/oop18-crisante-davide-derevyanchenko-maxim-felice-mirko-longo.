package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.account.Account;
import utilities.StringUtils;
import view.menu.HighscoreView;
/**
 * 
 * This class controls the highscore view.
 *
 */
public class HighscoreController implements Initializable {

    private static final String BACK_KEY = "back";
    private static final String LABEL_KEY = "highscore";
    private ResourceBundle bundle;
    private final Account account;
    private final HighscoreView view;
    @FXML
    private Button back;
    @FXML
    private Label label;
    /**
     * 
     * @param account is an account.
     */
    public HighscoreController(final Account account) {
        this.account = account;
        this.view = new HighscoreView(account.getSettings().getLanguage(), this);
    }

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.bundle = resources;
        setLanguage();
    }
    /**
     * Method to go back to the menu.
     */
    public void goBack() {
        final Stage stage = (Stage) this.back.getScene().getWindow();
        stage.close();
        new MenuController(this.account).start();
    }
    /**
     * 
     */
    private void setLanguage() {
        this.back.setText(this.bundle.getString(BACK_KEY));
        this.label.setText(this.bundle.getString(LABEL_KEY));
    }
    /**
     * Start method to load the view.
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
