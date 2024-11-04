/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tour;

/**
 *
 * @author nghialam
 */
public class Tour {
    protected String tourID, tourName, destination, departureLocation, vehicleID;
    protected int price; 
    protected String quantity;

    public Tour(String tourID, String tourName, String destination, String departureLocation, String vehicleID, int price, String quantity) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.destination = destination;
        this.departureLocation = departureLocation;
        this.vehicleID = vehicleID;
        this.price = price;
        this.quantity = quantity;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Tour{" + "tourID=" + tourID + ", tourName=" + tourName + ", destination=" + destination + ", departureLocation=" + departureLocation + ", vehicleID=" + vehicleID + ", price=" + price + ", quantity=" + quantity + '}';
    }
    
    public void showInfor(){
        System.out.printf("|%-5s|%-10s|%-10s|%-10s|%-10s|%-10d|%-5s|          |     |     |",
                    tourID, tourName, destination, departureLocation , vehicleID, price, quantity );
    }
}
