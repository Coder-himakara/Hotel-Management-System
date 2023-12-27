/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel_management;

/**
 *
 * @author PASINDU
 */
public class Modeltable_customer {

    String id, title, name, gender, dob, nationality, address, nic_no, phone_no, email;

    public Modeltable_customer(String id, String title, String name, String gender, String dob, String nationality, String address, String nic_no, String phone_no, String email) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.nationality = nationality;
        this.address = address;
        this.nic_no = nic_no;
        this.phone_no = phone_no;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getNationality() {
        return nationality;
    }

    public String getAddress() {
        return address;
    }

    public String getNic_no() {
        return nic_no;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNic_no(String nic_no) {
        this.nic_no = nic_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
