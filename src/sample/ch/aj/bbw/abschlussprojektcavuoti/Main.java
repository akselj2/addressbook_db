package sample.ch.aj.bbw.abschlussprojektcavuoti;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;

/**
 * Main Class
 *
 * @author Aksel Jessen
 * @version 09/01/2021
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {

            Model myModel = new Model();

            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("resources/MenuView.fxml"));
            HBox root = myLoader.load();

            Controller controller = (Controller) myLoader.getController();
            controller.setModel(myModel);

            Scene scene = new Scene(root);
            primaryStage.setTitle("AddressBook");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
