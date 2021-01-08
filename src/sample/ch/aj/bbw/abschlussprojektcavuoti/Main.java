package sample.ch.aj.bbw.abschlussprojektcavuoti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Main Class
 * @author Aksel Jessen
 * @version 26.08.2020
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            Model myModel = new Model();

            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("resources/MainView.fxml"));
            HBox root = myLoader.load();

            ViewController viewController = (ViewController) myLoader.getController();
            viewController.setModel(myModel);

            Scene scene = new Scene(root, 400, 400);
            primaryStage.setTitle("AddressBook");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
