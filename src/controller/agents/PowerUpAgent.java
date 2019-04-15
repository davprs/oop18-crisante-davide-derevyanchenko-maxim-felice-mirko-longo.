package controller.agents;

import controller.game.GameController;
import controller.game.PowerUpController;
import utilities.ErrorLog;

/**
 * Class that represents the Thread to active the power ups.
 *
 */
public class PowerUpAgent extends Thread {

    private static final long WAITING_TIME = 1000;
    private final PowerUpController powerController;
    private final GameController gameController;

    /**
     * Build the PowerUpAgent.
     * @param gameController the controller of the game
     */
    public PowerUpAgent(final GameController gameController) {
        this.powerController = new PowerUpController(gameController);
        this.gameController = gameController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while (!this.gameController.isEnded()) {
            try {
                this.powerController.active();
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException e) {
                System.out.println("non e' crashato qui!!!");
                ErrorLog.getLog().printError();
                System.exit(0);
            }
        }
    }
}
