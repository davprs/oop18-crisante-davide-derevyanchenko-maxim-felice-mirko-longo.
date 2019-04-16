package controller.game;

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
import view.game.LevelView;
/**
 * 
 * class LevelCotroller that controls the level of difficulty of the game.
 *
 */
public class LevelController implements FXMLController {

    private static final String LABEL_KEY = "label";
    private static final String EASY_KEY = "easy";
    private static final String MEDIUM_KEY = "medium";
    private static final String HARD_KEY = "hard";
    private static final String SURVIVAL_KEY = "survival";
    private static final String BACK_KEY = "back";
    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;
    private static final int SURVIVAL = 4;
    private final Account account;
    private final StageController stageController;
    private final MenuController menuController;
    private final LevelView levelView;
    private ResourceBundle bundle;
    @FXML
    private Label textLbl;
    @FXML
    private Button easyBtn;
    @FXML
    private Button mediumBtn;
    @FXML
    private Button hardBtn;
    @FXML
    private Button survivalBtn;
    @FXML
    private Button backBtn;
    @FXML
    private GridPane grid;
    /**
     * 
     * @param account account
     * @param stageController stage controller
     * @param menuController    menu controller
     */
    public LevelController(final Account account, final StageController stageController, final MenuController menuController) {
        this.account = account;
        this.stageController = stageController;
        this.menuController = menuController;
        this.levelView = new LevelView(this.account, this.stageController, this);
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
        this.menuController.getRoot().getChildren().add(this.levelView.getSubScene());
    }

    /**
     * 
     */
    @FXML
    public void setEasy() {
        this.startGame(EASY);
    }

    /**
     * 
     */
    @FXML
    public void setMedium() {
        this.startGame(MEDIUM);
    }

    /**
     * 
     */
    @FXML
    public void setHard() {
        this.startGame(HARD);
    }

    /**
     * 
     */
    @FXML
    public void setSurvival() {
        this.startGame(SURVIVAL);
    }

    /**
     * 
     */
    @FXML
    public void goBack() {
        new MenuController(this.account, this.stageController).start();
    }

    private void setLanguage() {
        this.textLbl.setText(this.bundle.getString(LABEL_KEY));
        this.easyBtn.setText(this.bundle.getString(EASY_KEY));
        this.mediumBtn.setText(this.bundle.getString(MEDIUM_KEY));
        this.hardBtn.setText(this.bundle.getString(HARD_KEY));
        this.survivalBtn.setText(this.bundle.getString(SURVIVAL_KEY));
        this.backBtn.setText(this.bundle.getString(BACK_KEY));
    }

    private void startGame(final int level) {
        new GameController(this.account, this.stageController, level);
    }
}
