package view.menu.login;

import java.io.IOException;

import controller.menu.login.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import utilities.SystemUtils;
import view.AbstractView;

/**
 * View of Login.
 *
 */
public class LoginView extends AbstractView {

    private static final String LOGIN_VIEW = "loginView.fxml";
    private static final double WIDTH_RATIO = 4.8;
    private static final double HEIGHT_RATIO = 3.1;
    private static final double PREF_WIDTH = SystemUtils.getScreenResolution().getWidth() / WIDTH_RATIO;
    private static final double PREF_HEIGHT = SystemUtils.getScreenResolution().getHeight() / HEIGHT_RATIO;
    private final FXMLLoader loader;

    /**
     * Build the LoginView.
     * @param controller the controller of the view 
     */
    public LoginView(final LoginController controller) {
        this.loader = new FXMLLoader(ClassLoader.getSystemResource(LOGIN_VIEW));
        this.loader.setController(controller);
        super.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parent getRoot() throws IOException {
        return this.loader.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double getWidth() {
        return PREF_WIDTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double getHeight() {
        return PREF_HEIGHT;
    }
}
