package view;

import controller.FieldController;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FieldView extends Application {

    private Canvas canvas = new Canvas( 1920, 1080 );
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
     
        root.getChildren().add(canvas);

        stage.show();
        new FieldController(this);
    }
    
    public void drawEntity(Image image, Rectangle2D boundary) {
       gc.drawImage(image, boundary.getMinX(), boundary.getMinY());
    }
    
    public void drawBackground(Image image) {
        gc.drawImage(image, 0, 0);
    }
    
    public Canvas getCanvas() {
        return this.canvas;
    }
    
    public Stage getStage() {
        return this.stage;
    }
    
    public static void main(String[] args) {
        launch();
    }
}
