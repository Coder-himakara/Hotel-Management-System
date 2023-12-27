/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel_management;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author PASINDU
 */
public class DbConfig {

    private static String serverName = "localhost";
    private static String userName = "root";
    private static String password = "";
    private static String dbName = "hotelms_admin";
    private static Integer port = 3307;

    public static Connection getConnection() {

        Connection con = null;

        MysqlDataSource datasource = new MysqlDataSource();

        datasource.setServerName(serverName);
        datasource.setUser(userName);
        datasource.setPassword(password);
        datasource.setDatabaseName(dbName);
        datasource.setPortNumber(port);

        try {
            con = datasource.getConnection();

            System.out.println("Connection Sucsses");
            return con;

        } catch (SQLException ex) {
            System.out.println("Connection Error");
            JOptionPane.showMessageDialog(null, "Connetion Error", "Error", 2);
            Logger.getLogger(" Get Connection -> " + DbConfig.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public static ObservableList<Modeltable_customer> getDatausers() {
        Connection conn = getConnection();
        ObservableList<Modeltable_customer> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM customers");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Modeltable_customer(rs.getString("ID"), rs.getString("TITLE"), rs.getString("NAME"), rs.getString("GENDER"), rs.getString("DOB"),
                        rs.getString("NATIONALITY"), rs.getString("ADDRESS"), rs.getString("NIC_NO"), rs.getString("PHONE_NO"), rs.getString("EMAIL")));
            }
        } catch (Exception e) {
        }
        return list;
    }

}
