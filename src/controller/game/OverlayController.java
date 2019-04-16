package controller.game;

import java.net.URL;
import java.util.ResourceBundle;

import controller.StageController;
import controller.menu.FXMLController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.account.Account;
import utilities.ErrorLog;
import view.game.OverlayView;

/**
 * Controller class of Overlay.
 *
 */
public class OverlayController implements FXMLController {

    private static final String LIVES = "LIVES";
    private static final String HP = "HP";
    private static final String SCORE = "SCORE";
    private static final long WAITING_TIME = 20;
    private final OverlayView view;
    private final GameController gameController;
    private ResourceBundle bundle;
    @FXML
    private Label lives;
    @FXML
    private Label hp;
    @FXML
    private Label score;
    @FXML
    private Label powerUpLbl;
    @FXML
    private Label tempPowerUpLbl;

    /**
     * Build the OverlayController.
     * @param account the account of the player
     * @param stageController the controller of the stage
     * @param gameController the controller of the game
     */
    public OverlayController(final Account account, final StageController stageController, final GameController gameController) {
        this.gameController = gameController;
        this.view = new OverlayView(account, stageController, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL url, final ResourceBundle bundle) {
        this.bundle = bundle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.gameController.getGameView().getRoot().getChildren().add(this.view.getSubScene());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!gameController.isEnded()) {
                    try {
                        if (!gameController.isInPause()) {
                            Platform.runLater(() -> {
                                lives.setText(bundle.getString(LIVES) + Integer.toString(gameController.getFieldController().getCharacter().getLife().getLives()));
                                hp.setText(bundle.getString(HP) + Integer.toString(gameController.getFieldController().getCharacter().getLife().getCurrentHealth()));
                                score.setText(bundle.getString(SCORE) + Integer.toString(gameController.getScore().getScorePoints()));
                            });
                        }
                        Thread.sleep(WAITING_TIME);
                    } catch (InterruptedException e) {
                        ErrorLog.getLog().printError();
                        System.exit(0);
                    }
                }
            }
        }).start();
    }

    /**
     * Get the OverlayView.
     * @return the view
     */
    public OverlayView getView() {
        return this.view;
    }

    /**
     * Get the PowerUpLabel.
     * @return the powerUpLabel
     */
    public Label getPowerUpLabel() {
        return this.powerUpLbl;
    }

    /**
     * Get the TemporaryPowerUpLabel.
     * @return the tempPowerUpLabel
     */
    public Label getTempPowerUpLbl() {
        return this.tempPowerUpLbl;
    }
}
