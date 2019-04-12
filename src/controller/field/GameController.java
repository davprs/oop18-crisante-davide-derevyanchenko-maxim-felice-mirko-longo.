package controller.field;

import controller.StageController;
import controller.menu.GameOverController;
import controller.menu.PauseController;
import controller.threading.BulletAgent;
import controller.threading.SpawnAgent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.account.Account;
import model.game.Score;
import model.game.ScoreImpl;
import utilities.GameUtils;
import view.field.FieldView;
import view.field.GameView;

/**
 * Class that represents the controller of the game.
 *
 */
public class GameController {

    private final FieldController fieldController;
    private final OverlayController overlayController;
    private final StageController stageController;
    private final GameView gameView;
    private final FieldView fieldView;
    private final Score score;
    private final Account account;
    private boolean inPause;
    private boolean ended;

    /**
     * Constructor of the GameController.
     * 
     * @param account the account of the player
     * @param stageController the controller of the Stage
     */
    public GameController(final Account account, final StageController stageController) {
        this.ended = false;
        this.account = account;
        this.stageController = stageController;
        this.gameView = new GameView(stageController);
        this.score = new ScoreImpl();
        final GameController controller = this;
        final EventHandler<KeyEvent> exitHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode().compareTo(KeyCode.ESCAPE) == 0 && !controller.inPause) {
                    controller.setInPause(true);
                    GameUtils.muteAllSounds();
                    new PauseController(account, stageController, controller).start();
                }
            }
        };

        final EventHandler<MouseEvent> shootHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                fieldController.getCharacter().shoot();
            }
        };
        if (account.getSettings().isSoundOn()) {
            GameUtils.GAMEPLAY_MUSIC.loop();
        }
        this.gameView.getScene().setOnKeyPressed(exitHandler);
        this.gameView.getScene().setOnMouseClicked(shootHandler);
        this.fieldView = new FieldView(stageController);
        stageController.setScene(this.gameView.getScene());
        stageController.setFullScreen(account.getSettings().isFullScreenOn());
        this.gameView.getRoot().getChildren().add(this.fieldView.getSubScene());
        this.fieldController = new FieldController(this);
        this.overlayController = new OverlayController(account, stageController, this);
        this.overlayController.start();
        new SpawnAgent(this, 1, fieldController, new Dimension2D(stageController.getScene().getWidth(), stageController.getScene().getHeight())).start();
        new BulletAgent(this, fieldController).start();
    }

    /**
     * Get the Score.
     * @return the score
     */
    public Score getScore() {
        return this.score;
    }

    /**
     * Gets the FieldController of this game.
     * 
     * @return the FieldController
     */
    public FieldController getFieldController() {
        return this.fieldController;
    }

    /**
     * Sets the value of the inPause state.
     * 
     * @param inPause the value that says if the game is paused or not
     */
    public synchronized void setInPause(final boolean inPause) {
        this.inPause = inPause;
    }

    /**
     * Method that says if the game is in pause or not.
     * 
     * @return true if the game is in pause, false otherwise
     */
    public synchronized boolean isInPause() {
        return this.inPause;
    }

    /**
     * Sets the value of the ended state.
     * 
     * @param ended the value that says if the game is ended or not
     */
    public synchronized void setEnded(final boolean ended) {
        this.ended = ended;
        if (!this.fieldController.getCharacter().getEntity().isAlive()) {
            Platform.runLater(() -> new GameOverController(account, stageController, this).start());
        }
    }

    /**
     * Method that says if the game is ended or not.
     * 
     * @return true if the game is ended, false otherwise
     */
    public synchronized boolean isEnded() {
        return this.ended;
    }

    /**
     * Get the game view.
     * @return the game view
     */
    public GameView getView() {
        return this.gameView;
    }

    /**
     * Get the fieldView.
     * @return the fieldView
     */
    public FieldView getFieldView() {
        return fieldView;
    }

    /**
     * Get the overlayController.
     * @return the overlayController
     */
    public OverlayController getOverlayController() {
        return this.overlayController;
    }
}
