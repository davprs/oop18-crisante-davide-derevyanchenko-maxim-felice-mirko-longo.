package utilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private static final String RES_PATH = "res" + SEPARATOR;
    private static final String ACCOUNT_PATH = RES_PATH + "accounts" + SEPARATOR;
    private static final String TXT_EXTENSION = ".txt";
    private static final String TOP_SCORE_PATH = RES_PATH + "topScore" + SEPARATOR + "topScore.txt";

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
            ps.println(account.getSettings().isFullScreenOn());
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
        try (Stream<Path> paths = Files.walk(Paths.get(ACCOUNT_PATH))) {
            paths.filter(Files::isRegularFile)
                 .map(p -> {
                     try {
                         final Iterator<String> iterator = Files.readAllLines(p).iterator();
                         return new AccountImpl.Builder(iterator.next(), iterator.next())
                                              .withNickname(iterator.next())
                                              .bestScore(Integer.parseInt(iterator.next()))
                                              .addMySettings(new SettingsImpl(Boolean.parseBoolean(iterator.next()),
                                                      new Dimension2D(Double.parseDouble(iterator.next()), Double.parseDouble(iterator.next())),
                                                      iterator.next(),
                                                      iterator.next(),
                                                      Boolean.parseBoolean(iterator.next())))
                                              .build();
                    } catch (IOException e) {
                        ErrorLog.getLog().printError(e);
                        System.exit(0);
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

    /**
     * Get the TopScore values mapped to the username keys.
     * @return a Map
     * @throws IOException if an I/O error is thrown when accessing the file.
     */
    public static Map<String, Integer> getTopScores() throws IOException {
        final Map<String, Integer> map = new HashMap<>();
        Files.readAllLines(Paths.get(TOP_SCORE_PATH))
                    .stream()
                    .forEach(s -> map.put(s.split(" ")[0], Integer.parseInt(s.split(" ")[1])));
        return map;
    }

    /**
     * Print a value on the TopScore.
     * @param account the account
     * @param value the value to print
     * @throws IOException if an I/O error is thrown when accessing the file.
     */
    public static void printTopScore(final Account account, final int value) throws IOException {
        Map<String, Integer> scores = getTopScores();
        if (account.getBestScore() < value) {
            scores.put(account.getUsername(), value);
            scores = scores.entrySet()
                           .stream()
                           .sorted(Map.Entry.comparingByValue())
                           .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            try (PrintStream ps = new PrintStream(new File(TOP_SCORE_PATH))) {
                scores.forEach((u, i) -> ps.println(u + " " + i));
            }
        }
    }

}
