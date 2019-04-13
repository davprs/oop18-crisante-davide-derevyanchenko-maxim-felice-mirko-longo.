package utilities;

import javafx.scene.control.Alert;
import view.alert.AlertFactory;
import view.alert.AlertFactoryImpl;

/**
 * Static Factory for different alerts.
 *
 */
public final class AlertUtils {

    private static final AlertFactory FACTORY = new AlertFactoryImpl();

    private AlertUtils() { }

    /**
     * Create a dialog caused by the login username error.
     */
    public static void createLoginUsernameError() {
        FACTORY.getLoginUsernameError().showAndWait();
    }

    /**
     * Create a dialog caused by the login password error.
     */
    public static void createLoginPasswordError() {
        FACTORY.getLoginPasswordError().showAndWait();
    }

    /**
     * Create a dialog caused by the register account error.
     */
    public static void createRegisterAccountError() {
        FACTORY.getRegisterAccountError().showAndWait();
    }

    /**
     * Create a dialog caused by the register username error.
     */
    public static void createRegisterUsernameError() {
        FACTORY.getRegisterUsernameError().showAndWait();
    }

    /**
     * Create a dialog caused by the register password error.
     */
    public static void createRegisterPasswordError() {
        FACTORY.getRegisterPasswordError().showAndWait();
    }

    /**
     * Create a dialog caused by the register password error.
     */
    public static void createRegisterAccountDialog() {
        FACTORY.getRegisterAccountDialog().showAndWait();
    }

    /**
     * Create a dialog to confirm exit game.
     * @return alert
     */
    public static Alert createExitConfirmationDialog() {
        return FACTORY.getExitConfirmationDialog();
    }
    /**
     * create a dialog to confirm settings.
     * @return alert
     */
    public static Alert createConfirmOptionsDialog() {
        return FACTORY.getConfirmOptionsDialog();
    }
}
