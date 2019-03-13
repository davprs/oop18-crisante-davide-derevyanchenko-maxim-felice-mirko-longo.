package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.account.Account;
import model.account.AccountImpl;
import model.account.AccountManager;
import model.account.AccountManagerImpl;
import utilities.ErrorType;
import utilities.StringUtils;
import view.register.RegisterView;

/**
 * This class controls the signing in of new accounts.
 *
 */
public class RegisterController {

    private final AccountManager manager = new AccountManagerImpl();
    private final RegisterView view = new RegisterView();
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
     * Starts the view.
     */
    public void start() {
        try {
            this.view.start(new Stage());
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }
    /**
     * Close this window.
     */
    @FXML
    public void close() {
        final Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * Register the account.
     */
    @FXML
    public void register() {
        if (usrField.getText().equals("")) {
            startUsernameError();
            return;
        }
        if (!checkPassword()) {
            startPasswordError();
            return;
        }
        final Account account = AccountImpl.createAccountWithNickname(usrField.getText(), getPassword(), nickField.getText());
        if (manager.isPresent(account)) {
            startAccountError();
        } else {
            manager.register(account);
            System.out.println("Account registrato");
            close();
        }
    }

    /**
     * Change the visibility of the first PasswordField.
     */
    @FXML
    public void changePswField() {
        togglePasswordVisibility(pswCheckBox, pswField, pswTextField);
    }

    /**
     * Change the visibility of the second PasswordField.
     */
    @FXML
    public void changeConfPswField() {
       togglePasswordVisibility(confPswCheckBox, confPswField, confPswTextField);
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
        if (pswField.getText().equals(confPswField.getText())) {
            return !pswField.getText().equals("");
        }
        if (pswTextField.getText().equals(confPswTextField.getText())) {
            return !pswTextField.getText().equals("");
        }
        return false;
    }

    private String getPassword() {
        return pswCheckBox.isSelected() ? pswTextField.getText() : pswField.getText();
    }

    private void startPasswordError() {
        try {
            new ErrorController().start(ErrorType.REGISTER_PASSWORD);
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    private void startAccountError() {
        try {
            new ErrorController().start(ErrorType.REGISTER_ACCOUNT);
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    private void startUsernameError() {
        try {
            new ErrorController().start(ErrorType.REGISTER_USERNAME);
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }
}
