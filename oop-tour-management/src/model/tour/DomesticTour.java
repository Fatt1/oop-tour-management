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
        System.out.printf("|               |     |%-8.2f|%-100s|\n", this.localDiscount, getShortDesription());
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
            double discount;
            do{
                discount = MyUtil.getAnDouble("Enter local discount: ", "The input is kind of DOUBLE");
                setLocalDiscount(discount);
                if(discount > 1){
                    System.out.println("Please again! local discount < 1");
                }
            }while(discount > 1);

        }
    }

    @Override
    public String toString() {
        return String.format("|Domestic Tour     |" + super.toString() + "|               |     |%-8.2f|-100%s|\n", this.localDiscount, this.getShortDesription());
    }

    
    
    @Override
    public void input() {
        super.input();
        
            double discount;
            do{
                discount = MyUtil.getAnDouble("Enter local discount: ", "The input is kind of DOUBLE");
                setLocalDiscount(discount);
                if(discount > 1){
                    System.out.println("Please again! local discount < 1");
                }
            }while(discount > 1);  
    }
    
    @Override
    public String getShortDesription() {
        return String.format("Domestic Tour: %s to %s with a discount of %4.2f", this.tourName, this.destination, this.localDiscount);
    }

}
