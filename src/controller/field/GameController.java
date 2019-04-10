package controller.field;

import controller.StageController;
import controller.threading.SpawnAgent;
import javafx.geometry.Dimension2D;
import model.account.Account;
import view.field.FieldView;
import view.field.GameView;
import view.field.OverlayView;

/**
 * Class that represents the controller of the game.
 *
 */
public class GameController {

    private final FieldController fieldController;
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
        final GameView overlay = new GameView(stageController);
        final FieldView fieldView = new FieldView(stageController);
        final OverlayView ov = new OverlayView(stageController);
        stageController.setScene(overlay.getScene());
        stageController.setFullScreen(account.getSettings().isFullScreenOn());
        overlay.getRoot().getChildren().add(fieldView.getSubScene());
        overlay.getRoot().getChildren().add(ov.getSubScene());
        this.fieldController = new FieldController(this, fieldView);
        new SpawnAgent(this, 1, fieldView, this.fieldController, new Dimension2D(stageController.getScene().getWidth(), stageController.getScene().getHeight())).start();
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
}
