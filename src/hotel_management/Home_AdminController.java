/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hotel_management;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PASINDU
 */
public class Home_AdminController implements Initializable {

    @FXML
    private Button signout_btn;
    @FXML
    private Button cutomers_check;
    @FXML
    private Button check_in;
    @FXML
    private Button check_out;
    @FXML
    private Button rooms;
    @FXML
    private Button reports;
    @FXML
    private Button profile;
    @FXML
    private Label show_name;
    @FXML
    private ImageView logo_pic;
    @FXML
    private AnchorPane button_pane;
    @FXML
    private ImageView hotel_pic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sign_out_function(ActionEvent event) throws IOException {
        Stage sign_out_stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Loging.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Main_login.css");
        sign_out_stage.setScene(scene);
        Stage stage = (Stage) signout_btn.getScene().getWindow();
        stage.close();
        sign_out_stage.show();

    }

    @FXML
    private void customer_details_function(ActionEvent event) throws IOException {
        Stage sign_out_stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Customer_Details_Foradmin.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Cus_details_admin.css");
        sign_out_stage.setScene(scene);
        Stage stage = (Stage) cutomers_check.getScene().getWindow();
        stage.close();
        sign_out_stage.show();

    }

    public void show_name(String text) {
        show_name.setText(text);
    }
}
