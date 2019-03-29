package view.login;

import java.awt.Toolkit;
import java.io.IOException;

import controller.login.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

/**
 * View of Login.
 *
 */
public class LoginView {

    private static final String LOGIN_VIEW = "loginView.fxml";
    private static final double WIDTH_RELATIONSHIP = 4.8;
    private static final double HEIGHT_RELATIONSHIP = 3.375;
    private static final double PREF_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / WIDTH_RELATIONSHIP;
    private static final double PREF_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / HEIGHT_RELATIONSHIP;
    private Scene scene;

    /**
     * Build the LoginView.
     * @param controller the controller of the view 
     */
    public LoginView(final LoginController controller) {
        try {
            final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(LOGIN_VIEW));
            loader.setController(controller);
            final GridPane root = loader.load();
            this.scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the Scene.
     * @return the Scene.
     */
    public Scene getScene() {
       return this.scene;
    }
}
