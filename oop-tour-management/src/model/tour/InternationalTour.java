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
public class InternationalTour extends Tour implements Serializable {

    private String country;
    private String visaRequired;

    public InternationalTour(String country, String visaRequired, String tourID, String tourName, String destination, String departureLocation, String vehicleID, int adultPrice, int childPrice, int quantity) {
        super(tourID, tourName, destination, departureLocation, vehicleID, adultPrice, childPrice, quantity);
        this.country = country;
        this.visaRequired = visaRequired;
    }

   

    public InternationalTour() {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVisaRequired() {
        return visaRequired;
    }

    public void setVisaRequired(String visaRequired) {
        this.visaRequired = visaRequired;
    }

    @Override
    public void showInfor() {
        System.out.print("|International Tour|");
        super.showInfor();
        System.out.printf("|%-15s|%-5s|        |%-100s|\n", this.country, this.visaRequired, getShortDesription());
    }

    @Override
    public void updateMenu(Menu menu) {
        super.updateMenu(menu);
        menu.addNewOption("9. Update new country");
        menu.addNewOption("10. Update new visa");
    }

    @Override
    public void setData(Tour tour, int choice) {
        super.setData(this, choice);
        if (choice == 9) {
            String country = MyUtil.getString("Enter country: ", "The input is kind of STRING ");
            setCountry(country);
        } else if (choice == 10) {
            String visaRequired = MyUtil.getValueOrDefault("Enter Visa: (YES or NO): ", "Struture of data is String 'YES' or 'NO");
            setVisaRequired(visaRequired);
        }
    }

    @Override
    public String toString() {
        return String.format("|International Tour|" + super.toString() +"|%-15s|%-5s|        |%-100s|\n", this.country, this.visaRequired, getShortDesription());
    }
    
    
    @Override
    public void input() {
        super.input();
        this.country = MyUtil.getString("Enter country: ", "The COUNTRY input is incorrect");
        this.visaRequired = MyUtil.getValueOrDefault("Enter (YES or NO): ", "Struture of data is String 'YES' or 'NO");

    }

    @Override
    public String getShortDesription() {
        return String.format("International Tour: %s to %s (a visa %s )",
                this.tourName, this.destination, visaRequired.equalsIgnoreCase("yes")? "required": "no");
    }
}
