package controller.game;

import controller.StageController;
import controller.agents.GameAgent;
import controller.agents.PowerUpAgent;
import controller.agents.entities.BulletAgent;
import controller.game.field.FieldController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import model.account.Account;
import model.game.Score;
import model.game.ScoreImpl;
import utilities.GameUtils;
import view.game.FieldView;
import view.game.GameView;

/**
 * Class that represents the controller of the game.
 *
 */
public class GameController {

    private final FieldController fieldController;
    private final OverlayController overlayController;
    private final StageController stageController;
    private final PowerUpController powerController;
    private final GameView gameView;
    private final FieldView fieldView;
    private final Score score;
    private final Account account;
    private final int gameLevel;
    private boolean inPause;
    private boolean ended;
    private boolean frozen;

    /**
     * Constructor of the GameController.
     * 
     * @param account the account of the player
     * @param stageController the controller of the Stage
     * @param gameLevel the difficulty of the game
     */
    public GameController(final Account account, final StageController stageController, final int gameLevel) {
        this.gameLevel = gameLevel;
        this.ended = false;
        this.account = account;
        this.stageController = stageController;
        this.powerController = new PowerUpController(this);
        this.gameView = new GameView(stageController);
        this.score = new ScoreImpl();
        final GameController controller = this;
        final EventHandler<KeyEvent> exitHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode().compareTo(KeyCode.ESCAPE) == 0) {
                    startHandler();
                }
            }
        };
        final EventHandler<MouseEvent> shootHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                fieldController.getCharacter().shoot();
            }
        };
        final EventHandler<MouseEvent> exitSceneHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                startHandler();
            }
        };
        final EventHandler<WindowEvent> exitWindow = new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent event) {
                controller.ended = true;
                GameUtils.muteAllSounds();
                Platform.exit();
            } 
        };
        this.stageController.setWindowHandler(exitWindow);
        GameUtils.muteAllSounds();
        this.gameView.getScene().setOnKeyPressed(exitHandler);
        this.gameView.getScene().setOnMouseExited(exitSceneHandler);
        this.gameView.getScene().setOnMousePressed(shootHandler);
        this.fieldView = new FieldView(this.account.getSettings().getResolution(), this.gameLevel);
        stageController.setScene(this.gameView.getScene());
        this.gameView.getRoot().getChildren().add(this.fieldView.getSubScene());
        this.fieldController = new FieldController(this);
        this.overlayController = new OverlayController(account, stageController, this);
        this.overlayController.start();
        this.startAgent(new BulletAgent(this));
        this.startAgent(new PowerUpAgent(this));
        this.startAgent(new GameAgent(this, this.gameLevel));
        this.stageController.setFullScreen(true);
    }

    /**
     * Get the PowerUpController.
     * @return the PowerUpController
     */
    public PowerUpController getPowerController() {
        return this.powerController;
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
        Platform.runLater(() -> new GameOverController(this.account, this.stageController, this).start());
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
     * Set the frozen status.
     * @param value the value to set
     */
    public synchronized void setFrozen(final boolean value) {
        this.frozen = value;
    }

    /**
     * Check the enemies' frozen status, if there aren't enemies the value is set to false.
     * @return the enemies' frozen status
     */
    public synchronized boolean isFrozen() {
        return this.frozen;
    }

    /**
     * Get the game view.
     * @return the game view
     */
    public GameView getGameView() {
        return this.gameView;
    }

    /**
     * Get the fieldView.
     * @return the fieldView
     */
    public FieldView getFieldView() {
        return this.fieldView;
    }

    /**
     * Get the overlayController.
     * @return the overlayController
     */
    public OverlayController getOverlayController() {
        return this.overlayController;
    }

    /**
     * Method that returns the actual account that is in game.
     * 
     * @return the account that is in game
     */
    public Account getAccount() {
        return this.account;
    }

    /**
     * Method that gets the difficulty of the game.
     * 
     * @return the level of the game
     */
    public int getGameLevel() {
        return this.gameLevel;
    }

    private void startAgent(final Thread agent) {
        agent.start();
    }

    private void startHandler() {
        if (!this.inPause && !this.ended) {
            this.inPause = true;
            GameUtils.muteAllSounds();
            new PauseController(this.account, this.stageController, this).start();
        }
    }
}
