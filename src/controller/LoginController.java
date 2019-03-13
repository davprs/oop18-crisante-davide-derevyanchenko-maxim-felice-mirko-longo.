package controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.account.Account;
import model.account.AccountImpl;
import model.account.AccountManager;
import model.account.AccountManagerImpl;
import utilities.ErrorType;
import utilities.FileUtils;
import utilities.StringUtils;
import view.login.LoginView;

/**
 * This class controls the login before starting the game.
 *
 */
public class LoginController {

    @FXML
    private Label login;
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private TextField usrField;
    @FXML
    private PasswordField pswField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button regBtn;
    @FXML
    private Button exitBtn;

    /**
     * Register a new account.
     */
    @FXML
    public void register() {
        new RegisterController().start();
    }

    /**
     * Try to log into the game.
     */
    @FXML
    public void tryLogin() {
        final Account account = AccountImpl.createSimpleAccount(usrField.getText(), pswField.getText());
        final AccountManager accManager = new AccountManagerImpl();
        if (accManager.isPresent(account)) {
            if (accManager.checkPassword(account)) {
                try {
                    startMenu(FileUtils.getAccountFromUsername(account.getUsername()));
                } catch (IOException e) {
                    System.out.println(StringUtils.ERROR_MESSAGE);
                    System.exit(0);
                }
            } else {
                startPasswordError();
            }
        } else {
            startAccountError();
        }
    }

    /**
     * Close the application.
     */
    @FXML
    public void exit() {
        Platform.exit();
    }

    private void startMenu(final Account account) {
        try {
            final Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.close();
            new MenuController();
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    private void startAccountError() {
        try {
            new ErrorController().start(ErrorType.lOGIN_USERNAME);
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    private void startPasswordError() {
        try {
            new ErrorController().start(ErrorType.LOGIN_PASSWORD);
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    /**
     * Main method.
     * @param args ignored.
     */
    public static void main(final String[] args) {
        LoginView.initialize();
    }
}
