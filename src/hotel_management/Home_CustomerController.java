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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PASINDU
 */
public class Home_CustomerController implements Initializable {

    @FXML
    private Button signout_btn;
    @FXML
    private Button profile_btn;
    @FXML
    private Button booking_btn;
    @FXML
    private Button details_btn;
    @FXML
    private Button search_btn;
    @FXML
    private ImageView back_img;
    @FXML
    private ImageView logo_img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void signout_function(ActionEvent event) throws IOException {
        Stage exit_stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Loging.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Main_login.css");
        exit_stage.setScene(scene);
        Stage stage = (Stage) signout_btn.getScene().getWindow();
        stage.close();
        exit_stage.show();
    }

}
