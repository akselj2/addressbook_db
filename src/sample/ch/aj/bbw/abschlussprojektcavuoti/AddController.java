package sample.ch.aj.bbw.abschlussprojektcavuoti;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import java.io.IOException;

/**
 * @author Aksel Jessen
 * @version 09/01/2021
 */

public class AddController {

    Model myModel;

    @FXML
    Label labelMessage;

    @FXML
    TextField textFieldName;

    @FXML
    TextField textFieldStreet;

    @FXML
    TextField textFieldZip;

    @FXML
    TextField textFieldAge;

    @FXML
    Button confirmButton;

    @FXML
    Button resetButton;

    @FXML
    Button backButton;

    // binds textfield text properties to the different properties in Model class

    public void setModel(Model model){
        myModel = model;

        textFieldName.textProperty().bindBidirectional(model.nameProperty());
        textFieldStreet.textProperty().bindBidirectional(model.streetProperty());
        textFieldZip.textProperty().bindBidirectional(model.zipProperty(), new NumberStringConverter());
        textFieldAge.textProperty().bindBidirectional(model.ageProperty(), new NumberStringConverter());

    }

    // sets all fields to blank

    public void reset(ActionEvent event){
        textFieldName.setText("");
        textFieldStreet.setText("");
        textFieldZip.setText("");
        textFieldAge.setText("");
        labelMessage.setText("");
    }

    // once button is clicked, method calls insertFromView() from Model class and sets Text for a Label to "Check Db" and makes it Green.

    public void confirm(ActionEvent event){
        myModel.insertFromView();

        labelMessage.setText("Check DB");
        labelMessage.setTextFill(Color.GREEN);
    }

    // method changes scene from creation interface to menu

    public void backToMenu(ActionEvent event){
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("resources/MenuView.fxml"));
            myLoader.load();
            Parent root = myLoader.getRoot();

            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
