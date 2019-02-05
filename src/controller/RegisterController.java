package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import controller.utilities.SizeUtils;
import controller.utilities.StringUtils;
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

    private static final String TITLE = "REGISTRAZIONE";
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
    private PasswordField pswField;
    @FXML
    private TextField pswTextField;
    @FXML
    private PasswordField confPswField;
    @FXML
    private TextField confPswTextField;
    @FXML
    private CheckBox pswCheckBox;
    @FXML
    private CheckBox confPswCheckBox;
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
     * Set the desired language.
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
        togglePasswordVisibility(pswCheckBox, pswField, pswTextField);
    }

    /**
     * 
     */
    @FXML
    public void checkConfPswField() {
       togglePasswordVisibility(confPswCheckBox, confPswField, confPswTextField);
    }

    private void togglePasswordVisibility(final CheckBox cb, final PasswordField psw, final TextField text) {
        if (cb.isSelected()) {
            text.setText(psw.getText());
            text.setVisible(true);
            psw.setVisible(false);
        } else {
            psw.setText(text.getText());
            text.setVisible(false);
            psw.setVisible(true);
        }
    }

    /**
     * Start method to load the view.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final GridPane root = FXMLLoader.load(ClassLoader.getSystemResource(StringUtils.REGISTER_VIEW));
        final Scene scene = new Scene(root, SizeUtils.REGISTER_PREF_WIDTH, SizeUtils.REGISTER_PREF_HEIGHT);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.setMinHeight(SizeUtils.REGISTER_MIN_HEIGHT);
        stage.setMinWidth(SizeUtils.REGISTER_MIN_WIDTH);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Runnable createLanguageChanger(final Language lang) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    final String pathname = lang.equals(Language.ITA) ? StringUtils.REGISTER_ITA : StringUtils.REGISTER_ENG;
                    final Iterator<String> iterator = Files.readAllLines(Paths.get(pathname)).iterator();
                    regLabel.setText(iterator.next());
                    usrLabel.setText(iterator.next());
                    nickLabel.setText(iterator.next());
                    pswLabel.setText(iterator.next());
                    confPswLabel.setText(iterator.next());
                    confBtn.setText(iterator.next());
                    regBtn.setText(iterator.next());
                    closeBtn.setText(iterator.next());
                    lngBox.getTooltip().setText(iterator.next());
                    if (iterator.hasNext()) {
                        throw new IllegalStateException();
                    }
                } catch (IOException | IllegalStateException e) {
                    System.out.println(StringUtils.ERROR_MESSAGE);
                    Platform.exit();
                }
            }
        };
    }

}
