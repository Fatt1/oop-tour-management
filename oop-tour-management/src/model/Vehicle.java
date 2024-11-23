/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Vehicle implements Serializable{
    private String id;
    private String name;
    private String company;
    private String phonenumber;
    private int numberOfSeats;

    public Vehicle(String id, String name, String company, String phonenumber, int numberOfSeats) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.phonenumber = phonenumber;
        this.numberOfSeats = numberOfSeats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return String.format("|%-6s|%-30s|%-15s|%-14s|%-6d|\n",
                            id, name, company, phonenumber, numberOfSeats);
    }
    
    public void display() {
        System.out.printf("|%-6s|%-30s|%-15s|%-14s|%-6d|\n",
                            id, name, company, phonenumber, numberOfSeats);
    }
    
}
