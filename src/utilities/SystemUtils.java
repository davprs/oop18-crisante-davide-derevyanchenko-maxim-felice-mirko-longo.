package utilities;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Locale;

import javafx.application.Platform;

/**
 * Utility class for System properties.
 *
 */
public final class SystemUtils {

    private static final Dimension RESOLUTION = Toolkit.getDefaultToolkit().getScreenSize();
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String HOME_DIR = System.getProperty("user.home");
    private static final String EMPTY_STRING = "";

    private SystemUtils() { }

    /**
     * Get the Screen Resolution.
     * @return the screen resolution
     */
    public static Dimension getScreenResolution() {
        return RESOLUTION;
    }

    /**
     * Get the System separator.
     * @return the string separator
     */
    public static String getSystemSeparator() {
        return SEPARATOR;
    }

    /**
     * Get the System home directory.
     * @return the home directory string
     */
    public static String getHomeDir() {
        return HOME_DIR;
    }

    /**
     * Get an Empty String.
     * @return the empty String
     */
    public static String getEmptyString() {
        return EMPTY_STRING;
    }

    /**
     * Set the language.
     * @param language the desired language
     */
    public static void setLocale(final String language) {
        switch (language) {
            case "it":
            case "Italian":
                Locale.setDefault(Locale.ITALIAN);
                break;
            case "en":
            case "English":
                Locale.setDefault(Locale.ENGLISH);
                break;
            default:
                ErrorLog.getLog().printError();
                Platform.exit();
        }
    }
}
