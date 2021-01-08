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
import javafx.stage.Window;

import java.io.IOException;

/**
 * Main Class
 *
 * @author Aksel Jessen
 * @version 09/01/2021
 */

public class Main extends Application {

    @FXML
    Button AddButton;

    @FXML
    Button UpdateButton;

    @FXML
    Button ViewButton;

    @FXML
    Button DeleteButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            Model myModel = new Model();

            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("resources/MenuView.fxml"));
            HBox root = myLoader.load();

            Scene scene = new Scene(root, 400, 400);
            primaryStage.setTitle("AddressBook");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeSceneAdd(ActionEvent event) {
        try {

            Model myModel = new Model();

            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("resources/AddView.fxml"));
            myLoader.load();
            Parent root = myLoader.getRoot();

            AddController AddController = (AddController) myLoader.getController();
            AddController.setModel(myModel);

            Stage stage = (Stage) AddButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void changeSceneUpdate(ActionEvent event) {

    }

    public void changeSceneView(ActionEvent event) {

    }

    public void changeSceneDelete(ActionEvent event) {

    }
}
