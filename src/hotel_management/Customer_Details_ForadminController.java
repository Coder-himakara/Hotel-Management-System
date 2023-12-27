/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hotel_management;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author PASINDU
 */
public class Customer_Details_ForadminController implements Initializable {

    @FXML
    private TableView<Modeltable_customer> Cutomers_table;
    @FXML
    private TableColumn<Modeltable_customer, String> col_id;
    @FXML
    private TableColumn<Modeltable_customer, String> col_title;
    @FXML
    private TableColumn<Modeltable_customer, String> col_name;
    @FXML
    private TableColumn<Modeltable_customer, String> col_gender;
    @FXML
    private TableColumn<Modeltable_customer, String> col_dob;
    @FXML
    private TableColumn<Modeltable_customer, String> col_nationality;
    @FXML
    private TableColumn<Modeltable_customer, String> col_address;
    @FXML
    private TableColumn<Modeltable_customer, String> col_nic;
    @FXML
    private TableColumn<Modeltable_customer, String> col_phone_no;
    @FXML
    private TableColumn<Modeltable_customer, String> col_email;

    ObservableList<Modeltable_customer> oblist;
    ObservableList<Modeltable_customer> datalist;

    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    private TextField cus_name;
    @FXML
    private TextField cus_phone_no;
    @FXML
    private TextField cus_email;
    @FXML
    private TextField cus_nic_no;
    @FXML
    private DatePicker cus_dob_picker;
    @FXML
    private TextField cus_address;
    @FXML
    private ChoiceBox<String> cus_gender_box;
    private String[] gender = {"Male", "Female", "Don't Mention"};
    @FXML
    private ChoiceBox<String> cus_title_box;
    private String[] title = {"Mr", "Mrs", "Ms", "Ven"};
    @FXML
    private TextField cus_nationality;
    @FXML
    private Button add_customer;
    @FXML
    private Label error_label;
    @FXML
    private Button exit_btn;
    @FXML
    private ChoiceBox<String> search_box;
    private String[] search = {"ID", "NAME", "NIC NO"};
    @FXML
    private TextField search_text;
    @FXML
    private Button search_btn;
    @FXML
    private Button delete_btn;
    @FXML
    private TextField change_field;
    @FXML
    private Button update_btn;
    @FXML
    private Button clear_btn;

    /**
     * Initializes the controller class.
     */
    //-------Table View for customer's deatils--------
    public void updateTabel() {

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_nationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_nic.setCellValueFactory(new PropertyValueFactory<>("nic_no"));
        col_phone_no.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        oblist = DbConfig.getDatausers();
        Cutomers_table.setItems(oblist);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cus_gender_box.getItems().addAll(gender);
        cus_title_box.getItems().addAll(title);
        search_box.getItems().addAll(search);
        updateTabel();
        search_user();

    }

    //----Adding a new customer-------------
    @FXML
    private void add_customer_function(ActionEvent event) {
        PreparedStatement statement;
        ResultSet queryResult;
        String title_name = cus_title_box.getValue();
        String nameText = cus_name.getText();
        String gender_name = cus_gender_box.getValue();
        LocalDate dob = cus_dob_picker.getValue();
        String addressText = cus_address.getText();
        String nationalityText = cus_nationality.getText();
        String nicNo = cus_nic_no.getText();
        String phoneNo = cus_phone_no.getText();
        String emailText = cus_email.getText();
        String agreeText = "yes";
        String userName = "admin ";
        String userPassword = "admin ";
        String conPassword = "admin ";

        if (nameText.equals("") || nicNo.equals("")) {
            error_label.setText("Mandotary Fields are Empty");

        } else {
            String query = "INSERT INTO `customers` (`TITLE`,`NAME`,`GENDER`,`DOB`,`NATIONALITY`,`ADDRESS`,`NIC_NO`,`PHONE_NO`,"
                    + "`EMAIL`,`AGREEMENT`,`USER_NAME`,`PASSWORD`,`CON_PASSWORD`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
            try {

                statement = DbConfig.getConnection().prepareStatement(query);
                statement.setString(1, title_name);
                statement.setString(2, nameText);
                statement.setString(3, gender_name);
                java.sql.Date dob_date = java.sql.Date.valueOf(cus_dob_picker.getValue());
                statement.setDate(4, dob_date);
                statement.setString(5, nationalityText);
                statement.setString(6, addressText);
                statement.setString(7, nicNo);
                statement.setString(8, phoneNo);
                statement.setString(9, emailText);
                statement.setString(10, agreeText);
                statement.setString(11, userName);
                statement.setString(12, userPassword);
                statement.setString(13, conPassword);

                if (statement.executeUpdate() != 0) {
                    error_label.setText("User Added");
                    cus_title_box.setValue(null);
                    cus_name.setText("");
                    cus_gender_box.setValue(null);
                    cus_dob_picker.setValue(null);
                    cus_address.setText("");
                    cus_nationality.setText("");
                    cus_nic_no.setText("");
                    cus_phone_no.setText("");
                    cus_email.setText("");
                    updateTabel();
                    search_user();

                } else {
                    error_label.setText("Somthing Went Wrong");
                    cus_title_box.setValue(null);
                    cus_name.setText("");
                    cus_gender_box.setValue(null);
                    cus_dob_picker.setValue(null);
                    cus_address.setText("");
                    cus_nationality.setText("");
                    cus_nic_no.setText("");
                    cus_phone_no.setText("");
                    cus_email.setText("");

                }

            } catch (Exception e) {

            }
        }

    }

