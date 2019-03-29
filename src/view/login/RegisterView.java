package view.login;

import java.awt.Toolkit;
import java.io.IOException;

import controller.login.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

/**
 * View of Register.
 *
 */
public class RegisterView {

    private static final String REGISTER_VIEW = "registerView.fxml";
    private static final double PREF_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3.49;
    private static final double PREF_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3.08571428571;
    private Scene scene;

    /**
     * Build the View.
     * @param controller the view controller
     */
    public RegisterView(final RegisterController controller) {
        try {
            final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(REGISTER_VIEW));
            loader.setController(controller);
            final GridPane root = loader.load();
            this.scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get this Scene.
     * @return the Scene
     */
     public Scene getScene() {
        return this.scene;
    }

}
