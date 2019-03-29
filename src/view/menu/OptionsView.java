package view.menu;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import controller.OptionsController;
import javafx.application.Application;
import javafx.stage.Stage;
import utilities.BundleUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

/**
 * View of Options.
 *
 */
public class OptionsView extends Application {

    private static final String OPTIONS_BUNDLE = "menu.OptionsBundle";
    private static final String OPTIONS_VIEW = "optionsSubmenu.fxml";
    private static final double PREF_WIDTH = 600;
    private static final double PREF_HEIGHT = 500;
    private static final double MIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
    private static final double MIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4;
    private final String language;
    private final OptionsController optionsController;

    /**
     * 
     * @param language .
     * @param optionsController .
     */
    public OptionsView(final String language, final OptionsController optionsController) {
        this.language = language;
        this.optionsController = optionsController;
    }

    /**
     * Start method to load the view.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        BundleUtils.setLocale(this.language);
        final ResourceBundle bundle = ResourceBundle.getBundle(OPTIONS_BUNDLE);
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(OPTIONS_VIEW), bundle);
        loader.setController(this.optionsController);
        final GridPane root = loader.load();
        final Scene scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        stage.setScene(scene);
        stage.show();
    }
}
