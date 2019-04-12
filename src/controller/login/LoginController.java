package controller.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.FXMLController;
import controller.StageController;
import controller.menu.MenuController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import view.login.LoginView;

/**
 * This class controls the login before starting the game.
 *
 */
public class LoginController implements FXMLController {

    private final AccountManager accManager;
    private final EventHandler<KeyEvent> loginHandler;
    private final EventHandler<KeyEvent> registerHandler;
    private final EventHandler<KeyEvent> exitHandler;
    private final StageController stageController;
    @FXML
    private GridPane grid;
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
     * Build the LoginController.
     * @param stageController the StageController able to change scene
     */
    public LoginController(final StageController stageController) {
        this.stageController = stageController;
        this.accManager = initAccountManager();
        this.loginHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode().compareTo(KeyCode.ENTER) == 0) {
                    loginBtn.fire();
                }
            }
        };
        this.registerHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode().compareTo(KeyCode.ENTER) == 0) {
                    regBtn.fire();
                }
            }
        };
        this.exitHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode().compareTo(KeyCode.ENTER) == 0) {
                    exitBtn.fire();
                }
            }
        };
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
        final Account account = new AccountImpl.Builder(this.usrField.getText(), this.pswField.getText()).build();
        if (this.accManager.isPresent(account)) {
            if (this.accManager.checkPassword(account)) {
                try {
                    startMenu(FileUtils.getAccountFromUsername(account.getUsername()));
                } catch (IOException e) {
                    ErrorLog.getLog().printError(e);
                    System.exit(0);
                }
            } else {
                this.grid.setEffect(GameUtils.getBlurEffect());
                AlertUtils.createLoginPasswordError();
                this.grid.setEffect(GameUtils.getTransparentEffect());
            }
        } else {
            this.grid.setEffect(GameUtils.getBlurEffect());
            AlertUtils.createLoginUsernameError();
            this.grid.setEffect(GameUtils.getTransparentEffect());
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

    private AccountManager initAccountManager() {
        try {
            return new AccountManagerImpl(FileUtils.getAccounts());
       } catch (IOException e) {
           ErrorLog.getLog().printError(e);
           System.exit(0);
       }
        return null;
    }

    private void startMenu(final Account account) {
        new MenuController(account, this.stageController).start();
    }

    private void setHandlers() {
        this.loginBtn.setOnKeyPressed(this.loginHandler);
        this.usrField.setOnKeyPressed(this.loginHandler);
        this.pswField.setOnKeyPressed(this.loginHandler);
        this.regBtn.setOnKeyPressed(this.registerHandler);
        this.exitBtn.setOnKeyPressed(this.exitHandler);
    }
}
