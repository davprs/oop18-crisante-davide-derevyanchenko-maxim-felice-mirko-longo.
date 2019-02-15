package controller.utilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import model.account.Account;
import model.account.AccountImpl;

/**
 * This class is created only to give File utilities.
 *
 */
public final class FileUtils {

    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String ACCOUNT_PATH = "res" + SEPARATOR + "accounts" + SEPARATOR;
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
                        return new AccountImpl(Files.readAllLines(p).get(0), Files.readAllLines(p).get(1), Files.readAllLines(p).get(2));
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
