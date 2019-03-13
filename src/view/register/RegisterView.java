package view.register;

import java.awt.Toolkit;

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

    private static final String TITLE = "SIGNING IN";
    private static final String REGISTER_VIEW = "registerView.fxml";
    private static final double PREF_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3.49;
    private static final double PREF_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3.08571428571;
    private static final double MIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3.5;
    private static final double MIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4.6;

    /**
     * Start method to load the view.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final GridPane root = FXMLLoader.load(ClassLoader.getSystemResource(REGISTER_VIEW));
        final Scene scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        stage.centerOnScreen();
        stage.show();
    }

}
