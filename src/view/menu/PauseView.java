package view.menu;

import java.io.IOException;
import java.util.ResourceBundle;

import controller.menu.PauseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.account.Account;
import utilities.BundleUtils;
import view.AbstractView;

/**
 * 
 * View of the Pause window.
 *
 */
public class PauseView extends AbstractView {

    private static final String PAUSE_VIEW = "pauseView.fxml";
    private static final String PAUSE_BUNDLE = "menu.PauseBundle";
    private final double prefWidth;
    private final double prefHeight;
    private final FXMLLoader loader;

    /**
     * 
     * @param account the game account
     * @param pauseController the controller class
     */
    public PauseView(final Account account, final PauseController pauseController) {
        this.prefWidth = account.getSettings().getResolution().getWidth();
        this.prefHeight = account.getSettings().getResolution().getHeight();
        BundleUtils.setLocale(account.getSettings().getLanguage());
        this.loader = new FXMLLoader(ClassLoader.getSystemResource(PAUSE_VIEW), ResourceBundle.getBundle(PAUSE_BUNDLE));
        this.loader.setController(pauseController);
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
        return this.prefWidth;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected double getHeight() {
        return this.prefHeight;
    }

}
