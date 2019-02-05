package controller.utilities;

/**
 * This class is created only to give String utilities.
 *
 */
public final class StringUtils {

    private static final String RES_PATH = "res" + System.getProperty("file.separator");
    private static final String LOGIN_PATH = RES_PATH + "login" + System.getProperty("file.separator");
    private static final String REGISTER_PATH = RES_PATH + "register" + System.getProperty("file.separator");
    /**
     * Path of Italian Login file.
     */
    public static final String LOGIN_ITA = LOGIN_PATH + "loginIta.txt";
    /**
     * Path of English Login file.
     */
    public static final String LOGIN_ENG = LOGIN_PATH + "loginEng.txt";
    /**
     * Path of Italian Register file.
     */
    public static final String REGISTER_ITA = REGISTER_PATH + "registerIta.txt";
    /**
     * Path of English Register file.
     */
    public static final String REGISTER_ENG = REGISTER_PATH + "registerEng.txt";
    /**
     * Filename of Login View.
     */
    public static final String LOGIN_VIEW = "LoginView.fxml";
    /**
     * Filename of Register View.
     */
    public static final String REGISTER_VIEW = "RegisterView.fxml";
    /**
     * Simple Error message.
     */
    public static final String ERROR_MESSAGE = "Sorry. Something went wrong.";

    private StringUtils() { }

}
