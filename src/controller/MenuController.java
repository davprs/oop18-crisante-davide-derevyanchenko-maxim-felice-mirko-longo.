package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * class MenuController that controls the menu.
 */
public class MenuController extends Application {

    /**
     * 
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final VBox root = FXMLLoader.load(ClassLoader.getSystemResource("menuView.fxml"));
        final Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
