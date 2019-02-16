package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller class of AccountErrorView.
 *
 */
public class ErrorController implements Initializable {

    private static final String WARNING_KEY = "warning";
    private static final String ERROR_KEY = "error";
    private static final String HINT_KEY = "hint";
    private ResourceBundle bundle;
    @FXML
    private Label warningLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private Label hintLabel;

    /**
     * Close this window.
     */
    @FXML
    public void close() {
        final Stage stage = (Stage) warningLabel.getScene().getWindow();
        stage.close();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        bundle = resources;
        setComponents();
    }

    private void setComponents() {
        warningLabel.setText(bundle.getString(WARNING_KEY));
        errorLabel.setText(bundle.getString(ERROR_KEY));
        hintLabel.setText(bundle.getString(HINT_KEY));
    }
}
