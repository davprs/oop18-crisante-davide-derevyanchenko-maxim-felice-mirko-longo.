package utilities;

/**
 * Class to Log an Error.
 *
 */
public final class ErrorLog {

    private static final String ERROR_MESSAGE = "Sorry. Something went wrong.";

    private static class LogHolder {
        private static final ErrorLog SINGLETON = new ErrorLog();
    }

    private ErrorLog() { }

    /**
     * Get the ErrorLog.
     * @return the Log
     */
    public static ErrorLog getLog() {
        return LogHolder.SINGLETON;
    }

    /**
     * Print an Error to STDOUT.
     */
    public void printError() {
        System.out.println(ERROR_MESSAGE);
    }
}
