/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tour;

import java.io.Serializable;
import lists.TourList;
import util.MyUtil;

/**
 *
 * @author nghialam
 */
public class Tour implements Serializable{

    protected String tourID, tourName, destination, departureLocation, vehicleID;
    protected int price;
    protected int quantity;

    public Tour(String tourID, String tourName, String destination, String departureLocation, String vehicleID, int price, int quantity) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.destination = destination;
        this.departureLocation = departureLocation;
        this.vehicleID = vehicleID;
        this.price = price;
        this.quantity = quantity;
    }

    public Tour() {
    }

    public String getTourID() {
        return tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Tour{" + "tourID=" + tourID + ", tourName=" + tourName + ", destination=" + destination + ", departureLocation=" + departureLocation + ", vehicleID=" + vehicleID + ", price=" + price + ", quantity=" + quantity + '}';
    }

    public void input() {
        this.tourID = TourList.getInstance().enterTourID();
        this.tourName = MyUtil.getString("Enter tour name: ", "\"The input is of type STRING");
        this.destination = MyUtil.getString("Enter destination: ", "The input is of type STRING");
        this.departureLocation = MyUtil.getString("Enter Departure location: ", "The input is of type STRING");
        this.vehicleID = MyUtil.getId("Enter vehicle ID(VE123): ", "The format is incorrect", "VE\\d{3}$");;
        this.price = MyUtil.getAnInteger("Enter price: ", "The input is of type INT(1000 <= price <= 50000000)", 1000, 50000000);
        this.quantity = MyUtil.getAnInteger("Enter Quantity: ", "The input is of type INT", 0, 100);
    }

    public void showInfor() {
        System.out.printf("|%-5s|%-30s|%-20s|%-20s|%-5s|%-10d|%-5s",
                tourID, tourName, destination, departureLocation, vehicleID, price, quantity);
    }
}
