/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package hotel_management;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author PASINDU
 */
public class LogingController implements Initializable {

    @FXML
    private Tab customer_login_screen;
    @FXML
    private Tab admin_login_screen;
    @FXML
    private TabPane tabpane;
    @FXML
    private TextField username_customer;
    @FXML
    private PasswordField password_customer;
    @FXML
    private Button login_customer_btn;
    @FXML
    private Button signup_customer_btn;
    @FXML
    private TextField username_admin;
    @FXML
    private PasswordField password_admin;
    @FXML
    private Button login_admin_btn;
    @FXML
    private Button signup_admin_btn;
    @FXML
    private Label error_label_customer;
    @FXML
    private Label error_label_admin;
    @FXML
    private ImageView cus_image;
    @FXML
    private ImageView admin_image;
    @FXML
    private ImageView cus_main_img;
    @FXML
    private ImageView admin_main_img;
    @FXML
    private Button check_rooms;
    @FXML
    private AnchorPane cus_pane;
    @FXML
    private AnchorPane admin_pane;
    @FXML
    private ImageView cus_name_icn;
    @FXML
    private ImageView cus_pass_icn;
    @FXML
    private ImageView admin_name_icn;
    @FXML
    private ImageView admin_pass_icn;
    @FXML
    private ImageView welcome;
    @FXML
    private ImageView hotel_logo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void cus_login_function(ActionEvent event) {
        PreparedStatement statement;
        ResultSet queryResult;
        String userCustomerName = username_customer.getText();
        String userCustomerPassword = password_customer.getText();

        if (userCustomerName.equals("") || userCustomerPassword.equals("")) {
            error_label_customer.setText("Filds Are Empty");
        } else {
            String query = "SELECT * FROM customers WHERE USER_NAME = ? AND PASSWORD = ?";
            try {

                statement = DbConfig.getConnection().prepareStatement(query);
                statement.setString(1, userCustomerName);
                statement.setString(2, userCustomerPassword);
                queryResult = statement.executeQuery();

                if (queryResult.next()) {
                    error_label_customer.setText("Login Sucses");
                    Stage login_stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Home_Customer.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/CSS/Home_customer.css");
                    login_stage.setScene(scene);
                    Stage stage = (Stage) login_customer_btn.getScene().getWindow();
                    stage.close();
                    login_stage.show();

                } else {
                    error_label_customer.setText("Login Faild");
                }
            } catch (Exception e) {
            }
        }

    }

    @FXML
    private void cus_signup_function(ActionEvent event) throws IOException {
        Stage sign_up_stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Customer_Signup.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Customer_signup.css");
        sign_up_stage.setScene(scene);
        Stage stage = (Stage) signup_customer_btn.getScene().getWindow();
        stage.close();
        sign_up_stage.show();

    }

    @FXML
    private void ad_login_function(ActionEvent event) {

        PreparedStatement statement;
        ResultSet queryResult;
        String userAdminName = username_admin.getText();
        String userAdminPassword = password_admin.getText();

        if (userAdminName.equals("") || userAdminPassword.equals("")) {
            error_label_admin.setText("Filds Are Empty");
        } else {
            String query = "SELECT * FROM sign_up WHERE USERNAME = ? AND PASSWORD = ?";
            try {

                statement = DbConfig.getConnection().prepareStatement(query);
                statement.setString(1, userAdminName);
                statement.setString(2, userAdminPassword);
                queryResult = statement.executeQuery();

                if (queryResult.next()) {
                    error_label_admin.setText("Login Sucses");
                    Stage login_stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Home_Admin.fxml"));
                    Parent root = (Parent) loader.load();
                    Home_AdminController doneController = loader.getController();
                    doneController.show_name(userAdminName);
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/CSS/Home_admin.css");
                    login_stage.setScene(scene);
                    Stage stage = (Stage) login_admin_btn.getScene().getWindow();
                    stage.close();
                    login_stage.show();

                } else {
                    error_label_admin.setText("Login Faild");
                }
            } catch (Exception e) {
            }
        }

    }

    @FXML
    private void ad_signup_function(ActionEvent event) throws IOException {
        Stage sign_up_stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Admin_Signup.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Admin_signup.css");
        sign_up_stage.setScene(scene);
        Stage stage = (Stage) signup_admin_btn.getScene().getWindow();
        stage.close();
        sign_up_stage.show();

    }

}
