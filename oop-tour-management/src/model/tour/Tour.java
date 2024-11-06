/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tour;

import java.io.Serializable;
import lists.ListTour;
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
        this.tourID = ListTour.getInstance().getUniqueTourID();
        this.tourName = MyUtil.getString("ENTER TOUR NAME: ", "ERROR");
        this.destination = MyUtil.getString("ENTER DESTINATION: ", "ERROR");
        this.departureLocation = MyUtil.getString("ENTER DEPARTURE LOCATION: ", "ERROR");
        this.vehicleID = MyUtil.getId("ENTER VEHICLE ID (VXXX): ", "ERROR", "^[v|V]\\d{3}$");
        this.price = MyUtil.getAnInteger("ENTER PRICE: ", "ERROR", 1000, 50000000); 
        this.quantity = MyUtil.getAnInteger("ENTER QUANTITY: ", "ERROR", 0, 100);
    }

    public void showInfor() {
        System.out.printf("|%-5s|%-10s|%-10s|%-10s|%-10s|%-10d|%-5s|",
                tourID, tourName, destination, departureLocation, vehicleID, price, quantity);
    }
}
