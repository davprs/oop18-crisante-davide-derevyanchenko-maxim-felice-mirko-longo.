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
        ResourceBundle bundle = ResourceBundle.getBundle("menu.MenuBundle");
        GridPane root = FXMLLoader.load(ClassLoader.getSystemResource("menuView.fxml"), bundle);
        Scene scene = new Scene(root,600,500);
        stage.setScene(scene);
        stage.show();
    }

}
