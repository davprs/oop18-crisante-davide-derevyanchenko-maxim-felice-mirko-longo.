package controller.field;

import java.net.URL;
import java.util.ResourceBundle;

import controller.FXMLController;
import controller.StageController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Life;
import model.account.Account;
import utilities.ErrorLog;
import view.field.OverlayView;

/**
 * Controller class of Overlay.
 *
 */
public class OverlayController implements FXMLController {

    private static final String LIVES = "LIVES";
    private static final String HP = "HP";
    private static final String SCORE = "SCORE";
    private static final long WAITING_TIME = 20;
    private final Life life;
    private final OverlayView view;
    private final GameController gameController;
    @FXML
    private Label lives;
    @FXML
    private Label numberOfLives;
    @FXML
    private Label hp;
    @FXML
    private Label hpNumber;
    @FXML
    private Label score;
    @FXML
    private Label scoreNumber;

    /**
     * Build the OverlayController.
     * @param account the account of the player
     * @param life the life of the characterShip
     * @param stageController the controller of the stage
     * @param gameController the controller of the game
     */
    public OverlayController(final Account account, final Life life, final StageController stageController, final GameController gameController) {
        this.life = life;
        this.gameController = gameController;
        this.view = new OverlayView(account, stageController, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL url, final ResourceBundle bundle) {
        this.lives.setText(bundle.getString(LIVES));
        this.hp.setText(bundle.getString(HP));
        this.score.setText(bundle.getString(SCORE));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.gameController.getView().getRoot().getChildren().add(this.view.getSubScene());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!gameController.isInPause()) {
                    try {
                        Platform.runLater(() -> {
                            numberOfLives.setText(Integer.toString(life.getLives()));
                            hpNumber.setText(Integer.toString(life.getHealth()));
                        });
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
}
