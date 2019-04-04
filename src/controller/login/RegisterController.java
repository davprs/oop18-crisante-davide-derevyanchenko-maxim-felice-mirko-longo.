package controller.login;

import java.net.URL;
import java.util.ResourceBundle;

import controller.FXMLController;
import controller.StageController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.account.Account;
import model.account.ABCAccountImpl;
import model.account.AccountManager;
import model.account.AccountManagerImpl;
import utilities.AlertUtils;
import view.login.RegisterView;

/**
 * This class controls the signing in of new accounts.
 *
 */
public class RegisterController implements FXMLController {

    private final AccountManager accManager = new AccountManagerImpl();
    private final StageController stageController;
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
    private final EventHandler<KeyEvent> registerHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(final KeyEvent event) {
            if (event.getCode().compareTo(KeyCode.ENTER) == 0) {
                regBtn.fire();
            } 
        }
    };
    private final EventHandler<KeyEvent> cancelHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(final KeyEvent event) {
            if (event.getCode().compareTo(KeyCode.ENTER) == 0) {
                closeBtn.fire();
            } 
        }
    };
    private final EventHandler<KeyEvent> checkHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(final KeyEvent event) {
            if (event.getCode().compareTo(KeyCode.ENTER) == 0) {
                pswCheckBox.fire();
            } 
        }
    };
    private final EventHandler<KeyEvent> confCheckHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(final KeyEvent event) {
            if (event.getCode().compareTo(KeyCode.ENTER) == 0) {
                confPswCheckBox.fire();
            } 
        }
    };

    /**
     * Build the RegisterController.
     * @param stageController the StageController
     */
    public RegisterController(final StageController stageController) {
        this.stageController = stageController;
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
        if (!checkUserField()) {
            AlertUtils.createRegisterUsernameError();
        } else if (!checkPassword()) {
            AlertUtils.createRegisterPasswordError();
        }
        if (checkFields()) {
            final Account account = ABCAccountImpl.createAccountWithNickname(this.usrField.getText(), getPassword(), this.nickField.getText());
            if (this.accManager.isPresent(account)) {
                AlertUtils.createRegisterAccountError();
            } else {
                this.accManager.register(account);
                AlertUtils.createRegisterAccountDialog();
                close();
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
            return !this.pswField.getText().equals("");
        }
        if (this.pswTextField.getText().equals(this.confPswTextField.getText())) {
            return !this.pswTextField.getText().equals("");
        }
        return false;
    }

    private boolean checkUserField() {
        return !this.usrField.getText().equals("");
    }

    private boolean checkFields() {
        return this.checkPassword() && this.checkUserField();
    }

    private String getPassword() {
        return this.pswCheckBox.isSelected() ? this.pswTextField.getText() : this.pswField.getText();
    }

    private void setHandlers() {
        this.regBtn.setOnKeyPressed(registerHandler);
        this.usrField.setOnKeyPressed(registerHandler);
        this.nickField.setOnKeyPressed(registerHandler);
        this.pswField.setOnKeyPressed(registerHandler);
        this.confPswField.setOnKeyPressed(registerHandler);
        this.pswTextField.setOnKeyPressed(registerHandler);
        this.confPswTextField.setOnKeyPressed(registerHandler);
        this.closeBtn.setOnKeyPressed(cancelHandler);
        this.pswCheckBox.setOnKeyPressed(checkHandler);
        this.confPswCheckBox.setOnKeyPressed(confCheckHandler);
    }
}
