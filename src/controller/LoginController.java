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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This class controls the login before starting the game.
 *
 */
public class LoginController extends Application implements Controller {

    private static final String TITLE = "ACCEDERE";
    private boolean isAlreadyRegistering;
    @FXML
    private Label login;
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private TextField usrField;
    @FXML
    private PasswordField pswField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button regBtn;
    @FXML
    private Button confBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private ChoiceBox<String> lngBox;

    /**
     * Set the desired language.
     */
    @FXML
    public void setLanguage() {
       changeLanguage(lngBox.getValue());
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
                   final String pathname = lang.equals(Language.ITA) ? StringUtils.LOGIN_ITA : StringUtils.LOGIN_ENG;
                   final Iterator<String> iterator = Files.readAllLines(Paths.get(pathname)).iterator();
                   login.setText(iterator.next());
                   username.setText(iterator.next());
                   password.setText(iterator.next());
                   regBtn.setText(iterator.next());
                   loginBtn.setText(iterator.next());
                   confBtn.setText(iterator.next());
                   exitBtn.setText(iterator.next());
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

    private void changeLanguage(final String lang) { 
       Platform.runLater(createLanguageChanger(Language.valueOf(lang)));
    }

    /**
     * Register a new account.
     */
    @FXML
    public void register() {
        try {
            if (!isAlreadyRegistering) {
                new RegisterController().start(new Stage());
            }
            isAlreadyRegistering = true;
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    /**
     * Try to log into the game.
     */
    @FXML
    public void tryLogin() {
        try {
            final Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.close();
            new MenuController().start(new Stage());
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    /**
     * Close the application.
     */
    @FXML
    public void exit() {
        Platform.exit();
    }

    /**
     * Start method to load the view.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final GridPane root = FXMLLoader.load(ClassLoader.getSystemResource(StringUtils.LOGIN_VIEW));
        final Scene scene = new Scene(root, SizeUtils.LOGIN_PREF_WIDTH, SizeUtils.LOGIN_PREF_HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.setMinHeight(SizeUtils.LOGIN_MIN_HEIGHT);
        stage.setMinWidth(SizeUtils.LOGIN_MIN_WIDTH);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * @param args ignored.
     */
    public static void main(final String[] args) {
       launch();
    }

}
