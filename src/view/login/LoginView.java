package view.login;

import java.awt.Toolkit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * View of Login.
 *
 */
public class LoginView extends Application {

    private static final String TITLE = "LOGGING IN";
    private static final String LOGIN_VIEW = "loginView.fxml";
    private static final double WIDTH_RELATIONSHIP = 4.8;
    private static final double HEIGHT_RELATIONSHIP = 3.375;
    private static final double PREF_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / WIDTH_RELATIONSHIP;
    private static final double PREF_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / HEIGHT_RELATIONSHIP;
    private static final double MIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 6;
    private static final double MIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 5.4;

    /**
     * Start method to load the view.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final GridPane root = FXMLLoader.load(ClassLoader.getSystemResource(LOGIN_VIEW));
        final Scene scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Launcher method.
     */
    public static void initialize() {
        Application.launch();
    }
}
