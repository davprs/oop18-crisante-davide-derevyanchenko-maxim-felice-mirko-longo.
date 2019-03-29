package model.powerup;
 /**
 * this class implements the agent that is active for a time.
 */
public class TimeAgent extends Thread {
    private long waitingTime;
    private TemporaryPowerUp powerUp;
    /**
     * 
     * @param powerUp active power up
     * @param waitingTime the time duration on the power up
     */
    public TimeAgent(final TemporaryPowerUp powerUp, final long waitingTime) {
        this.waitingTime = waitingTime;
        this.powerUp = powerUp;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            this.wait(this.waitingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.powerUp.stop();
    }
}
