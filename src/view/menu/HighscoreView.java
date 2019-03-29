package view.menu;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import controller.HighscoreController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utilities.BundleUtils;
/**
 * 
 * View of Highscore.
 *
 */
public class HighscoreView extends Application {

    private static final String HIGHSCORE_BUNDLE = "menu.Highscorebundle";
    private static final String HIGHSCORE_VIEW = "highscoreSubmenu.fxml";
    private static final double PREF_WIDTH = 600;
    private static final double PREF_HEIGHT = 500;
    private static final double MIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
    private static final double MIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4;
    private final String language;
    private final HighscoreController highscoreController;

    /**
     * 
     * @param language is a string.
     * @param highscoreController .
     */
    public HighscoreView(final String language, final HighscoreController highscoreController) {
        this.language = language;
        this.highscoreController = highscoreController;
    }
 
    /**
     * Start method to load the view.
     */
    @Override
    public void start(final Stage stage) throws Exception {
            BundleUtils.setLocale(this.language);
            final ResourceBundle bundle = ResourceBundle.getBundle(HIGHSCORE_BUNDLE);
            final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(HIGHSCORE_VIEW), bundle);
            loader.setController(this.highscoreController);
            final GridPane root = loader.load();
            final Scene scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setMinWidth(MIN_WIDTH);
            stage.setScene(scene);
            stage.show();
        }
    }

