package sample.ch.aj.bbw.abschlussprojektcavuoti;

import com.sun.glass.ui.EventLoop;
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
import java.sql.*;

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
    ObservableList<String> names = FXCollections.observableArrayList();

    @FXML
    ListView<String> databaseListView = new ListView<>();

    public void setModel(Model model) {
        myModel = model;

        showItems();
    }

    public ViewController() {
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
    }

    public void delete() {

        final String selectedItem = databaseListView.getSelectionModel().getSelectedItem();

        String sql = "DELETE FROM addresses WHERE name='" + selectedItem + "'";

        Connection myConn = connect();

        try {
            Statement stmt = myConn.createStatement();

            stmt.executeQuery(sql);
            names.remove(selectedItem);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void showItems(){
        String sql = "SELECT name FROM addresses";

        Connection myConn = connect();

        try {
            Statement stmt = myConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                names.add(name);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        databaseListView.setItems(names);
    }

    public Connection connect(){
        Connection myConn = null;

        try {
            myConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/addressbook?user=root&password=aksel");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myConn;
    }
}
