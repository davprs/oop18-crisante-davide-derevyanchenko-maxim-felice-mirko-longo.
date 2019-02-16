package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
//import view.GameView;

/**
 * class MenuController that controls the menu.
 */
public class MenuController implements Initializable {

    private ResourceBundle bundle;
    @FXML
    private Button play;
    @FXML
    private Button highscore;

   // @FXML
    //private Button Options;

    /**
     * 
     */
    @FXML
    public void play() {
        final Stage stage = (Stage) play.getScene().getWindow();
        stage.close();
        try {
            //new GameView(bundle.getLocale().getLanguage()).start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.bundle = resources;
        setLanguage();
    }

    private void setLanguage() {
        play.setText(bundle.getString("play"));
        highscore.setText(bundle.getString("highscore"));
     //   Options.setText(bundle.getString("options"));
    }

}
