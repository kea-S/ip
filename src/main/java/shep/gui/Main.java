package shep.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shep.ui.Interaction;

/**
 * A GUI for Shep using FXML.
 */
public class Main extends Application {

    private Interaction shep = new Interaction();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setInteraction(shep);  // inject the Shep instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}