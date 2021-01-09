package sample.ch.aj.bbw.abschlussprojektcavuoti;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Model
 *
 * @author Aksel Jessen
 * @version 09/01/2021
 */

public class Model {

    //insert new variables and shit here

    private StringProperty name = new SimpleStringProperty();
    private StringProperty street = new SimpleStringProperty();
    private IntegerProperty zip = new SimpleIntegerProperty();
    private IntegerProperty age = new SimpleIntegerProperty();
    private StringProperty confirmMessage = new SimpleStringProperty();


    /*
    method gets all variables after having been changed from property to normal datatype
    then calls the insert method with the params in a try catch
     */

    public void insertFromView(){

        String currentName = getName();
        String currentStreet = getStreet();
        int currentZip = getZip();
        int currentAge = getAge();

        try {
            insert(currentName, currentStreet, currentZip, currentAge);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * insert method
     *
     * bindings sql style.
     *
     * @param name      String variable for name in address
     * @param street    String variable for street in address
     * @param zip       integer for zip code in address
     * @param age       integer for age (to be changed) in address
     */

    public void insert(String name, String street, int zip, int age){

        String sql = "INSERT INTO addresses(name,street,zip,age) VALUES(?,?,?,?)";

        try (Connection myConn = this.connect();
             PreparedStatement pstmt = myConn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, street);
            pstmt.setInt(3, zip);
            pstmt.setInt(4, age);
            pstmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    // connects to database with username and password

    private Connection connect() {

        Connection myConn = null;

        try {
            myConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/addressbook?user=root&password=aksel");
        } catch (SQLException e){
            e.printStackTrace();
        }

        return myConn;
    }

    // getters and setters

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public int getZip() {
        return zip.get();
    }

    public IntegerProperty zipProperty() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip.set(zip);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getConfirmMessage() {
        return confirmMessage.get();
    }

    public StringProperty confirmMessageProperty() {
        return confirmMessage;
    }

    public void setConfirmMessage(String confirmMessage) {
        this.confirmMessage.set(confirmMessage);
    }
}
