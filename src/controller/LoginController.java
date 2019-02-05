package controller;

import java.awt.Toolkit;
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

    private static final double WIDTH_RELATIONSHIP = 4.8;
    private static final double HEIGHT_RELATIONSHIP = 3.375;
    private static final int PREF_WIDTH =  Math.toIntExact(Math.round((Toolkit.getDefaultToolkit().getScreenSize().getWidth() / WIDTH_RELATIONSHIP)));
    private static final int PREF_HEIGHT = Math.toIntExact(Math.round((Toolkit.getDefaultToolkit().getScreenSize().getHeight() / HEIGHT_RELATIONSHIP)));
    private static final double MIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 6;
    private static final double MIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 5.4;
    private static final String TITLE = "ACCEDERE";
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
                   final String path = "res" + System.getProperty("file.separator");
                   final String pathname = lang.equals(Language.ITA) ? path + "login_ita.txt" : path + "login_eng.txt";
                   final Iterator<String> iterator = Files.readAllLines(Paths.get(pathname)).iterator();
                   login.setText(iterator.next());
                   username.setText(iterator.next());
                   password.setText(iterator.next());
                   regBtn.setText(iterator.next());
                   loginBtn.setText(iterator.next());
                   confBtn.setText(iterator.next());
                   exitBtn.setText(iterator.next());
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

    private void changeLanguage(final String lang) { 
       Platform.runLater(createLanguageChanger(Language.valueOf(lang)));
    }

    /**
     * Register a new account.
     */
    @FXML
    public void register() {
        try {
            new RegisterController().start(new Stage());
        } catch (Exception e) {
            System.out.println("Sorry, something went wrong.");
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
            System.out.println("Sorry, something went wrong.");
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
     * Start method needed to run the Application.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final GridPane root = FXMLLoader.load(ClassLoader.getSystemResource("loginView.fxml"));
        final Scene scene = new Scene(root, PREF_WIDTH, PREF_HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
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
