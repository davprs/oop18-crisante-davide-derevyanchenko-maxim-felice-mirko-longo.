package view.menu;

import java.io.IOException;
import java.util.ResourceBundle;
import controller.menu.ChangeCredentialsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.account.Account;
import utilities.SystemUtils;
import view.AbstractView;

/**
 * 
 * View of change Credentials.
 *
 */
public class ChangeCredentialsView extends AbstractView {
    private static final String CHANGE_CREDENTIALS_BUNDLE = "menu.ChangeCredentialsBundle";
    private static final String CHANGE_CREDENTIALS_VIEW = "changeCredentialsView.fxml";
    private final double prefWidth;
    private final double prefHeight;
    private final FXMLLoader loader;

    /**
     * Build ChangeCredentialsView.
     * @param account the game account
     * @param changeCredentialsController the controller of this view
     */
    public ChangeCredentialsView(final Account account, final ChangeCredentialsController changeCredentialsController) {
        this.prefWidth = account.getSettings().getResolution().getWidth();
        this.prefHeight = account.getSettings().getResolution().getHeight();
        SystemUtils.setLocale(account.getSettings().getLanguage());
        this.loader = new FXMLLoader(ClassLoader.getSystemResource(CHANGE_CREDENTIALS_VIEW), ResourceBundle.getBundle(CHANGE_CREDENTIALS_BUNDLE));
        this.loader.setController(changeCredentialsController);
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
