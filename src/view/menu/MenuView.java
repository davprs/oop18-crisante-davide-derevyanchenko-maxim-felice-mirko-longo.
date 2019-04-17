package view.menu;

import java.io.IOException;
import java.util.ResourceBundle;
import controller.menu.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import utilities.SystemUtils;
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
     * @param menuController the controller class
     */
    public MenuView(final MenuController menuController) {
        this.prefWidth = menuController.getAccount().getSettings().getResolution().getWidth();
        this.prefHeight = menuController.getAccount().getSettings().getResolution().getHeight();
        SystemUtils.setLocale(menuController.getAccount().getSettings().getLanguage());
        this.loader = new FXMLLoader(ClassLoader.getSystemResource(MENU_VIEW), ResourceBundle.getBundle(MENU_BUNDLE));
        this.loader.setController(menuController);
        super.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GridPane getRoot() throws IOException {
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
