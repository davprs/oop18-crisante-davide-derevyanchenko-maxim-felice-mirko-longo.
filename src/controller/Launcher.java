package controller;

import controller.menu.login.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import utilities.SystemUtils;

/**
 * Launcher class.
 *
 */
public class Launcher extends Application {

    private static final double WIDTH_RATIO = 6;
    private static final double HEIGHT_RATIO = 3.5;
    private static final double INITIAL_WIDTH = SystemUtils.getScreenResolution().getWidth() / WIDTH_RATIO;
    private static final double INITIAL_HEIGHT = SystemUtils.getScreenResolution().getHeight() / HEIGHT_RATIO;
    private static final String TITLE = "SPACE SHOOTING";

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final StageController stageController = new StageController(stage);
        new LoginController(stageController).start();
        stage.setTitle(TITLE);
        stage.setMinHeight(INITIAL_HEIGHT);
        stage.setMinWidth(INITIAL_WIDTH);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Main method.
     * @param args ignored.
     */
    public static void main(final String[] args) {
        Application.launch();
    }
}
