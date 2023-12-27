/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hotel_management;

import static hotel_management.DbConfig.getConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PASINDU
 */
public class Cutomer_signup_doneController implements Initializable {

    String nic, id;
    @FXML
    private Label show_id;
    @FXML
    private Button enter_home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void enter_home(ActionEvent event) throws IOException {
        Stage all_done = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Home_Customer.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Home_customer.css");
        all_done.setScene(scene);
        Stage stage = (Stage) enter_home.getScene().getWindow();
        stage.close();
        all_done.show();

    }

    public void info(String id) {

        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void myFunction(String text) {
        //show_id.setText(text);
        PreparedStatement stmt;

        String sql1 = "SELECT `ID` FROM `customers` WHERE `NIC_NO`= '" + text + "';";
        try {
            //PreparedStatement ps = conn.prepareStatement("SELECT `ID` FROM `customers` WHERE `NIC_NO`= '" + text + "';");
            stmt = DbConfig.getConnection().prepareStatement(sql1);
            stmt.executeUpdate(sql1);
            System.out.println(stmt.executeUpdate(sql1));

            System.out.println("Getting id succes!");
            show_id.setText(Integer.toString(stmt.executeUpdate(sql1)));

        } catch (Exception e) {

        }

        show_id.setText(getId());
    }

}
