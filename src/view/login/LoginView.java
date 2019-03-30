package view.login;

import java.awt.Toolkit;
import java.io.IOException;

import controller.login.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import view.AbstractView;

/**
 * View of Login.
 *
 */
public class LoginView extends AbstractView {

    private static final String LOGIN_VIEW = "loginView.fxml";
    private static final double WIDTH_RELATIONSHIP = 4.8;
    private static final double HEIGHT_RELATIONSHIP = 3.375;
    private static final double PREF_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / WIDTH_RELATIONSHIP;
    private static final double PREF_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / HEIGHT_RELATIONSHIP;
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
