package view;

import javafx.scene.control.Alert;

/**
 * Interface that gives some Alerts.
 *
 */
public interface AlertFactory {

    /**
     * Get the Alert representing the login username error.
     * @return the Alert.
     */
    Alert getLoginUsernameError();

    /**
     * Get the Alert representing the login password error.
     * @return the Alert.
     */
    Alert getLoginPasswordError();

    /**
     * Get the Alert representing the register account error.
     * @return the Alert.
     */
    Alert getRegisterAccountError();

    /**
     * Get the Alert representing the register username error.
     * @return the Alert.
     */
    Alert getRegisterUsernameError();

    /**
     * Get the Alert representing the register password error.
     * @return the Alert.
     */
    Alert getRegisterPasswordError();

    /**
     * Get the Alert representing the register account dialog.
     * @return the Alert.
     */
    Alert getRegisterAccountDialog();

    /**
     * Get the Alert representing the exit dialog.
     * @return the Alert;
     */
    Alert getExitConfirmationDialog();
}
