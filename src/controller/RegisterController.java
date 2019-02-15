package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

/**
 * This class controls the signing in of new accounts.
 *
 */
public class RegisterController implements Initializable {

    private static final String SIGN_IN_KEY = "SIGN_IN";
    private static final String USERNAME_KEY = "username";
    private static final String NICKNAME_KEY = "nickname";
    private static final String PASSWORD_KEY = "password";
    private static final String CONF_PASSWORD_KEY = "confPassword";
    private static final String SIGN_KEY = "sign";
    private static final String CANCEL_KEY = "cancel";
    private static final String SHOW_PASSWORD_KEY = "showPassword";
    private final AccountManager manager = new AccountManagerImpl();
    private ResourceBundle bundle;
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
        if (!checkPassword()) {
            System.out.println("Errore");
            return;
        }
        final Account account = new AccountImpl(usrField.getText(), nickField.getText(), getPassword());
        if (manager.isPresent(account)) {
            System.out.println("Account già presente");
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

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        bundle = resources;
        setComponents();
    }

    private void setComponents() {
        regLabel.setText(bundle.getString(SIGN_IN_KEY));
        usrLabel.setText(bundle.getString(USERNAME_KEY));
        nickLabel.setText(bundle.getString(NICKNAME_KEY));
        pswLabel.setText(bundle.getString(PASSWORD_KEY));
        confPswLabel.setText(bundle.getString(CONF_PASSWORD_KEY));
        closeBtn.setText(bundle.getString(CANCEL_KEY));
        regBtn.setText(bundle.getString(SIGN_KEY));
        pswCheckBox.setText(bundle.getString(SHOW_PASSWORD_KEY));
        confPswCheckBox.setText(bundle.getString(SHOW_PASSWORD_KEY));
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

}
