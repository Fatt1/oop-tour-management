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
    public void showInfor() {
        System.out.print("|Domestic Tour     |");
        super.showInfor();
        System.out.printf("|               |     |%-8.1f|\n", this.localDiscount);
    }

    @Override
    public void updateMenu(Menu menu) {
        super.updateMenu(menu);
        menu.addNewOption("7. Update new localDiscount");
    }

    @Override
    public void setData(Tour tour, int choice) {
        super.setData(this, choice);
        if (choice == 7) {
            double discount = MyUtil.getAnDouble("Enter local discount: ", "The input is kind of DOUBLE");
            setLocalDiscount(discount);
        }
    }

    @Override
    public void input() {
        super.input();
        this.localDiscount = MyUtil.getAnDouble("Enter local discount: ", "The LOCAL DISCOUNT input is incorrect");
    }
}
