package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import utilities.ErrorType;
import utilities.StringUtils;
import view.login.LoginPasswordErrorView;
import view.login.LoginUsernameErrorView;
import view.register.RegisterAccountErrorView;
import view.register.RegisterPasswordErrorView;
import view.register.RegisterUsernameErrorView;
import view.ErrorView;

/**
 * Controller class of AccountErrorView.
 *
 */
public class ErrorController implements Initializable {

    private static final String WARNING_KEY = "warning";
    private static final String ERROR_KEY = "error";
    private static final String HINT_KEY = "hint";
    private ResourceBundle bundle;
    @FXML
    private Label warningLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private Label hintLabel;
    @FXML
    private Button okBtn;
    private final EventHandler<KeyEvent> exitHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(final KeyEvent event) {
            if (event.getCode().compareTo(KeyCode.ENTER) == 0) {
                okBtn.fire();
            } 
        }
    };

    /**
     * Starts the view, according to the ErrorType.
     * @param error the ErrorType
     */
    public void start(final ErrorType error) {
        try {
            final ErrorView view;
            switch (error) {
                case lOGIN_USERNAME: 
                    view = new LoginUsernameErrorView();
                    break;
                case LOGIN_PASSWORD: 
                    view = new LoginPasswordErrorView();
                    break;
                case REGISTER_ACCOUNT:
                    view = new RegisterAccountErrorView();
                    break;
                case REGISTER_USERNAME:
                    view = new RegisterUsernameErrorView();
                    break;
                case REGISTER_PASSWORD:
                    view = new RegisterPasswordErrorView();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            view.start(new Stage());
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Close this window.
     */
    @FXML
    public void close() {
        final Stage stage = (Stage) this.warningLabel.getScene().getWindow();
        stage.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.bundle = resources;
        setComponents();
        this.okBtn.setOnKeyPressed(exitHandler);
    }

    private void setComponents() {
        this.warningLabel.setText(bundle.getString(WARNING_KEY));
        this.errorLabel.setText(bundle.getString(ERROR_KEY));
        this.hintLabel.setText(bundle.getString(HINT_KEY));
    }
}
