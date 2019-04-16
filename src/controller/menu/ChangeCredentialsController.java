package controller.menu;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.StageController;
import controller.menu.login.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.account.Account;
import utilities.AlertUtils;
import utilities.ErrorLog;
import utilities.FileUtils;
import view.menu.ChangeCredentialsView;

/**
 * this class controlls the change credentials view.
 */
public class ChangeCredentialsController implements FXMLController {

    private static final String BACK_KEY = "back";
    private static final String LOGIN_KEY = "login";
    private static final String NICKNAME_KEY = "nickname";
    private static final String PASSWORD_KEY = "password"; 
    private static final String CONFIRM_KEY = "confirm";
    private final Account account;
    private final StageController stageController;
    private ResourceBundle bundle;
    @FXML
    private Label changeLbl;
    @FXML
    private Label changeNicknameLbl;
    @FXML
    private Label changePasswordLbl;
    @FXML
    private Button backBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField nicknameField;
    @FXML
    private PasswordField changePasswordField;
    @FXML
    private TextField visiblePasswordField;
    @FXML
    private CheckBox pswCheckBox;
    @FXML
    private GridPane grid;

    /**
     * Build the ChangeCredentials controller.
     * @param account is an account.
     * @param stageController controller
     */
    public ChangeCredentialsController(final Account account, final StageController stageController) {
        this.account = account;
        this.stageController = stageController;
    }

    /**
     *
     * 
     */
    @FXML
    public void newLogin() {
        final  Optional<ButtonType> confirmSettings = AlertUtils.createConfirmOptionsDialog().showAndWait();
        if (confirmSettings.get() == ButtonType.YES) {
            applyChanges();
            new LoginController(this.stageController).start();
        }
    }

    /**
     * 
     */
    @FXML
    public void revealPassword() {
        setPasswordVisible(pswCheckBox, changePasswordField, visiblePasswordField);
    }

    private void setPasswordVisible(final CheckBox cb, final PasswordField psw, final TextField text) {
        if (cb.isSelected()) {
            text.setText(psw.getText());
            text.setVisible(true);
            psw.setVisible(false);
        } else {
            psw.setText(text.getText());
            text.setVisible(false);
            psw.setVisible(true);
        }
    }

    /**
     * 
     */
    @FXML
    public void goBack() {
        final  Optional<ButtonType> confirmSettings = AlertUtils.createConfirmOptionsDialog().showAndWait();
        if (confirmSettings.get() == ButtonType.YES) {
            applyChanges();
            new OptionsController(this.account, this.stageController).start();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.bundle = resources;
        setLanguage();
        this.nicknameField.setText(this.account.getNickname());
        this.changePasswordField.setText(this.account.getPassword());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(new ChangeCredentialsView(this.account, this).getScene());
        this.stageController.setMinResolution();
    }

    private void applyChanges() {
        try {
            this.account.setNickname(this.nicknameField.getText());
            if (!getPassword().equals("")) {
                this.account.setPassword(getPassword());
            }
            FileUtils.printAccount(account);
        } catch (IOException e) {
            ErrorLog.getLog().printError();
            System.exit(0);
        }
    }


    private String getPassword() {
        return this.pswCheckBox.isSelected() ? this.visiblePasswordField.getText() : this.changePasswordField.getText();
    }

    private void setLanguage() {
        this.loginBtn.setText(this.bundle.getString(LOGIN_KEY));
        this.backBtn.setText(this.bundle.getString(BACK_KEY));
        this.changeLbl.setText(this.bundle.getString(CONFIRM_KEY));
        this.changeNicknameLbl.setText(this.bundle.getString(NICKNAME_KEY));
        this.changePasswordLbl.setText(this.bundle.getString(PASSWORD_KEY));
    }
}
