package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This class controls the signing in of new accounts.
 *
 */
public class RegisterController extends Application implements Controller {

    @FXML
    private Label regLabel;
    @FXML
    private Label usrLabel;
    @FXML
    private Label pswLabel;
    @FXML
    private Label confPswLabel;
    @FXML
    private Label nickLabel;
    @FXML
    private Button closeBtn;
    @FXML
    private Button regBtn;
    @FXML
    private Button confBtn;
    @FXML
    private TextField usrField;
    @FXML
    private PasswordField  pswField;
    @FXML
    private CheckBox pswCheckBox;
    @FXML
    private ChoiceBox<String> lngBox;

    /**
     * Close this window.
     */
    @FXML
    public void close() {
        final Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * 
     */
    @FXML
    public void register() {

    }

    /**
     * 
     */
    @FXML
    public void setLanguage() {
        Platform.runLater(createLanguageChanger(Language.valueOf(lngBox.getValue())));
    }

    /**
     * 
     */
    @FXML
    public void checkPswField() {
        if (pswCheckBox.isSelected()) {
            pswField.setPromptText(pswField.getText());
            pswField.setDisable(true);
        } else {
            pswField.setText(pswField.getPromptText());
            pswField.setPromptText("");
            pswField.setDisable(false);
        }
    }

    /**
     * 
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final GridPane root = FXMLLoader.load(ClassLoader.getSystemResource("registerView.fxml"));
        final Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 
     */
    @Override
    public Runnable createLanguageChanger(final Language lang) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    final String path = "res" + System.getProperty("file.separator");
                    final String pathname = lang.equals(Language.ITA) ? path + "register_ita.txt" : path + "register_eng.txt";
                    final Iterator<String> iterator = Files.readAllLines(Paths.get(pathname)).iterator();
                    regLabel.setText(iterator.next());
                    usrLabel.setText(iterator.next());
                    nickLabel.setText(iterator.next());
                    pswLabel.setText(iterator.next());
                    confPswLabel.setText(iterator.next());
                    confBtn.setText(iterator.next());
                    regBtn.setText(iterator.next());
                    closeBtn.setText(iterator.next());
                    if (iterator.hasNext()) {
                        throw new IllegalStateException();
                    }
                } catch (IOException | IllegalStateException e) {
                    System.out.println("Sorry, something went wrong.");
                    Platform.exit();
                }
            }
        };
    }

}
