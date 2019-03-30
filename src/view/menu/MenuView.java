package view.menu;

import java.io.IOException;
import java.util.ResourceBundle;

import controller.menu.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.account.Account;
import utilities.BundleUtils;
import view.AbstractView;

/**
 *  View of the Menu.
 *
 */
public class MenuView extends AbstractView {

    private static final String MENU_VIEW = "menuView.fxml";
    private static final String MENU_BUNDLE = "menu.MenuBundle";
    private final double prefWidth;
    private final double prefHeight;
    private final FXMLLoader loader;

    /**
     * Build the MenuView.
     * @param account the game account
     * @param menuController the controller class
     */
    public MenuView(final Account account, final MenuController menuController) {
        this.prefWidth = account.getSettings().getResolution().getWidth();
        this.prefHeight = account.getSettings().getResolution().getHeight();
        BundleUtils.setLocale(account.getSettings().getLanguage());
        this.loader = new FXMLLoader(ClassLoader.getSystemResource(MENU_VIEW), ResourceBundle.getBundle(MENU_BUNDLE));
        this.loader.setController(menuController);
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
