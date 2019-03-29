package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Implementation of AlertFactory.
 *
 */
public class AlertFactoryImpl implements AlertFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Alert getLoginUsernameError() {
        final Alert alert = new Alert(AlertType.ERROR);
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
        alert.setHeaderText("cai");
        alert.setContentText("aFafsd");
        return alert;
    }

}
