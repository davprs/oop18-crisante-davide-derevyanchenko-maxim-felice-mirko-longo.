package view;

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

    private static final String ERROR = "Error";
    private static final String BUNDLE_ALERT = "menu.DialogBundle";
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_ALERT);
    private static final String DIALOG_CONTEXT_KEY = "dialogContentText";
    private static final String DIALOG_HEADER_KEY = "dialogHeaderText";
    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getLoginUsernameError() {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("This username does NOT exist!");
        alert.setContentText("Please, digit again your username.");
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getLoginPasswordError() {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Your password is NOT correct!");
        alert.setContentText("Please, digit again your password.");
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getRegisterAccountError() {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("This account is already signed!");
        alert.setContentText("Please, change your username to sign.");
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getRegisterUsernameError() {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Your username is empty!");
        alert.setContentText("Please, digit your username to sign.");
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getRegisterPasswordError() {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Your passwords do NOT match!");
        alert.setContentText("Please, digit again your password to match.");
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getRegisterAccountDialog() {
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Congratulations!");
        alert.setContentText("Account registered.");
        return alert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getExitConfirmationDialog() {
        final Alert alert = new Alert(AlertType.CONFIRMATION, BUNDLE.getString(DIALOG_CONTEXT_KEY), ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(BUNDLE.getString(DIALOG_HEADER_KEY));
        final DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/css/alertStyle.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        return alert;
    }

}
