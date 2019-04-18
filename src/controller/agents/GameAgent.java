package controller.agents;

import controller.game.GameController;
import utilities.ErrorLog;
import utilities.GameUtils;

/**
 * Class that represents the game thread that constantly creates spawn agents of the appropriate level.
 */
public class GameAgent extends Thread {

    private final int gameLevel;
    private final GameController gameContoller;
    private int currentLevel;
    private SpawnAgent spawnAgent;

    /**
     * Constructor for the GameAgent.
     * 
     * @param gameController the controller of the game
     * @param gameLevel the difficulty of the game
     */
    public GameAgent(final GameController gameController, final int gameLevel) {
        this.gameContoller = gameController;
        this.gameLevel = gameLevel;
        this.currentLevel = 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        if (this.gameLevel <= 3) {
            if (this.gameContoller.getAccount().getSettings().isSoundOn()) {
                GameUtils.getLevelMusic().loop();
            }
            new SpawnAgent(this.gameContoller, this.gameLevel, this.gameContoller.getAccount().getSettings().getResolution()).start();
        } else {
            if (this.gameContoller.getAccount().getSettings().isSoundOn()) {
                GameUtils.getSurvivalMusic().loop();
            }
            this.spawnAgent = new SpawnAgent(this.gameContoller, this.currentLevel, this.gameContoller.getAccount().getSettings().getResolution());
            while (!this.gameContoller.isEnded()) {
                try {
                    if (!this.spawnAgent.isAlive()) {
                        this.spawnAgent = new SpawnAgent(this.gameContoller, this.currentLevel, this.gameContoller.getAccount().getSettings().getResolution());
                        this.spawnAgent.start();
                        this.currentLevel++;
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    ErrorLog.getLog().printError();
                    System.exit(0);
                }
            }
        }
    }
}
