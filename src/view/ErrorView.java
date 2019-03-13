package view;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Abstract view class of Errors.
 *
 */
public abstract class ErrorView extends Application {

    private static final String TITLE_KEY = "title";
    private static final String ERROR_VIEW = "errorView.fxml";
    private static final double PREF_WIDTH = 300;
    private static final double PREF_HEIGHT = 200;

    /**
     * Start method to load the view.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        setLanguage();
        final GridPane root = FXMLLoader.load(ClassLoader.getSystemResource(ERROR_VIEW), getBundle());
        final Scene scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(getBundle().getString(TITLE_KEY));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Set the language.
     */
    protected abstract void setLanguage();

    /**
     * Get the ResourceBundle.
     * @return the Bundle of this View.
     */
    protected abstract ResourceBundle getBundle();
}
