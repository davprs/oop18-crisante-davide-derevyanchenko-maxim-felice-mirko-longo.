package utilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.geometry.Dimension2D;
import model.account.Account;
import model.account.AccountImpl;
import model.account.SettingsImpl;

/**
 * This class is created only to give File utilities.
 *
 */
public final class FileUtils {

    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String HOME_DIR = System.getProperty("user.home");
    private static final String ACCOUNT_PATH = HOME_DIR + SEPARATOR + "accounts" + SEPARATOR;
    private static final String TXT_EXTENSION = ".txt";

    private FileUtils() { }

    /**
     * Print an Account on a simple text File. 
     * 
     * @param account the account to print
     * @throws IOException if an I/O error is thrown when accessing the file.
     */
    public static void printAccount(final Account account) throws IOException {
        final File file = new File(ACCOUNT_PATH + account.getUsername() + TXT_EXTENSION);
        file.createNewFile();
        try (PrintStream ps = new PrintStream(file)) {
            ps.println(account.getUsername());
            ps.println(account.getPassword());
            ps.println(account.getNickname());
            ps.println(account.getBestScore());
            ps.println(account.getSettings().getResolution().getWidth());
            ps.println(account.getSettings().getResolution().getHeight());
            ps.println(account.getSettings().getLanguage());
            ps.println(account.getSettings().getImageName());
            ps.println(account.getSettings().isSoundOn());
        }
    }

    /**
     * Read the accounts already registered and create a Set of them.
     * 
     * @return the accounts already registered
     * @throws IOException if an I/O error is thrown when accessing the file.
     */
    public static Set<Account> getAccounts() throws IOException {
        final Set<Account> set = new HashSet<>();
        final File dir = new File(ACCOUNT_PATH);
        dir.mkdirs();
        final File[] array = dir.listFiles();
        if (array != null) {
            final List<File> files = new ArrayList<>(Arrays.asList(array));
            files.stream()
                 .map(f -> {
                     try {
                         final Iterator<String> iterator = Files.readAllLines(Paths.get(f.getPath())).iterator();
                         return new AccountImpl.Builder(iterator.next(), iterator.next())
                                               .withNickname(iterator.next())
                                               .bestScore(Integer.parseInt(iterator.next()))
                                               .addMySettings(new SettingsImpl(
                                                              new Dimension2D(Double.parseDouble(iterator.next()), Double.parseDouble(iterator.next())),
                                                              iterator.next(),
                                                              iterator.next(),
                                                              Boolean.parseBoolean(iterator.next())))
                                               .build();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                     return null;
                 })
                 .forEach(set::add);
        }
        return set;
    }

    /**
     * Get the complete Account from username.
     * @param username the account username to get
     * @return a complete Account
     * @throws IOException if an I/O error is thrown when accessing the file.
     */
    public static Account getAccountFromUsername(final String username) throws IOException {
        return getAccounts()
                .stream()
                .filter(a -> a.getUsername().equals(username))
                .findFirst()
                .get();
    }

    /**
     * Read the password of the account linked to the username. 
     * 
     * @param username the account username to read
     * @return the Account password.
     * @throws IOException if an I/O error is thrown when accessing the file.
     */
    public static String getPassword(final String username) throws IOException {
        return getAccountFromUsername(username)
                             .getPassword();
    }

}
