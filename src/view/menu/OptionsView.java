package view.menu;

import java.io.IOException;
import java.util.ResourceBundle;

import controller.menu.OptionsController;
import model.account.Account;
import utilities.BundleUtils;
import view.AbstractView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * View of Options.
 *
 */
public class OptionsView extends AbstractView {

    private static final String OPTIONS_BUNDLE = "menu.OptionsBundle";
    private static final String OPTIONS_VIEW = "optionsSubmenu.fxml";
    private final double prefWidth;
    private final double prefHeight;
    private final FXMLLoader loader;

    /**
     * Build a OptionsView.
     * @param account the game account
     * @param optionsController the controller of this view
     */
    public OptionsView(final Account account, final OptionsController optionsController) {
        this.prefWidth = account.getSettings().getResolution().getWidth();
        this.prefHeight = account.getSettings().getResolution().getHeight();
        BundleUtils.setLocale(account.getSettings().getLanguage());
        this.loader = new FXMLLoader(ClassLoader.getSystemResource(OPTIONS_VIEW), ResourceBundle.getBundle(OPTIONS_BUNDLE));
        this.loader.setController(optionsController);
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
