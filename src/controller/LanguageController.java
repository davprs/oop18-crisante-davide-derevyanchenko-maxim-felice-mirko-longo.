package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utilities.StringUtils;
import view.LoginView;

/**
 * Controller class of LanguageView.
 *
 */
public class LanguageController {

    @FXML
    private Button italian;
    @FXML
    private Button english;

    /**
     * Set the language to Italian.
     */
    @FXML
    public void setItalian() {
        set(italian.getText());
    }

    /**
     * Set the language to English.
     */
    @FXML
    public void setEnglish() {
        set(english.getText());
    }

    private void set(final String language) {
        try {
            final Stage stage = (Stage) italian.getScene().getWindow();
            stage.close();
            new LoginView(language).start(new Stage());
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }
}
