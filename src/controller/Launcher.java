package controller;

import java.awt.Toolkit;

import controller.login.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launcher class.
 *
 */
public class Launcher extends Application {

    private static final double MIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 6;
    private static final double MIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 5.4;
    private static final String TITLE = "Space Shooting";

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final StageController stageController = new StageController(stage);
        new LoginController(stageController).start();
        stage.setTitle(TITLE);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
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
