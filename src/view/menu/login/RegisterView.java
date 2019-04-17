package view.menu.login;

import java.io.IOException;

import controller.menu.login.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import utilities.SystemUtils;
import view.AbstractView;

/**
 * View of Register.
 *
 */
public class RegisterView extends AbstractView {

    private static final String REGISTER_VIEW = "registerView.fxml";
    private static final double WIDTH_RATIO = 3.49;
    private static final double HEIGHT_RATIO = 3.08571428571;
    private static final double PREF_WIDTH = SystemUtils.getScreenResolution().getWidth() / WIDTH_RATIO;
    private static final double PREF_HEIGHT = SystemUtils.getScreenResolution().getHeight() / HEIGHT_RATIO;
    private final FXMLLoader loader;

    /**
     * Build the View.
     * @param controller the view controller
     */
    public RegisterView(final RegisterController controller) {
        this.loader = new FXMLLoader(ClassLoader.getSystemResource(REGISTER_VIEW));
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
