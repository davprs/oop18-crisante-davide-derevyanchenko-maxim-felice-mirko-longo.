package controller.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.FXMLController;
import controller.MenuController;
import controller.StageController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.account.Account;
import model.account.AccountImpl;
import model.account.AccountManager;
import model.account.AccountManagerImpl;
import utilities.AlertUtils;
import utilities.FileUtils;
import utilities.StringUtils;
import view.login.LoginView;

/**
 * This class controls the login before starting the game.
 *
 */
public class LoginController implements FXMLController {

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
    private final AccountManager accManager = new AccountManagerImpl();
    private final EventHandler<KeyEvent> loginHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(final KeyEvent event) {
            if (event.getCode().compareTo(KeyCode.ENTER) == 0) {
                loginBtn.fire();
            } 
        }
    };
    private final EventHandler<KeyEvent> registerHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(final KeyEvent event) {
            if (event.getCode().compareTo(KeyCode.ENTER) == 0) {
                regBtn.fire();
            } 
        }
    };
    private final EventHandler<KeyEvent> exitHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(final KeyEvent event) {
            if (event.getCode().compareTo(KeyCode.ENTER) == 0) {
                exitBtn.fire();
            } 
        }
    };
    private final StageController stageController;

    /**
     * Build the LoginController.
     * @param stageController the StageController able to change scene
     */
    public LoginController(final StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(new LoginView(this).getScene());
    }
    /**
     * Register a new account.
     */
    @FXML
    public void register() {
        new RegisterController(this.stageController).start();
    }

    /**
     * Try to log into the game.
     */
    @FXML
    public void tryLogin() {
        final Account account = AccountImpl.createSimpleAccount(usrField.getText(), pswField.getText());
        if (accManager.isPresent(account)) {
            if (accManager.checkPassword(account)) {
                try {
                    startMenu(FileUtils.getAccountFromUsername(account.getUsername()));
                } catch (IOException e) {
                    System.out.println(StringUtils.ERROR_MESSAGE);
                    System.exit(0);
                }
            } else {
                AlertUtils.createLoginPasswordError();
            }
        } else {
            AlertUtils.createLoginUsernameError();
        }
    }

    /**
     * Close the application.
     */
    @FXML
    public void exit() {
        Platform.exit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        setHandlers();
    }

    private void startMenu(final Account account) {
        try {
            final Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.close();
            new MenuController(account).start();
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    private void setHandlers() {
        this.loginBtn.setOnKeyPressed(loginHandler);
        this.usrField.setOnKeyPressed(loginHandler);
        this.pswField.setOnKeyPressed(loginHandler);
        this.regBtn.setOnKeyPressed(registerHandler);
        this.exitBtn.setOnKeyPressed(exitHandler);
    }
}
