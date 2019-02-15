package controller.utilities;

import java.util.Locale;

import javafx.application.Platform;

/**
 * This class is created only to give Bundle utilities.
 *
 */
public final class BundleUtils {

    private BundleUtils() { }

    /**
     * 
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
                System.out.println(StringUtils.ERROR_MESSAGE);
                Platform.exit();
        }
    }
}
