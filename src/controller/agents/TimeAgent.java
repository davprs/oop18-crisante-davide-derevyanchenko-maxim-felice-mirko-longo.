package controller.agents;

import controller.game.GameController;
import model.powerup.TemporaryPowerUp;
import utilities.ErrorLog;

/**
 * This class implements the agent that is active for a time.
 */
public class TimeAgent extends Thread {

    private static final long SLEEPING_TIME = 15; 
    private final GameController gameController;
    private long pauseStartingTime;
    private long currentTime;
    private long endTime;
    private final TemporaryPowerUp powerUp;

    /**
     * Build the TimeAgent.
     * @param powerUp active power up
     * @param gameController the controller of the game
     */
    public TimeAgent(final TemporaryPowerUp powerUp, final GameController gameController) {
        this.gameController = gameController;
        this.currentTime = System.currentTimeMillis();
        this.powerUp = powerUp;
        this.endTime = currentTime + (this.powerUp.getDuration() * 1000);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while (this.currentTime <= this.endTime) {
            if (this.gameController.isInPause()) {
                this.pauseStartingTime = System.currentTimeMillis();
                while (this.gameController.isInPause()) {
                    try {
                        Thread.sleep(SLEEPING_TIME);
                    } catch (InterruptedException e) {
                        ErrorLog.getLog().printError();
                        System.exit(0);
                    }
                }
                this.currentTime = System.currentTimeMillis();
                this.endTime = this.currentTime - this.pauseStartingTime;
            }
            this.currentTime = System.currentTimeMillis();
        }
        this.powerUp.stop();
    }
}
