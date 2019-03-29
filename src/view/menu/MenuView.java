package view.menu;

import java.awt.Toolkit;
import java.util.ResourceBundle;

import controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utilities.BundleUtils;

/**
 * 
 *
 */
public class MenuView extends Application {

    private static final double PREF_WIDTH = 600;
    private static final double PREF_HEIGHT = 500;
    private static final double MIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
    private static final double MIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3;
    private static final String MENU_VIEW = "menuView.fxml";
    private static final String MENU_BUNDLE = "menu.MenuBundle";
    private final String language;
    private final MenuController menuController;

    /**
     * 
     * @param language the desired language
     * @param menuController 
     */
    public MenuView(final String language, final MenuController menuController) {
        this.language = language;
        this.menuController = menuController;
    }

    /**
     * {@inheritDoc}
     */
   @Override
    public void start(final Stage stage) throws Exception {
        BundleUtils.setLocale(this.language);
        final ResourceBundle bundle = ResourceBundle.getBundle(MENU_BUNDLE);
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(MENU_VIEW), bundle);
        loader.setController(this.menuController);
        final GridPane root = loader.load();
        final Scene scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        stage.setScene(scene);
        stage.show();
    }
}
