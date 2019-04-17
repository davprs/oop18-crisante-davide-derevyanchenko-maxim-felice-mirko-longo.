package view.alert;

import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;

/**
 * Implementation of AlertFactory.
 *
 */
public class AlertFactoryImpl implements AlertFactory {

    private static final String BUNDLE_ALERT = "menu.DialogBundle";
    private static final String ERROR_TITLE = "ERROR";
    private static final String INFORMATION_TITLE = "Information";
    private static final String LOGIN_USERNAME_HEADER_TEXT = "This username does NOT exist!";
    private static final String LOGIN_USERNAME_CONTENT_TEXT = "Please, digit again your username.";
    private static final String LOGIN_PASSWORD_HEADER_TEXT = "Your password is NOT correct!";
    private static final String LOGIN_PASSWORD_CONTENT_TEXT = "Please, digit again your password.";
    private static final String REGISTER_ACCOUNT_HEADER_TEXT = "This account is already signed!";
    private static final String REGISTER_ACCOUNT_CONTENT_TEXT = "Please, change your username to sign.";
    private static final String REGISTER_USERNAME_HEADER_TEXT = "Your username is empty!";
    private static final String REGISTER_USERNAME_CONTENT_TEXT = "Please, digit your username to sign.";
    private static final String REGISTER_PASSWORD_HEADER_TEXT = "Your passwords do NOT match!";
    private static final String REGISTER_PASSWORD_CONTENT_TEXT = "Please, digit again your password to match.";
    private static final String REGISTER_HEADER_TEXT = "Congratulations!";
    private static final String REGISTER_CONTENT_TEXT = "Account registered.";
    private static final String DIALOG_CONTEXT_KEY = "dialogContentText";
    private static final String DIALOG_HEADER_KEY = "dialogHeaderText";
    private static final String OPTIONS_DIALOG_CONTEXT_KEY = "optionsDialogContentText";
    private static final String OPTIONS_DIALOG_HEADER_KEY = "optionsDialogHeaderText";
    private static final String URL_CSS = "/css/alertStyle.css";
    private static final String CSS_ID_DIALOG = "myDialog";
    private ResourceBundle bundle;

    /**
     * Build the AlertFactory.
     */
    public AlertFactoryImpl() {
        this.bundle = ResourceBundle.getBundle(BUNDLE_ALERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getLoginUsernameError() {
        final Alert alert = new Alert(AlertType.ERROR, LOGIN_USERNAME_CONTENT_TEXT);
        alert.setTitle(ERROR_TITLE);
        alert.setHeaderText(LOGIN_USERNAME_HEADER_TEXT);
        this.addCSSToDialogPane(alert.getDialogPane());
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getLoginPasswordError() {
        final Alert alert = new Alert(AlertType.ERROR, LOGIN_PASSWORD_CONTENT_TEXT);
        alert.setTitle(ERROR_TITLE);
        alert.setHeaderText(LOGIN_PASSWORD_HEADER_TEXT);
        this.addCSSToDialogPane(alert.getDialogPane());
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getRegisterAccountError() {
        final Alert alert = new Alert(AlertType.ERROR, REGISTER_ACCOUNT_CONTENT_TEXT);
        alert.setTitle(ERROR_TITLE);
        alert.setHeaderText(REGISTER_ACCOUNT_HEADER_TEXT);
        this.addCSSToDialogPane(alert.getDialogPane());
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getRegisterUsernameError() {
        final Alert alert = new Alert(AlertType.ERROR, REGISTER_USERNAME_CONTENT_TEXT);
        alert.setTitle(ERROR_TITLE);
        alert.setHeaderText(REGISTER_USERNAME_HEADER_TEXT);
        this.addCSSToDialogPane(alert.getDialogPane());
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getRegisterPasswordError() {
        final Alert alert = new Alert(AlertType.ERROR, REGISTER_PASSWORD_CONTENT_TEXT);
        alert.setTitle(ERROR_TITLE);
        alert.setHeaderText(REGISTER_PASSWORD_HEADER_TEXT);
        this.addCSSToDialogPane(alert.getDialogPane());
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getRegisterAccountDialog() {
        final Alert alert = new Alert(AlertType.INFORMATION, REGISTER_CONTENT_TEXT);
        alert.setTitle(INFORMATION_TITLE);
        alert.setHeaderText(REGISTER_HEADER_TEXT);
        this.addCSSToDialogPane(alert.getDialogPane());
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getExitConfirmationDialog() {
        this.bundle = ResourceBundle.getBundle(BUNDLE_ALERT);
        final Alert alert = new Alert(AlertType.CONFIRMATION, this.bundle.getString(DIALOG_CONTEXT_KEY), ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(this.bundle.getString(DIALOG_HEADER_KEY));
        this.addCSSToDialogPane(alert.getDialogPane());
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getConfirmOptionsDialog() {
        this.bundle = ResourceBundle.getBundle(BUNDLE_ALERT);
        final Alert alert = new Alert(AlertType.CONFIRMATION, this.bundle.getString(OPTIONS_DIALOG_CONTEXT_KEY), ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(this.bundle.getString(OPTIONS_DIALOG_HEADER_KEY));
        this.addCSSToDialogPane(alert.getDialogPane());
        return alert;
    }

    private void addCSSToDialogPane(final DialogPane dialogPane) {
        dialogPane.getStylesheets().add(
                getClass().getResource(URL_CSS).toExternalForm());
        dialogPane.getStyleClass().add(CSS_ID_DIALOG);
    }
}
