package view.menu;

import java.io.IOException;
import java.util.ResourceBundle;

import controller.menu.HighscoreController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.account.Account;
import utilities.BundleUtils;
import view.AbstractView;
/**
 * 
 * View of Highscore.
 *
 */
public class HighscoreView extends AbstractView {

    private static final String HIGHSCORE_BUNDLE = "menu.Highscorebundle";
    private static final String HIGHSCORE_VIEW = "highscoreSubmenu.fxml";
    private final double prefWidth;
    private final double prefHeight;
    private final FXMLLoader loader;

    /**
     * Build the HighscoreView.
     * @param account the account.
     * @param highscoreController .
     */
    public HighscoreView(final Account account, final HighscoreController highscoreController) {
        this.prefWidth = account.getSettings().getResolution().getWidth();
        this.prefHeight = account.getSettings().getResolution().getHeight();
        BundleUtils.setLocale(account.getSettings().getLanguage());
        this.loader = new FXMLLoader(ClassLoader.getSystemResource(HIGHSCORE_VIEW), ResourceBundle.getBundle(HIGHSCORE_BUNDLE));
        this.loader.setController(highscoreController);
        super.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parent getRoot() throws IOException {
        return this.loader.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double getWidth() {
        return this.prefWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double getHeight() {
        return this.prefHeight;
    }
}

