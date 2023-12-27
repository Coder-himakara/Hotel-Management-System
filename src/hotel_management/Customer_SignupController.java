/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hotel_management;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PASINDU
 */
public class Customer_SignupController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField phone_no;
    @FXML
    private TextField email;
    @FXML
    private TextField nic_no;
    @FXML
    private DatePicker dob_picker;
    @FXML
    private TextField address;
    @FXML
    private ChoiceBox<String> gender_box;
    private String[] gender = {"Male", "Female", "Don't Mention"};
    @FXML
    private ChoiceBox<String> title_box;
    private String[] title = {"Mr", "Mrs", "Ms", "Ven"};
    @FXML
    private TextField nationality;
    @FXML
    private Label error_label;
    @FXML
    private TextField user_name;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField con_password;
    @FXML
    private RadioButton yes_btn;
    @FXML
    private ToggleGroup agree_tc;
    @FXML
    private RadioButton no_btn;
    @FXML
    private Button create_btn;
    @FXML
    private Button back_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        title_box.getItems().addAll(title);
        gender_box.getItems().addAll(gender);
    }

    @FXML
    private void sign_up(ActionEvent event) {
        PreparedStatement statement;
        ResultSet queryResult;
        String title_name = title_box.getValue();
        String nameText = name.getText();
        String gender_name = gender_box.getValue();
        LocalDate dob = dob_picker.getValue();
        String addressText = address.getText();
        String nationalityText = nationality.getText();
        String nicNo = nic_no.getText();
        String phoneNo = phone_no.getText();
        String emailText = email.getText();
        Toggle agreeTerms = agree_tc.getSelectedToggle();
        String userName = user_name.getText();
        String userPassword = password.getText();
        String conPassword = con_password.getText();

        if (userName.equals("") || userPassword.equals("") || nicNo.equals("")) {
            error_label.setText("Mandotary Fields are Empty");
        } else {
            if (!userPassword.equals(conPassword)) {
                error_label.setText("Password is not same");
            } else if (!yes_btn.isSelected()) {
                error_label.setText("You have to agree to Terms and Conditions");
            } else {
                String query = "INSERT INTO `customers` (`TITLE`,`NAME`,`GENDER`,`DOB`,`NATIONALITY`,`ADDRESS`,`NIC_NO`,`PHONE_NO`,"
                        + "`EMAIL`,`AGREEMENT`,`USER_NAME`,`PASSWORD`,`CON_PASSWORD`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";

                try {

                    statement = DbConfig.getConnection().prepareStatement(query);
                    statement.setString(1, title_name);
                    statement.setString(2, nameText);
                    statement.setString(3, gender_name);
                    java.sql.Date dob_date = java.sql.Date.valueOf(dob_picker.getValue());
                    statement.setDate(4, dob_date);
                    statement.setString(5, nationalityText);
                    statement.setString(6, addressText);
                    statement.setString(7, nicNo);
                    statement.setString(8, phoneNo);
                    statement.setString(9, emailText);
                    statement.setString(10, yes_btn.getText());
                    statement.setString(11, userName);
                    statement.setString(12, userPassword);
                    statement.setString(13, conPassword);

                    if (statement.executeUpdate() != 0) {

                        Stage create_done = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cutomer_signup_done.fxml"));
                        Parent root = (Parent) loader.load();
                        Cutomer_signup_doneController doneController = loader.getController();
                        doneController.myFunction(nicNo);
                        Scene scene = new Scene(root);
                        create_done.setScene(scene);
                        Stage stage = (Stage) create_btn.getScene().getWindow();
                        stage.close();
                        create_done.show();

                    } else {
                        error_label.setText("Somthing Went Wrong");
                        title_box.setValue(null);
                        name.setText("");
                        gender_box.setValue(null);
                        dob_picker.setValue(null);
                        address.setText("");
                        nationality.setText("");
                        nic_no.setText("");
                        phone_no.setText("");
                        email.setText("");
                        agree_tc.getUserData();
                        user_name.setText("");
                        password.setText("");
                        con_password.setText("");
                    }

                } catch (Exception e) {
                }

            }
        }

    }

    @FXML
    private void back_login(ActionEvent event) throws IOException {
        Stage sign_in_stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Loging.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Main_login.css");
        sign_in_stage.setScene(scene);
        Stage stage = (Stage) back_btn.getScene().getWindow();
        stage.close();
        sign_in_stage.show();

    }

}
