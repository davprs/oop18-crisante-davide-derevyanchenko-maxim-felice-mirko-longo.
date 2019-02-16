package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Represents the initial LanguageView.
 *
 */
public class LanguageView extends Application {

    private static final String TITLE = "Hello!";
    private static final String LANGUAGE_VIEW = "languageView.fxml";
    private static final double PREF_WIDTH = 390;
    private static final double PREF_HEIGHT = 200;

    /**
     * Start method to load the view.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final GridPane root = FXMLLoader.load(ClassLoader.getSystemResource(LANGUAGE_VIEW));
        final Scene scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
    /**
     * @param args ignored.
     */
    public static void main(final String[] args) {
       launch();
    }
}
