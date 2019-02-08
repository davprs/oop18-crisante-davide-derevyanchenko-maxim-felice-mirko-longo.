package controller.utilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

import controller.FileType;
import controller.Language;
import model.account.Account;
import model.account.AccountImpl;

/**
 * This class is created only to give File utilities.
 *
 */
public final class FileUtils {

    private FileUtils() { }

    /**
     * Generate a String Iterator from a specific File.
     * 
     * @param lang the file language
     * @param fileType the fileType according to the Enum
     * @return an Iterator.
     * @throws IOException if an I/O error is thrown when accessing the file.
     */
    public static Iterator<String> iteratorFromFile(final Language lang, final FileType fileType) throws IOException {
        String pathname;
        switch (fileType) {
            case LOGIN:
                pathname = lang.equals(Language.ITA) ? StringUtils.LOGIN_ITA : StringUtils.LOGIN_ENG;
                break;
            case REGISTER:
                pathname = lang.equals(Language.ITA) ? StringUtils.REGISTER_ITA : StringUtils.REGISTER_ENG;
                break;
            default: 
                throw new IllegalArgumentException();
        }
        return Files.readAllLines(Paths.get(pathname)).iterator();
    }

    /**
     * Print an Account on a simple text File. 
     * 
     * @param account the account to print
     * @throws IOException if an I/O error is thrown when accessing the file.
     */
    public static void printAccount(final Account account) throws IOException {
        final File file = new File(StringUtils.ACCOUNT_PATH + account.getUsername() + StringUtils.TXT_EXTENSION);
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
        try (Stream<Path> paths = Files.walk(Paths.get(StringUtils.ACCOUNT_PATH))) {
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
