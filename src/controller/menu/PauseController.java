package controller.menu;

import java.net.URL;
import java.util.ResourceBundle;
import controller.FXMLController;
import controller.StageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.account.Account;
import view.menu.PauseView;
/**
 * 
 * the class controls the game when is paused.
 *
 */
public class PauseController implements FXMLController {

    private final Account account;
    private ResourceBundle bundle;
    private final StageController stageController;
    private static final String LABEL_KEY = "label";
    private static final String RESUME_KEY = "resume";
    private static final String OPTIONS_KEY = "options";
    private static final String GO_TO_MENU_KEY = "menu";
    @FXML
    private Label label;
    @FXML
    private Button resume;
    @FXML
    private Button options;
    @FXML
    private Button menu;

    /**
     * 
     * @param account acount
     * @param stageController stageController
     * 
     */
    public PauseController(final Account account, final StageController stageController) {
        this.account = account;
        this.stageController = stageController;
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
        this.stageController.setScene(new PauseView(this.account, this.stageController).getScene());
    }

    /**
     * 
     */
    private void setLanguage() {
        this.label.setText(bundle.getString(LABEL_KEY));
        this.resume.setText(bundle.getString(RESUME_KEY));
        this.options.setText(bundle.getString(OPTIONS_KEY));
        this.menu.setText(bundle.getString(GO_TO_MENU_KEY));
    }

}
