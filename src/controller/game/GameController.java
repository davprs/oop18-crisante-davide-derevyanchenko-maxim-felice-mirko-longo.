package controller.game;

import controller.StageController;
import controller.agents.PowerUpAgent;
import controller.agents.SpawnAgent;
import controller.agents.entities.BulletAgent;
import controller.game.field.FieldController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
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
                System.exit(0);
            } 
        };
        this.stageController.setHandler(exitWindow);
        if (account.getSettings().isSoundOn()) {
            GameUtils.GAMEPLAY_MUSIC.loop();
        }
        this.gameView.getScene().setOnKeyPressed(exitHandler);
        this.gameView.getScene().setOnMouseExited(exitSceneHandler);
        this.gameView.getScene().setOnMousePressed(shootHandler);
        this.fieldView = new FieldView(stageController);
        stageController.setScene(this.gameView.getScene());
        stageController.setFullScreen(account.getSettings().isFullScreenOn());
        this.gameView.getRoot().getChildren().add(this.fieldView.getSubScene());
        this.fieldController = new FieldController(this);
        this.overlayController = new OverlayController(account, stageController, this);
        this.overlayController.start();
        this.startAgent(new SpawnAgent(this, 1, new Dimension2D(stageController.getScene().getWidth(), stageController.getScene().getHeight())));
        this.startAgent(new BulletAgent(this));
        this.startAgent(new PowerUpAgent(this));
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
     * Check the enemies' frozen status, if there aren't enemies the value is set to false.
     * @return the enemies' frozen status
     */
    public synchronized boolean checkEnemiesFrozen() {
        if (!this.fieldController.getEnemies().isEmpty()) {
            return this.fieldController.getEnemies().get(0).getEntity().isFrozen();
        } else {
            return false;
        }
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

    private void startAgent(final Thread agent) {
        agent.start();
    }

    private void startHandler() {
        if (!inPause && !ended) {
            inPause = true;
            GameUtils.muteAllSounds();
            new PauseController(account, stageController, this).start();
        }
    }
}
