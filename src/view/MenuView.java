package view;

import java.util.ResourceBundle;

import controller.utilities.BundleUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * 
 *
 */
public class MenuView extends Application {

    private static final double PREF_WIDTH = 600;
    private static final double PREF_HEIGHT = 500;
    private final String language;

    /**
     * 
     * @param language the desired language
     */
    public MenuView(final String language) {
        this.language = language;
    }

    /**
     * 
     */
    @Override
    public void start(final Stage stage) throws Exception {
        BundleUtils.setLocale(language);
        final ResourceBundle bundle = ResourceBundle.getBundle("menu.MenuBundle");
        final GridPane root = FXMLLoader.load(ClassLoader.getSystemResource("menuView.fxml"), bundle);
        final Scene scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

}
