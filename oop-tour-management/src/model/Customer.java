/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author User
 */
public class Customer implements Serializable{
    private String id;
    private String firstName;
    private String lastName;
    private String nationality; // quốc tịch
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth; // ngày sinh sử dụng LocalDate, LocalDate là 1 class có sẵn trong java để lưu thời gian

    public Customer(String id, String firstName, String lastName, String nationality, String phoneNumber, String address, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
    
    public Customer(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    
    public int getAge(){
        return LocalDate.now().getYear() - dateOfBirth.getYear(); // localDate.now() là hàm để lấy ngày tháng năm hiện tại, nhưng mình chỉ cần lấy năm thôi
                                                                    // nên sử dụng hàm getYear() để lấy năm
    }

    @Override
    public String toString() {
        return String.format("|%-6s|%-25s|%-15s|%-14s|%-25s|%-15s|\n",
                            id, (lastName + " " + firstName),  nationality, phoneNumber, address, dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
    
    public void display() { 
        System.out.printf("|%-6s|%-25s|%-15s|%-14s|%-25s|%-15s|\n",
                            id, (lastName + " " + firstName),  nationality, phoneNumber, address, dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
}
