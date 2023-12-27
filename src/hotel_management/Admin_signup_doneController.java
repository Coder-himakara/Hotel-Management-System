/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hotel_management;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
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
public class Admin_signup_doneController implements Initializable {

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
        Parent root = FXMLLoader.load(getClass().getResource("Home_Admin.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Home_admin.css");
        all_done.setScene(scene);
        Stage stage = (Stage) enter_home.getScene().getWindow();
        stage.close();
        all_done.show();

    }

    public void myFunction(String text) {
        show_id.setText(text);

    }

}
