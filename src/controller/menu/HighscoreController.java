package controller.menu;

import javafx.scene.control.TableView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.StageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.account.Account;
import utilities.ErrorLog;
import utilities.FileUtils;
import view.menu.HighscoreView;

/**
 * 
 * This class controls the highscore view.
 *
 */
public class HighscoreController implements FXMLController {

    private static final String BACK_KEY = "back";
    private static final String LABEL_KEY = "highscore";
    private static final String NICKNAME_KEY = "nickname";
    private static final String HIGHSCORE_KEY = "bestscore";
    private final Account account;
    private final StageController stageController;
    private ResourceBundle bundle;
    @FXML
    private Button back;
    @FXML
    private Label label;
    @FXML
    private TableView<Account> table;
    @FXML
    private TableColumn<Account, String> nickname;
    @FXML
    private TableColumn<Account, String> highscore;

    /**
     * Build the HighScoreController.
     * @param account is an account.
     * @param stageController 
     */
    public HighscoreController(final Account account, final StageController stageController) {
        this.account = account;
        this.stageController =  stageController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.bundle = resources;
        setLanguage();
        this.nickname.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        this.highscore.setCellValueFactory(new PropertyValueFactory<>("bestScore"));
        try {
            this.table.getItems().addAll(FileUtils.getAccounts());
        } catch (IOException e) {
            System.out.println(e);
            ErrorLog.getLog().printError();
            System.exit(0);
        }
        this.table.getSortOrder().add(highscore);
    }

    /**
     * Method to go back to the menu.
     */
    @FXML
    public void goBack() {
        new MenuController(this.account, this.stageController).start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(new HighscoreView(this.account, this).getScene());
        this.stageController.setMinResolution();
    }

    private void setLanguage() {
        this.back.setText(this.bundle.getString(BACK_KEY));
        this.label.setText(this.bundle.getString(LABEL_KEY));
        this.nickname.setText(this.bundle.getString(NICKNAME_KEY));
        this.highscore.setText(this.bundle.getString(HIGHSCORE_KEY));
    }
}
