package utilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.account.Account;
import model.account.AccountImpl;

/**
 * This class is created only to give File utilities.
 *
 */
public final class FileUtils {

    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String RES_PATH = "res" + SEPARATOR;
    private static final String ACCOUNT_PATH = RES_PATH + "accounts" + SEPARATOR;
    private static final String TOP_SCORE_PATH = RES_PATH + "topScore" + SEPARATOR + "topScore.txt";
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
            ps.println(account.getNickname());
            ps.println(account.getPassword());
        }
    }

    /**
     * Read the accounts already registered and create a Set of them.
     * 
     * @return the accounts already registered
     * @throws IOException if an I/O error is thrown when accessing the file.
     */
    public static Set<Account> readAccounts() throws IOException {
        final Set<Account> set = new HashSet<>();
        try (Stream<Path> paths = Files.walk(Paths.get(ACCOUNT_PATH))) {
            paths
                .filter(Files::isRegularFile)
                .map(p -> {
                    try {
                        final List<String> file = Files.readAllLines(p);
                        if (file.size() == 3) {
                            return AccountImpl.createAccountWithNickname(file.get(0), file.get(2), file.get(1));
                        } else {
                            return AccountImpl.createCompleteAccount(file.get(0), file.get(2), file.get(1), file.stream()
                                                                                                                .skip(3)
                                                                                                                .map(value -> Integer.parseInt(value))
                                                                                                                .collect(Collectors.toList()));
                        }
                    } catch (IOException e) {
                        System.out.println(StringUtils.ERROR_MESSAGE);
                    }
                    return null;
                })
                .forEach(set::add);
        } 
        return set;
    }

    /**
     * Read the password of the account linked to the username. 
     * 
     * @param username the account username to read
     * @return the Account password.
     * @throws IOException if an I/O error is thrown when accessing the file.
     */
    public static String readPassword(final String username) throws IOException {
        return readAccounts().stream()
                             .filter(a -> a.getUsername().equals(username))
                             .findFirst()
                             .get()
                             .getPassword();
    }

}
