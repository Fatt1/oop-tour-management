/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tour;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author nghialam
 */
public class TourSchedule {
    private String tourScheduleID, tourID, guildID;
    private LocalDate returnDay, departureDay;
    private int emptySlots, donGia, adultPrice, childPrice;
    private int totalPrice;
    private long duration;

    public TourSchedule(String tourScheduleID, String tourID, String guildID, LocalDate returnDay, LocalDate departureDay, int emptySlots, int donGia, int adultPrice, int childPrice, int totalPrice, long duration) {
        this.tourScheduleID = tourScheduleID;
        this.tourID = tourID;
        this.guildID = guildID;
        this.returnDay = returnDay;
        this.departureDay = departureDay;
        this.emptySlots = emptySlots;
        this.donGia = donGia;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.totalPrice = totalPrice;
        this.duration = ChronoUnit.DAYS.between(returnDay, departureDay);
    }

    public TourSchedule() {
        this.tourScheduleID = "TS1234";
        this.tourID = "T1234";
        this.guildID = "G1234";
        this.returnDay = LocalDate.now();
        this.departureDay = LocalDate.now();
        this.emptySlots = 0;
        this.donGia = 0;
        this.adultPrice = 0;
        this.childPrice = 0;
        this.totalPrice = 0;
        this.duration = ChronoUnit.DAYS.between(returnDay, departureDay);
    }

    public String getTourScheduleID() {
        return tourScheduleID;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public String getGuildID() {
        return guildID;
    }

    public void setGuildID(String guildID) {
        this.guildID = guildID;
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

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
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
    
}
