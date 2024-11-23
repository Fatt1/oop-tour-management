/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tour;

import java.io.Serializable;
import ui.Menu;
import util.MyUtil;

/**
 *
 * @author nghialam
 */
public class DomesticTour extends Tour implements Serializable {

    private double localDiscount;

    public DomesticTour(double localDiscount, String tourID, String tourName, String destination, String departureLocation, String vehicleID, int adultPrice, int childPrice, int quantity) {
        super(tourID, tourName, destination, departureLocation, vehicleID, adultPrice, childPrice, quantity);
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
    public void showInfor() {
        System.out.print("|Domestic Tour     |");
        super.showInfor();
        System.out.printf("|               |     |%-8.2f|\n", this.localDiscount);
    }

    @Override
    public void updateMenu(Menu menu) {
        super.updateMenu(menu);
        menu.addNewOption("9. Update new local discount");
    }

    @Override
    public void setData(Tour tour, int choice) {
        super.setData(this, choice);
        if (choice == 9) {
            double discount = MyUtil.getAnDouble("Enter local discount: ", "The input is kind of DOUBLE");
            setLocalDiscount(discount);
        }
    }

    @Override
    public String toString() {
        return String.format("|Domestic Tour     |" + super.toString() + "|               |     |%-8.2f|\n", this.localDiscount);
    }

    
    
    @Override
    public void input() {
        super.input();
        
        this.localDiscount = MyUtil.getAnDouble("Enter local discount: ", "The LOCAL DISCOUNT input is incorrect");
    }
    
//    public double getDiscountPrice(){
//        return localDiscount * ;
//    }

    @Override
    public int calculateChildPrice() {
        return (int)Math.round(this.childPrice * (1 + MyUtil.VAT - localDiscount));
    }

    @Override
    public int calculateAdultPrice() {
        return (int)Math.round(this.adultPrice * (1 + MyUtil.VAT - localDiscount));
    }
}
