package view;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.Scene;
import utilities.ErrorLog;

/**
 * Abstract class of FXMLView.
 *
 */
public abstract class AbstractView {

    private Scene scene;

    /**
     * This method HAS to be called in the constructor of subclasses.
     * Initialize the view.
     */
    public final void init() {
        try {
            this.scene = new Scene(this.getRoot(), this.getWidth(), this.getHeight());
        } catch (IOException e) {
            ErrorLog.getLog().printError();
            System.exit(0);
        }
    }

    /**
     * Get the Scene.
     * @return the Scene.
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Get the Root.
     * @return the Parent
     * @throws IOException if I/O problem happens.
     */
    public abstract Parent getRoot() throws IOException;

    /**
     * Get the Width.
     * @return the width
     */
    protected abstract double getWidth();

    /**
     * Get the Height.
     * @return the height
     */
    protected abstract double getHeight();

}
