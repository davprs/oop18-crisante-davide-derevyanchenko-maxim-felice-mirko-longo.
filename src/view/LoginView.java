package view;

import java.awt.Toolkit;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utilities.BundleUtils;

/**
 * Represents the LoginView.
 *
 */
public class LoginView extends Application {

    private static final String LOGGING_IN_KEY = "LOGGING_IN";
    private static final String BUNDLE = "login.LoginBundle";
    private static final String LOGIN_VIEW = "loginView.fxml";
    private static final double WIDTH_RELATIONSHIP = 4.8;
    private static final double HEIGHT_RELATIONSHIP = 3.375;
    private static final double PREF_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / WIDTH_RELATIONSHIP;
    private static final double PREF_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / HEIGHT_RELATIONSHIP;
    private static final double MIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 6;
    private static final double MIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 5.4;
    private final String language;
    /**
     * Construct the View, according to the language.
     * @param language the desired language
     */
    public LoginView(final String language) {
        this.language = language;
    }
    /**
     * Start method to load the view.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        BundleUtils.setLocale(language);
        final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE);
        final GridPane root = FXMLLoader.load(ClassLoader.getSystemResource(LOGIN_VIEW), bundle);
        final Scene scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
        stage.setTitle(bundle.getString(LOGGING_IN_KEY));
        stage.setScene(scene);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        stage.centerOnScreen();
        stage.show();
    }

}
