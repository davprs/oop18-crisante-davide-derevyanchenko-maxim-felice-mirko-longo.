package controller;

import java.io.IOException;
import java.util.Iterator;

import controller.utilities.FileUtils;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.account.Account;
import model.account.AccountImpl;
import model.account.AccountManager;
import model.account.AccountManagerImpl;

/**
 * This class controls the signing in of new accounts.
 *
 */
public class RegisterController extends Application implements Controller {

    private static final String TITLE = "REGISTRAZIONE";
    private final AccountManager manager = new AccountManagerImpl();
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
    private TextField nickField;
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
     * Register the account.
     */
    @FXML
    public void register() {
        if (!checkPassword()) {
            System.out.println("Errore");
            return;
        }
        final Account account = new AccountImpl(usrField.getText(), nickField.getText(), getPassword());
        if (manager.isPresent(account)) {
            System.out.println("Account già presente");
        } else {
            manager.register(account);
            System.out.println("Account registrato");
            close();
        }
    }

    /**
     * Set the desired language.
     */
    @FXML
    public void setLanguage() {
        Platform.runLater(createLanguageChanger(Language.valueOf(lngBox.getValue())));
    }

    /**
     * Change the visibility of the first PasswordField.
     */
    @FXML
    public void changePswField() {
        togglePasswordVisibility(pswCheckBox, pswField, pswTextField);
    }

    /**
     * Change the visibility of the second PasswordField.
     */
    @FXML
    public void changeConfPswField() {
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
        stage.initModality(Modality.APPLICATION_MODAL);
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
                    final Iterator<String> iterator = FileUtils.iteratorFromFile(lang, FileType.REGISTER);
                    regLabel.setText(iterator.next());
                    usrLabel.setText(iterator.next());
                    nickLabel.setText(iterator.next());
                    pswLabel.setText(iterator.next());
                    confPswLabel.setText(iterator.next());
                    confBtn.setText(iterator.next());
                    regBtn.setText(iterator.next());
                    closeBtn.setText(iterator.next());
                    lngBox.getTooltip().setText(iterator.next());
                    pswCheckBox.setText(iterator.next());
                    confPswCheckBox.setText(iterator.next());
                    final Stage stage = (Stage) closeBtn.getScene().getWindow();
                    stage.setTitle(iterator.next());
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

    private boolean checkPassword() {
        if (pswField.getText().equals(confPswField.getText())) {
            return !pswField.getText().equals("");
        }
        if (pswTextField.getText().equals(confPswTextField.getText())) {
            return !pswTextField.getText().equals("");
        }
        return false;
    }

    private String getPassword() {
        return pswCheckBox.isSelected() ? pswTextField.getText() : pswField.getText();
    }
}
