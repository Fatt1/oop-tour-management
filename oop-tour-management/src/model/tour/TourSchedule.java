/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tour;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lists.TourList;

/**
 *
 * @author nghialam
 */
public class TourSchedule implements Serializable {

    private String ID, tourID, employeeID;
    private LocalDate returnDay, departureDay;
    private int emptySlots, adultPrice, childPrice;
    private int totalPrice;
    private long duration;
    private TourList t = TourList.getInstance();

    public TourSchedule(String ID, String tourID, String employeeID, LocalDate returnDay, LocalDate departureDay, int emptySlots) {
        this.ID = ID;
        this.tourID = tourID;
        this.employeeID = employeeID;
        this.returnDay = returnDay;
        this.departureDay = departureDay;
        this.emptySlots = emptySlots;
        this.adultPrice = t.searchObjectById(ID).getAdultPrice();
        this.childPrice = t.searchObjectById(ID).getChildPrice();
        this.duration = ChronoUnit.DAYS.between(departureDay, returnDay);
    }

    public TourSchedule() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public void setEmployeeID(String EmployeeID) {
        this.employeeID = EmployeeID;
    }

    public LocalDate getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(LocalDate returnDay) {
        this.returnDay = returnDay;
    }

    public LocalDate getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(LocalDate departureDay) {
        this.departureDay = departureDay;
    }

    public int getEmptySlots() {
        return emptySlots;
    }

    public void setEmptySlots(int emptySlots) {
        this.emptySlots = emptySlots;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("|%-20s|%-8s|%-12s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                this.ID, this.tourID, this.employeeID, this.returnDay, this.departureDay, this.emptySlots, this.duration, this.adultPrice, this.childPrice, this.totalPrice);
    }

    public void showInfor() {
        System.out.printf("|%-20s|%-8s|%-12s|%-15s|%-15s|%-15s|%-15d|%-15s|%-15s|%-15s|\n",
                this.ID, this.tourID, this.employeeID, this.returnDay, this.departureDay, this.emptySlots, this.duration, this.adultPrice, this.childPrice, this.totalPrice);
    }

}
