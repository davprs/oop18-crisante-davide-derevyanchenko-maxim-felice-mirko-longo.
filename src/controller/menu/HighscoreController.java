package controller.menu;

import javafx.scene.control.TableView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.FXMLController;
import controller.StageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.account.Account;
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
    private ResourceBundle bundle;
    private final Account account;
    private final StageController stageController;
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
     * 
     * @param account is an account.
     * @param stageController 
     */
    public HighscoreController(final Account account, final StageController stageController) {
        this.account = account;
        this.stageController =  stageController;
    }

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.bundle = resources;
        setLanguage();
        this.nickname.setCellValueFactory(new PropertyValueFactory<>("username"));
        this.highscore.setCellValueFactory(new PropertyValueFactory<>("bestScore"));
        try {
            this.table.getItems().addAll(FileUtils.getAccounts());
            this.table.getSortOrder().add(highscore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to go back to the menu.
     */
    public void goBack() {
        new MenuController(this.account, this.stageController).start();
    }

    /**
     * 
     */
    private void setLanguage() {
        this.back.setText(this.bundle.getString(BACK_KEY));
        this.label.setText(this.bundle.getString(LABEL_KEY));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(new HighscoreView(this.account, this).getScene());
    }

}
