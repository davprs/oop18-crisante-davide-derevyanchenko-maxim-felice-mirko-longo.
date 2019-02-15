package controller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.utilities.StringUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.account.Account;
import model.account.AccountImpl;
import model.account.AccountManager;
import model.account.AccountManagerImpl;
import view.LanguageView;
import view.RegisterView;
/**
 * This class controls the login before starting the game.
 *
 */
public class LoginController implements Initializable {

    private static final String LOGIN_KEY = "login";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String REGISTER_KEY = "register";
    private static final String LOGIN_BTN_KEY = "LOGIN";
    private static final String EXIT_KEY = "exit";
    private ResourceBundle bundle;
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
        try {
            new RegisterView(bundle.getLocale().getLanguage()).start(new Stage());
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    /**
     * Try to log into the game.
     */
    @FXML
    public void tryLogin() {
        final Account account = new AccountImpl(usrField.getText(), pswField.getText());
        final AccountManager accManager = new AccountManagerImpl();
        if (accManager.isPresent(account)) {
            if (accManager.checkPassword(account)) {
                try {
                    final Stage stage = (Stage) loginBtn.getScene().getWindow();
                    stage.close();
                    new MenuController().start(new Stage());
                } catch (Exception e) {
                    System.out.println(StringUtils.ERROR_MESSAGE);
                    Platform.exit();
                }
            }
            System.out.println("Password errata");
        }
        System.out.println("Account inesistente");
    }

    /**
     * Close the application.
     */
    @FXML
    public void exit() {
        Platform.exit();
    }

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        bundle = resources;
        setComponents();
        try {
            new LanguageView().start(new Stage());
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    private void setComponents() {
        login.setText(bundle.getString(LOGIN_KEY));
        username.setText(bundle.getString(USERNAME_KEY));
        password.setText(bundle.getString(PASSWORD_KEY));
        regBtn.setText(bundle.getString(REGISTER_KEY));
        loginBtn.setText(bundle.getString(LOGIN_BTN_KEY));
        exitBtn.setText(bundle.getString(EXIT_KEY));
    }

}
