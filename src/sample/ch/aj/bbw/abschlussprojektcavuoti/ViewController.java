package sample.ch.aj.bbw.abschlussprojektcavuoti;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Aksel Jessen
 * @version 09/01/2021
 */

public class ViewController {

    Model myModel;

    @FXML
    Button editButton;

    @FXML
    Button deleteButton;

    @FXML
    Button backButton;

    @FXML
    ListView<Address> databaseListView;

    ObservableList<Address> addresses = FXCollections.observableArrayList();

    public void setModel(Model model) {
        myModel = model;
    }

    //method retrieves .fxml file, loads it, changes scenes and sets the scene. same method as seen in AddController.fxml

    public void backToMenu() {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("resources/MenuView.fxml"));
            myLoader.load();
            Parent root = myLoader.getRoot();

            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        // don't know how yet
    }

    public void delete() {
        // somehow delete selected address with id in listview
    }

    /*public void showItems() {

        databaseListView.setItems(addresses);

        String sql = "SELECT id, name FROM addresses";

        Statement stmt;
        ResultSet rs;

        try {
            stmt = myModel.connect().createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                addresses.add(rs.getAddress(1));
            }

        } catch(SQLException sqle){
            sqle.printStackTrace();
        }

    }*/
}
