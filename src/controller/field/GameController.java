package controller.field;

import controller.StageController;
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

    /**
     * Constructor of the GameController.
     * 
     * @param account the account of the player
     * @param stageController the controller of the Stage
     */
    public GameController(final Account account, final StageController stageController) {
        final GameView overlay = new GameView(stageController);
        final FieldView fieldView = new FieldView(stageController);
        final OverlayView ov = new OverlayView(stageController);
        stageController.setScene(overlay.getScene());
        stageController.setFullScreen(account.getSettings().isFullScreenOn());
        overlay.getRoot().getChildren().add(fieldView.getSubScene());
        overlay.getRoot().getChildren().add(ov.getSubScene());
        this.fieldController = new FieldController(this, fieldView);
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
}
