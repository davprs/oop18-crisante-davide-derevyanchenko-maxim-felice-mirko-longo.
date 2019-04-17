package controller.menu.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.StageController;
import controller.menu.FXMLController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import model.account.Account;
import model.account.AccountImpl;
import model.account.AccountManager;
import model.account.AccountManagerImpl;
import utilities.AlertUtils;
import utilities.ErrorLog;
import utilities.FileUtils;
import utilities.GameUtils;
import utilities.SystemUtils;
import view.menu.login.RegisterView;

/**
 * This class controls the signing in of new accounts.
 *
 */
public class RegisterController implements FXMLController {

    private final AccountManager accountManager;
    private final StageController stageController;
    private final EventHandler<KeyEvent> registerHandler;
    private final EventHandler<KeyEvent> cancelHandler;
    private final EventHandler<KeyEvent> checkHandler;
    private final EventHandler<KeyEvent> confCheckHandler;
    @FXML
    private GridPane grid;
    @FXML
    private Label regLabel;
    @FXML
    private Label usrLabel;
    @FXML
    private Label pswLabel;
    @FXML
    private Label confPswLabel;
    @FXML
    private Label nickLabel;
    @FXML
    private Button closeBtn;
    @FXML
    private Button regBtn;
    @FXML
    private TextField usrField;
    @FXML
    private TextField nickField;
    @FXML
    private PasswordField pswField;
    @FXML
    private TextField pswTextField;
    @FXML
    private PasswordField confPswField;
    @FXML
    private TextField confPswTextField;
    @FXML
    private CheckBox pswCheckBox;
    @FXML
    private CheckBox confPswCheckBox;

    /**
     * Build the RegisterController.
     * @param stageController the controller of the stage
     */
    public RegisterController(final StageController stageController) {
        this.stageController = stageController;
        this.accountManager = this.initAccountManager();
        this.registerHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                checkKeyEvent(event, regBtn);
            }
        };
        this.cancelHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                checkKeyEvent(event, closeBtn);
            }
        };
        this.checkHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                checkKeyEvent(event, pswCheckBox);
            }
        };
        this.confCheckHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                checkKeyEvent(event, confPswCheckBox);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(new RegisterView(this).getScene());
    }

    /**
     * Close this window.
     */
    @FXML
    public void close() {
        new LoginController(this.stageController).start();
    }

    /**
     * Register the account.
     */
    @FXML
    public void register() {
        if (checkFields()) {
            final Account account = new AccountImpl.Builder(this.usrField.getText(), getPassword())
                                                   .withNickname(this.nickField.getText())
                                                   .build();
            if (this.accountManager.isPresent(account)) {
                this.setBlur();
                AlertUtils.createRegisterAccountError();
                this.setTransparentBlur();
            } else {
                this.accountManager.register(account);
                try {
                    FileUtils.printAccount(account);
                } catch (IOException e) {
                    ErrorLog.getLog().printError();
                    System.exit(0);
                }
                this.setBlur();
                AlertUtils.createRegisterAccountDialog();
                this.setTransparentBlur();
                this.close();
            }
        }
    }

    /**
     * Change the visibility of the first PasswordField.
     */
    @FXML
    public void changePswField() {
        togglePasswordVisibility(this.pswCheckBox, this.pswField, this.pswTextField);
    }

    /**
     * Change the visibility of the second PasswordField.
     */
    @FXML
    public void changeConfPswField() {
       togglePasswordVisibility(this.confPswCheckBox, this.confPswField, this.confPswTextField);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
       setHandlers();
    }

    private AccountManager initAccountManager() {
        try {
            return new AccountManagerImpl(FileUtils.getAccounts());
        } catch (IOException e) {
            ErrorLog.getLog().printError();
            System.exit(0);
        }
        return null;
    }

    private void togglePasswordVisibility(final CheckBox cb, final PasswordField psw, final TextField text) {
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

    private boolean checkPassword() {
        if (this.pswField.getText().equals(this.confPswField.getText())) {
            return !this.pswField.getText().equals(SystemUtils.getEmptyString());
        }
        if (this.pswTextField.getText().equals(this.confPswTextField.getText())) {
            return !this.pswTextField.getText().equals(SystemUtils.getEmptyString());
        }
        return false;
    }

    private boolean checkUserField() {
        return !this.usrField.getText().equals("");
    }

    private boolean checkFields() {
        if (!this.checkUserField()) {
            this.setBlur();
            AlertUtils.createRegisterUsernameError();
            this.setTransparentBlur();
        } else if (!this.checkPassword()) {
            this.setBlur();
            AlertUtils.createRegisterPasswordError();
            this.setTransparentBlur();
        }
        return this.checkPassword() && this.checkUserField();
    }

    private String getPassword() {
        return this.pswCheckBox.isSelected() ? this.pswTextField.getText() : this.pswField.getText();
    }

    private void setHandlers() {
        this.regBtn.setOnKeyPressed(this.registerHandler);
        this.usrField.setOnKeyPressed(this.registerHandler);
        this.nickField.setOnKeyPressed(this.registerHandler);
        this.pswField.setOnKeyPressed(this.registerHandler);
        this.confPswField.setOnKeyPressed(this.registerHandler);
        this.pswTextField.setOnKeyPressed(this.registerHandler);
        this.confPswTextField.setOnKeyPressed(this.registerHandler);
        this.closeBtn.setOnKeyPressed(this.cancelHandler);
        this.pswCheckBox.setOnKeyPressed(this.checkHandler);
        this.confPswCheckBox.setOnKeyPressed(this.confCheckHandler);
    }

    private void setBlur() {
        this.grid.setEffect(GameUtils.getBlurEffect());
    }

    private void setTransparentBlur() {
        this.grid.setEffect(GameUtils.getTransparentEffect());
    }

    private void checkKeyEvent(final KeyEvent event, final ButtonBase button) {
        if (event.getCode().compareTo(KeyCode.ENTER) == 0) {
            button.fire();
        }
    }
}
