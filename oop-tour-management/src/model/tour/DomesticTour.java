/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tour;

/**
 *
 * @author nghialam
 */
public class DomesticTour extends Tour{
    private double localDiscount;

    public DomesticTour(double localDiscount, String tourID, String tourName, String destination, String departureLocation, String vehicleID, int price, String quantity) {
        super(tourID, tourName, destination, departureLocation, vehicleID, price, quantity);
        this.localDiscount = localDiscount;
    }

    public double getLocalDiscount() {
        return localDiscount;
    }

    public void setLocalDiscount(double localDiscount) {
        this.localDiscount = localDiscount;
    }
    
    @Override
    public void showInfor(){
        System.out.printf("|%-5s|%-10s|%-10s|%-10s|%-10s|%-10d|%-5s|          |     |%-5f|\n",
                    tourID, tourName, destination, departureLocation , vehicleID, price, quantity, localDiscount);
    }
}
