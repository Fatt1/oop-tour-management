/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tour;

import util.MyUtil;

/**
 *
 * @author nghialam
 */
public class DomesticTour extends Tour{
    private double localDiscount;

    public DomesticTour(double localDiscount, String tourID, String tourName, String destination, String departureLocation, String vehicleID, int price, int quantity) {
        super(tourID, tourName, destination, departureLocation, vehicleID, price, quantity);
        this.localDiscount = localDiscount;
    }

    public DomesticTour() {
    }

    public double getLocalDiscount() {
        return localDiscount;
    }

    public void setLocalDiscount(double localDiscount) {
        this.localDiscount = localDiscount;
    }
    
    @Override
    public void showInfor(){
        super.showInfor();
        System.out.printf("|          |     |%-4.1f|\n", this.localDiscount);
    }
    
    @Override
    public void input(){
        super.input();
        this.localDiscount = MyUtil.getAnDouble("ENTER LOCAL DISCOUNT: ", "ERROR");
    }
}
