package view.game;

import java.io.IOException;
import java.util.ResourceBundle;
import controller.game.LevelController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import utilities.SystemUtils;
import view.AbstractView;
/**
 * 
 * View of the Level View.
 *
 */
public class LevelView extends AbstractView {

    private static final String LEVEL_VIEW = "levelView.fxml";
    private static final String LEVEL_BUNDLE = "game.LevelBundle";
    private final double prefWidth;
    private final double prefHeight;
    private final FXMLLoader loader;

    /**
     * 
     * @param levelController of this controller
     */
    public LevelView(final LevelController levelController) {
        this.prefWidth = levelController.getAccount().getSettings().getResolution().getWidth();
        this.prefHeight = levelController.getAccount().getSettings().getResolution().getHeight();
        SystemUtils.setLocale(levelController.getAccount().getSettings().getLanguage());
        this.loader = new FXMLLoader(ClassLoader.getSystemResource(LEVEL_VIEW), ResourceBundle.getBundle(LEVEL_BUNDLE));
        this.loader.setController(levelController);
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
