package controller.menu;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import controller.FXMLController;
import controller.StageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.account.Account;
import utilities.AlertUtils;
import utilities.ErrorLog;
import utilities.FileUtils;
import utilities.GameUtils;
import view.menu.OptionsView;
/**
 * 
 * This class controls the Options view.
 *
 */
public class OptionsController implements FXMLController {

    private static final int SLEEP_TIME = 150;
    private static final String BACK_KEY = "back";
    private static final String FIRST_CHOICE_CB1 = "720x480";
    private static final String SECOND_CHOICE_CB1 = "1024x600";
    private static final String THIRD_CHOICE_CB1 = "1280x720";
    private static final String FOURTH_CHOICE_CB1 = "1920x1080";
    private static final String FIRST_CHOICE_CB2 = "it";
    private static final String SECOND_CHOICE_CB2 = "en";
    private static final String RESOLUTION_KEY = "resolution";
    private static final String LANGUAGE_KEY = "language";
    private static final String SOUND_KEY = "sound";
    private static final String YES_KEY = "yes";
    private static final String NO_KEY = "no";
    private static final String CHANGE_SHIP_KEY = "change_ship";
    private static final String SHIP_1 = "spaceship";
    private static final String SHIP_2 = "enemyShip1";
    private static final String SHIP_3 = "GreenEvil";
    private static final ObservableList<String> RESOLUTIONS_LIST = FXCollections.observableArrayList(FIRST_CHOICE_CB1, SECOND_CHOICE_CB1, THIRD_CHOICE_CB1, FOURTH_CHOICE_CB1);
    private static final ObservableList<String> LANGUAGE_LIST = FXCollections.observableArrayList(FIRST_CHOICE_CB2, SECOND_CHOICE_CB2);
    private static final ObservableList<String> SHIP_LIST = FXCollections.observableArrayList(SHIP_1, SHIP_2, SHIP_3);
    private static final String PNG = ".png";
    private final Account account;
    private final StageController stageController;
    private ResourceBundle bundle;
    private boolean isInOptions;
    @FXML
    private GridPane grid;
    @FXML
    private Button back;
    @FXML
    private ChoiceBox<String> language; 
    @FXML
    private ChoiceBox<String> resolution;
    @FXML
    private Label resolutionLb;
    @FXML
    private Label languageLb;
    @FXML
    private Label soundLb;
    @FXML
    private Label changeShipLb;
    @FXML
    private RadioButton yes;
    @FXML
    private RadioButton no;
    @FXML
    private ChoiceBox<String> shipList;
    @FXML
    private ImageView image;

    /**
     * Build the OptionsController.
     * @param account the game account
     * @param stageController the stage controller 
     */
    public OptionsController(final Account account, final StageController stageController) {
        this.account = account;
        this.stageController = stageController;
        this.isInOptions = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.bundle = resources;
        setLanguage();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isInOptions) {
                    image.setImage(new Image(shipList.getValue() + PNG));
                }
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    ErrorLog.getLog().printError();
                    System.exit(0);
                }
            };
        }).start();
        setComponents();
    }


    /**
     * Method to go back to the menu.
     */
    public void goBack() {
        this.grid.setEffect(GameUtils.getBlurEffect());
        final  Optional<ButtonType> confirmSettings = AlertUtils.createConfirmOptionsDialog().showAndWait();
        if (confirmSettings.get() == ButtonType.YES) {
            final String[] values = resolution.getValue().split("x");
            final Dimension2D selectedResolution = new Dimension2D(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
            this.account.getSettings().setResolution(selectedResolution);
            this.account.getSettings().setLanguage(language.getValue());
            this.account.getSettings().setImageName(this.shipList.getValue());
            if (yes.isSelected()) {
                account.getSettings().setSound(true);
            } else if (no.isSelected()) {
                account.getSettings().setSound(false); 
            }
            try {
                FileUtils.printAccount(account);
            } catch (IOException e) {
                ErrorLog.getLog().printError();
                System.exit(0);
            }
        }
        this.grid.setEffect(GameUtils.getTransparentEffect());
        this.isInOptions = false;
        new MenuController(this.account, this.stageController).start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(new OptionsView(this.account, this).getScene());
    }

    private void setLanguage() {
        this.back.setText(this.bundle.getString(BACK_KEY));
        this.resolutionLb.setText(this.bundle.getString(RESOLUTION_KEY));
        this.languageLb.setText(this.bundle.getString(LANGUAGE_KEY));
        this.soundLb.setText(this.bundle.getString(SOUND_KEY));
        this.yes.setText(this.bundle.getString(YES_KEY));
        this.no.setText(this.bundle.getString(NO_KEY));
        this.changeShipLb.setText(this.bundle.getString(CHANGE_SHIP_KEY));
    }

    private void setComponents() {
        this.language.setItems(LANGUAGE_LIST);
        this.resolution.setItems(RESOLUTIONS_LIST);
        this.shipList.setItems(SHIP_LIST);
        this.shipList.setValue(this.account.getSettings().getImageName());
        this.language.setValue(this.account.getSettings().getLanguage());
        this.resolution.setValue((int) this.account.getSettings().getResolution().getWidth() + "x" + (int) this.account.getSettings().getResolution().getHeight());
        if (this.account.getSettings().isSoundOn()) {
            this.yes.setSelected(true);
        } else {
            this.no.setSelected(false);
        }
    }
}
