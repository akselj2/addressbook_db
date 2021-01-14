package sample.ch.aj.bbw.abschlussprojektcavuoti;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.sql.*;

public class Controller {
    Model myModel;

    @FXML
    Label labelMessage;

    @FXML
    TextField textFieldName;

    @FXML
    TextField textFieldStreet;

    @FXML
    TextField textFieldPlz;

    @FXML
    Button backButton;

    @FXML
    ObservableList<String> names = FXCollections.observableArrayList();

    @FXML
    ListView<String> databaseListView = new ListView<>();

    // binds textfield text properties to the different properties in Model class

    public void setModel(Model model) {
        myModel = model;

        textFieldName.textProperty().bindBidirectional(model.nameProperty());
        textFieldStreet.textProperty().bindBidirectional(model.streetProperty());
        textFieldPlz.textProperty().bindBidirectional(model.plzProperty(), new NumberStringConverter());

        showItems();

    }

    // sets all fields to blank

    public void clear(ActionEvent event) {
        textFieldName.setText("");
        textFieldStreet.setText("");
        textFieldPlz.setText("");
        labelMessage.setText("");
    }

    // once button is clicked, method calls insertFromView() from Model class and sets Text for a Label to "Check Db" and makes it Green.

    public void add(ActionEvent event) {
        myModel.insertFromView();

        showItems();
    }

    public void edit() {
        delete(databaseListView.getSelectionModel().getSelectedItem());

    }

    public void delete() {
        delete(databaseListView.getSelectionModel().getSelectedItem());
    }

    public void delete(String name) {

        String sql = "DELETE FROM addresses WHERE name='" + name + "'";

        Connection myConn = connect();

        try {
            Statement stmt = myConn.createStatement();

            stmt.executeQuery(sql);
            names.remove(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showItems() {
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

    public Connection connect() {
        Connection myConn = null;

        try {
            myConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/addressbook?user=root&password=aksel");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myConn;
    }
}
