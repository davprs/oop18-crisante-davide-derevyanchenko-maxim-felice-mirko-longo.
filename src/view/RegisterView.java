package view;

import java.awt.Toolkit;
import java.util.ResourceBundle;

import controller.utilities.BundleUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * View of Register.
 *
 */
public class RegisterView extends Application {

    private static final String BUNDLE = "register.RegisterBundle";
    private static final String SIGNING_IN_KEY = "SIGNING_IN";
    private static final String REGISTER_VIEW = "RegisterView.fxml";
    private static final double PREF_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3.49;
    private static final double PREF_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3.08571428571;
    private static final double MIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3.5;
    private static final double MIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4.6;
    private final String language;

    /**
     * Construct the View, according to the language.
     * @param language the desired language
     */
    public RegisterView(final String language) {
        this.language = language;
    }
    /**
     * Start method to load the view.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        BundleUtils.setLocale(language);
        final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE);
        final GridPane root = FXMLLoader.load(ClassLoader.getSystemResource(REGISTER_VIEW), bundle);
        final Scene scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle(bundle.getString(SIGNING_IN_KEY));
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        stage.centerOnScreen();
        stage.show();
    }

}
