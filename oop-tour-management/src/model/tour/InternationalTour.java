/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tour;

import java.io.Serializable;
import util.MyUtil;

/**
 *
 * @author nghialam
 */
public class InternationalTour extends Tour implements Serializable{

    private String country;
    private boolean visaRequired;

    public InternationalTour(String country, boolean visaRequired, String tourID, String tourName, String destination, String departureLocation, String vehicleID, int price, int quantity) {
        super(tourID, tourName, destination, departureLocation, vehicleID, price, quantity);
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

    public boolean isVisaRequired() {
        return visaRequired;
    }

    public void setVisaRequired(boolean visaRequired) {
        this.visaRequired = visaRequired;
    }

    @Override
    public void showInfor() {
        System.out.print("|International Tour|");
        super.showInfor();
        System.out.printf("|%-10s|%-5s|     |\n", this.country, this.visaRequired);
    }

    @Override
    public void input() {
        super.input();
        this.country = MyUtil.getString("enter country: ", "The COUNTRY input is incorrect");
        System.out.println("visa required: YES or NO");
        String s = MyUtil.getString("Enter: ", "Struture of data is String 'YES' or 'NO' ");
        while (true) {
            if (s.compareToIgnoreCase("YES") == 0 || s.compareToIgnoreCase("NO") == 0) {
                this.visaRequired = Boolean.parseBoolean(s);
                break;
            } else {
                System.out.println("You must enter YES or NO");
                s = MyUtil.getString("Enter: ", "Struture of data is String 'YES' or 'NO' ");
            }
        }
    }
}
