package sample.ch.aj.bbw.abschlussprojektcavuoti;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.converter.NumberStringConverter;

import java.sql.*;

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

    public void setModel(Model model){
        myModel = model;

        textFieldName.textProperty().bindBidirectional(model.nameProperty());
        textFieldStreet.textProperty().bindBidirectional(model.streetProperty());
        textFieldZip.textProperty().bindBidirectional(model.zipProperty(), new NumberStringConverter());
        textFieldAge.textProperty().bindBidirectional(model.ageProperty(), new NumberStringConverter());
        labelMessage.textProperty().bindBidirectional(model.confirmMessageProperty());

    }

    public void reset(ActionEvent event){
        textFieldName.setText("");
        textFieldStreet.setText("");
        textFieldZip.setText("");
        textFieldAge.setText("");
        labelMessage.setText("");
    }

    public void confirm(ActionEvent event){
        myModel.insertFromView();

        labelMessage.setText("Check DB");
        labelMessage.setTextFill(Color.GREEN);
    }
}
