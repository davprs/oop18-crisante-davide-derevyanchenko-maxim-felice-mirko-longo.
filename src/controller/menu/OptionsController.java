package controller.menu;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import controller.FXMLController;
import controller.StageController;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import model.account.Account;
import utilities.AlertUtils;
import utilities.FileUtils;
import view.menu.OptionsView;
/**
 * 
 * This class controls the Options view.
 *
 */
public class OptionsController implements FXMLController {

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
    private final Account account;
    private ResourceBundle bundle;
   // private final ObservableList<String> resolutionlist = FXCollections.observableArrayList(FIRST_CHOICE_CB1, SECOND_CHOICE_CB1, THIRD_CHOICE_CB1, FOURTH_CHOICE_CB1);
   // private final ObservableList<String> languagelist = FXCollections.observableArrayList(FIRST_CHOICE_CB2, SECOND_CHOICE_CB2, THIRD_CHOICE_CB2);

    private final StageController stageController;
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

    /**
     * Build the OptionsController.
     * @param account the game account
     * @param stageController the stage controller 
     */
    public OptionsController(final Account account, final StageController stageController) {
        this.account = account;
        this.stageController = stageController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.bundle = resources;
        setLanguage();
        this.language.setValue(this.account.getSettings().getLanguage());
       // this.language.setItems(languagelist);
        //this.resolution.setItems(resolutionlist);
    }

    /**
     * Method to go back to the menu.
     */
    public void goBack() {
        final  Optional<ButtonType> confirmSettings = AlertUtils.createConfirmOptionsDialog().showAndWait();
        if (confirmSettings.get() == ButtonType.YES) {
           final String[] values = resolution.getValue().split("x");
           final Dimension2D selectedResolution = new Dimension2D(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
            account.getSettings().setResolution(selectedResolution);
            account.getSettings().setLanguage(language.getValue());
           // account.getSettings().setImageName();
           if (yes.isSelected()) {
              account.getSettings().setSound(true);
           } else if (no.isSelected()) {
               account.getSettings().setSound(false); 
           }
            try {
                FileUtils.printAccount(account);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new MenuController(this.account, this.stageController).start();
        }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(new OptionsView(this.account, this).getScene());
        this.language.getItems().addAll(FIRST_CHOICE_CB2, SECOND_CHOICE_CB2);
        this.resolution.getItems().addAll(FIRST_CHOICE_CB1, SECOND_CHOICE_CB1, THIRD_CHOICE_CB1, FOURTH_CHOICE_CB1);
        this.resolution.setValue((int) this.account.getSettings().getResolution().getWidth() + "x" + (int) this.account.getSettings().getResolution().getHeight());

        if (this.account.getSettings().isSoundOn()) {
            this.yes.setSelected(true);
        } else {
            this.no.setSelected(false);
        }
    }

    private void setLanguage() {
        this.back.setText(bundle.getString(BACK_KEY));
        this.resolutionLb.setText(bundle.getString(RESOLUTION_KEY));
        this.languageLb.setText(bundle.getString(LANGUAGE_KEY));
        this.soundLb.setText(bundle.getString(SOUND_KEY));
        this.yes.setText(bundle.getString(YES_KEY));
        this.no.setText(bundle.getString(NO_KEY));
        this.changeShipLb.setText(bundle.getString(CHANGE_SHIP_KEY));
    }
}
