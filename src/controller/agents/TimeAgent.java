package controller.agents;

import controller.game.GameController;
import javafx.application.Platform;
import model.powerup.PowerUp;
import model.powerup.TemporaryPowerUp;
import utilities.ErrorLog;

/**
 * This class implements the agent that is active for a time.
 */
public class TimeAgent extends Thread {

    private static final int TEXT_DURATION = 2;
    private static final long SLEEPING_TIME = 15; 
    private final GameController gameController;
    private final TemporaryPowerUp temporaryPowerUp;
    private final PowerUp powerUp;
    private long pauseStartingTime;
    private long currentTime;
    private long endTime;

    /**
     * Build the TimeAgent.
     * @param temporaryPowerUp active power up
     * @param gameController the controller of the game
     */
    public TimeAgent(final TemporaryPowerUp temporaryPowerUp, final GameController gameController) {
        this.gameController = gameController;
        this.currentTime = System.currentTimeMillis();
        this.temporaryPowerUp = temporaryPowerUp;
        this.powerUp = null;
        this.endTime = this.currentTime + (this.temporaryPowerUp.getDuration() * 1000);
    }

    /**
     * Build the TimeAgent.
     * @param powerUp active power up
     * @param gameController the controller of the game
     */
    public TimeAgent(final PowerUp powerUp, final GameController gameController) {
        this.gameController = gameController;
        this.currentTime = System.currentTimeMillis();
        this.powerUp = powerUp;
        this.temporaryPowerUp = null;
        this.endTime = this.currentTime + (TEXT_DURATION * 1000);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while (this.currentTime <= this.endTime && !this.gameController.isEnded()) {
            try {
                if (this.gameController.isInPause()) {
                    this.pauseStartingTime = System.currentTimeMillis();
                    while (this.gameController.isInPause() && !this.gameController.isEnded()) {
                        Thread.sleep(SLEEPING_TIME);
                    }
                    this.currentTime = System.currentTimeMillis();
                    this.endTime = this.currentTime - this.pauseStartingTime;
                }
                this.currentTime = System.currentTimeMillis();
                Platform.runLater(() -> this.showPowerUp());
                Thread.sleep(SLEEPING_TIME);
            } catch (InterruptedException e) {
                ErrorLog.getLog().printError();
                System.exit(0);
            }
        }
        this.checkStop();
        this.gameController.getPowerController().setAvailable(true);
        Platform.runLater(() -> this.hidePowerUp());
    }

    private void showPowerUp() {
        if (this.powerUp != null) {
            this.gameController.getOverlayController().getPowerUpLabel().setText(this.powerUp.toString() + " unlocked!");
        } else {
            this.gameController.getOverlayController().getTempPowerUpLbl().setText(this.temporaryPowerUp.toString() + " unlocked for " + (this.endTime - this.currentTime) / 1000 + " seconds!");
        }
    }

    private void hidePowerUp() {
        if (this.powerUp != null) {
            this.gameController.getOverlayController().getPowerUpLabel().setText("");
        } else {
            this.gameController.getOverlayController().getTempPowerUpLbl().setText("");
        }
    }

    private void checkStop() {
        if (this.temporaryPowerUp != null) {
            this.temporaryPowerUp.stop();
        }
    }
}
