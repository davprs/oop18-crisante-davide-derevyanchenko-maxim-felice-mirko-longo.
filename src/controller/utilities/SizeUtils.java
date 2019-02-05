package controller.utilities;

import java.awt.Toolkit;

/**
 * This class is created only to give Size utilities.
 *
 */
public final class SizeUtils {

    private static final double LOGIN_WIDTH_RELATIONSHIP = 4.8;
    private static final double LOGIN_HEIGHT_RELATIONSHIP = 3.375;
    /**
     * Preferred width of Login View.
     */
    public static final double LOGIN_PREF_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / LOGIN_WIDTH_RELATIONSHIP;
    /**
     * Preferred height of Login View.
     */
    public static final double LOGIN_PREF_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / LOGIN_HEIGHT_RELATIONSHIP;
    /**
     * Minimum width of Login View.
     */
    public static final double LOGIN_MIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 6;
    /**
     * Minimum height of Login View.
     */
    public static final double LOGIN_MIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 5.4;
    /**
     * Preferred width of Register View.
     */
    public static final double REGISTER_PREF_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3.49;
    /**
     * Preferred height of Register View.
     */
    public static final double REGISTER_PREF_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3.08571428571;
    /**
     * Minimum width of Register View.
     */
    public static final double REGISTER_MIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3.5;
    /**
     * Minimum height of Register View.
     */
    public static final double REGISTER_MIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4.6;

    private SizeUtils() { }
}
