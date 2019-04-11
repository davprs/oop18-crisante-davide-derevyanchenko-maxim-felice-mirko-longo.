package controller.field;

import controller.StageController;
import controller.menu.PauseController;
import controller.threading.SpawnAgent;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.account.Account;
import utilities.SoundUtils;
import view.field.FieldView;
import view.field.GameView;

/**
 * Class that represents the controller of the game.
 *
 */
public class GameController {

    private final FieldController fieldController;
    private final OverlayController overlayController;
    private final GameView gameView;
    private final FieldView fieldView;
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
        this.gameView = new GameView(stageController);
        final GameController controller = this;
        final EventHandler<KeyEvent> exitHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode().compareTo(KeyCode.ESCAPE) == 0) {
                    controller.setInPause(true);
                    SoundUtils.muteAllSounds();
                    new PauseController(account, stageController, controller).start();
                }
            }
        };
        if (account.getSettings().isSoundOn()) {
            SoundUtils.GAMEPLAY_MUSIC.loop();
        }
        this.gameView.getScene().setOnKeyPressed(exitHandler);
        this.fieldView = new FieldView(stageController);
        stageController.setScene(this.gameView.getScene());
        stageController.setFullScreen(account.getSettings().isFullScreenOn());
        this.gameView.getRoot().getChildren().add(this.fieldView.getSubScene());
        this.overlayController = new OverlayController(account, null, stageController, this);
        this.overlayController.start();
        this.fieldController = new FieldController(this, fieldView);
        new SpawnAgent(this, 1, fieldController, new Dimension2D(stageController.getScene().getWidth(), stageController.getScene().getHeight())).start();
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
