/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tour;

import java.io.Serializable;
import lists.VehicleList;
import ui.Menu;
import util.MyUtil;

/**
 *
 * @author nghialam
 */
public abstract class Tour implements Serializable {

    protected String tourID, tourName, destination, departureLocation, vehicleID;
    protected int adultPrice, childPrice;
    protected int quantity;

    public Tour(String tourID, String tourName, String destination, String departureLocation, String vehicleID, int adultPrice, int childPrice, int quantity) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.destination = destination;
        this.departureLocation = departureLocation;
        this.vehicleID = vehicleID;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
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

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }
    
    public abstract int calculateChildPrice();
    public abstract int calculateAdultPrice();
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%-5s|%-30s|%-20s|%-20s|%-10s|%-10s|%-10d|%-8s",
                tourID, tourName, destination, departureLocation, vehicleID, adultPrice, childPrice, quantity);
    }
    public void input(String id){
        this.tourID = id;
    }
    public void input() {
        this.tourName = MyUtil.getString("Enter tour name: ", "The input is of type STRING");
        this.destination = MyUtil.getString("Enter destination: ", "The input is of type STRING");
        this.departureLocation = MyUtil.getString("Enter Departure location: ", "The input is of type STRING");
        this.vehicleID = VehicleList.getInstance().getExistedVehicleId();
        this.adultPrice = MyUtil.getAnInteger("Enter adult price(1000 <= price <= 500000000): ", "The input is of type INT(1000 <= price <= 50000000)", 1000, 500000000);
        this.childPrice = MyUtil.getAnInteger("Enter child price(1000 <= price <= 500000000): ", "The input is of type INT(1000 <= price <= 50000000)", 1000, 500000000);
        this.quantity = MyUtil.getAnInteger("Enter Quantity(1 <= quantity <= 50): ", "The input is of type INT", 1, 50);
    }

    public void updateMenu(Menu menu) {
        menu.addNewOption("1. Exit");
        menu.addNewOption("2. Update new tour name");
        menu.addNewOption("3. Update new destination");
        menu.addNewOption("4. Update new departureLocation");
        menu.addNewOption("5. Update new adult price");
        menu.addNewOption("6. Update new child price");
        menu.addNewOption("7. Update new quantity");
        menu.addNewOption("8. Update new VehicleID");

    }

    public void setData(Tour tour, int choice) {
        switch (choice) {
            case 1:
                System.out.println("Bye bye!!");
                break;
            case 2:
                String Name = MyUtil.getString("Enter tour name: ", "The input is kind of STRING!!!");
                tour.setTourName(Name);
                break;
            case 3:
                String des = MyUtil.getString("Enter destination: ", "The input is kind of STRING");
                tour.setDestination(des);
                break;
            case 4:
                String dLocation = MyUtil.getString("Enter departure location: ", "The input is kind of STRING");
                tour.setDepartureLocation(dLocation);
                break;
            case 5:
                int adultCost = MyUtil.getAnInteger("Enter adult price (1000<= price <= 50000000): ", "The input is kind of INTEGER");
                tour.setAdultPrice(adultCost);
                break;
            case 6:
                int childCost = MyUtil.getAnInteger("Enter child price (1000<= price <= 50000000): ", "The input is kind of INTEGER");
                tour.setChildPrice(childCost);
                break;                
            case 7:
                int slot = MyUtil.getAnInteger("Enter quantity(1 <= quantity <= 50): ", "The input is kind of integer (1->50)", 1, 50);
                tour.setQuantity(slot);
                break;
            case 8:
                
                String newVehicleId = VehicleList.getInstance().getExistedVehicleId();
                tour.setVehicleID(newVehicleId);
                break;
        }
    }

    public void showInfor() {
        System.out.printf("%-5s|%-30s|%-20s|%-20s|%-10s|%-10d|%-10s|%-8s",
                tourID, tourName, destination, departureLocation, vehicleID, adultPrice, childPrice, quantity);
    }
}