    //------Sign out-------
    @FXML
    private void exit_function(ActionEvent event) throws IOException {
        Stage exit_stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Home_Admin.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Home_admin.css");
        exit_stage.setScene(scene);
        Stage stage = (Stage) exit_btn.getScene().getWindow();
        stage.close();
        exit_stage.show();
    }

    @FXML
    private void search_function(ActionEvent event) {

    }

    //------Searching a customer-------
    void search_user() {

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_nationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_nic.setCellValueFactory(new PropertyValueFactory<>("nic_no"));
        col_phone_no.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        datalist = DbConfig.getDatausers();
        Cutomers_table.setItems(datalist);

        FilteredList<Modeltable_customer> filteredData = new FilteredList<>(datalist, b -> true);
        search_text.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getId().contains(newValue)) {
                    return true; // Filter matches username
                } else if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password

                } else if (String.valueOf(person.getNic_no()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<Modeltable_customer> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(Cutomers_table.comparatorProperty());
        Cutomers_table.setItems(sortedData);
    }

    //----Deleting a customer's details----------
    @FXML
    private void delete_function(ActionEvent event) {
        String changeID = change_field.getText();

        if (changeID.equals("")) {
            error_label.setText("Enter an ID to delete");
        } else {
            PreparedStatement statement;
            String query = "DELETE FROM `customers` WHERE ID = ?";
            try {
                statement = DbConfig.getConnection().prepareStatement(query);
                statement.setString(1, changeID);

                if (statement.executeUpdate() != 0) {
                    error_label.setText("Customer Deleted");
                    JOptionPane.showMessageDialog(null, "Delete");
                    change_field.setText("");
                    updateTabel();
                    search_user();
                } else {
                    error_label.setText("Something went wrong");
                    change_field.setText("");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //-------- Select a customer-----------
    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index = Cutomers_table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        change_field.setText(col_id.getCellData(index).toString());
        cus_title_box.setValue(col_title.getCellData(index).toString());
        cus_name.setText(col_name.getCellData(index).toString());
        cus_gender_box.setValue(col_gender.getCellData(index).toString());
        String dob = col_dob.getCellData(index).toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dob, formatter);
        cus_dob_picker.setValue(localDate);
        cus_nationality.setText(col_nationality.getCellData(index).toString());
        cus_address.setText(col_address.getCellData(index).toString());
        cus_nic_no.setText(col_nic.getCellData(index).toString());
        cus_phone_no.setText(col_phone_no.getCellData(index).toString());
        cus_email.setText(col_email.getCellData(index).toString());

    }

    //-----Edit customers's Details--------
    @FXML
    private void edit_info(ActionEvent event) {

        if ("".equals(change_field.getText())) {
            error_label.setText("Select a customer to edit");
        } else {
            try {
                conn = DbConfig.getConnection();
                String value1 = change_field.getText();
                String value2 = cus_title_box.getValue();
                String value3 = cus_name.getText();
                String value4 = cus_gender_box.getValue();
                java.sql.Date dob_date = java.sql.Date.valueOf(cus_dob_picker.getValue());
                Date value5 = dob_date;
                String value6 = cus_nationality.getText();
                String value7 = cus_address.getText();
                String value8 = cus_nic_no.getText();
                String value9 = cus_phone_no.getText();
                String value10 = cus_email.getText();

                String sql = "UPDATE `customers` SET `ID`='" + value1 + "',`TITLE`='" + value2 + "',`NAME`='" + value3 + "',`GENDER`='" + value4 + "',`DOB`='" + value5 + "',`NATIONALITY`='" + value6 + "',"
                        + "`ADDRESS`='" + value7 + "',`NIC_NO`='" + value8 + "',`PHONE_NO`='" + value9 + "',`EMAIL`='" + value10 + "' WHERE `ID`='" + value1 + "' ";
                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Update");
                updateTabel();
                search_user();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @FXML
    private void clear_all(ActionEvent event) {
        error_label.setText("");
        search_text.setText("");
        change_field.setText("");
        cus_title_box.setValue(null);
        cus_name.setText("");
        cus_gender_box.setValue(null);
        cus_dob_picker.setValue(null);
        cus_address.setText("");
        cus_nationality.setText("");
        cus_nic_no.setText("");
        cus_phone_no.setText("");
        cus_email.setText("");
        updateTabel();
        search_user();

    }

}
