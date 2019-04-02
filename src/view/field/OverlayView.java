package view.field;

import controller.StageController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 *
 */
public class OverlayView extends Application {

    private static final String OVERLAY_VIEW = "overlayView.fxml";
    private final StageController stageController;

    /**
     * Build the MenuView.
     * @param stageController the controller of the main stage
     */
    public OverlayView(final StageController stageController) {
        this.stageController = stageController;
//        this.prefWidth = account.getSettings().getResolution().getWidth();
//        this.prefHeight = account.getSettings().getResolution().getHeight();
//        BundleUtils.setLocale(account.getSettings().getLanguage());
//        this.loader.setController(menuController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader  = new FXMLLoader(ClassLoader.getSystemResource(OVERLAY_VIEW));
        final Scene scene = new Scene(loader.load(), Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stageController.setOwner(stage);
        final AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                stage.setX(stageController.getX());
                stage.setY(stageController.getY());
                stage.setWidth(stageController.getWidth());
                stage.setHeight(stageController.getHeight());
            }
        };
        stage.show();
        at.start();
    }
}
