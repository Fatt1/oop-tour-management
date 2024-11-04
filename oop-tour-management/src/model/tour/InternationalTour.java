/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tour;

/**
 *
 * @author nghialam
 */
public class InternationalTour extends Tour{
    private String country;
    private boolean visaRequired;

    public InternationalTour(String country, boolean visaRequired, String tourID, String tourName, String destination, String departureLocation, String vehicleID, int price, String quantity) {
        super(tourID, tourName, destination, departureLocation, vehicleID, price, quantity);
        this.country = country;
        this.visaRequired = visaRequired;
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
    public void showInfor(){
        System.out.printf("|%-5s|%-10s|%-10s|%-10s|%-10s|%-10d|%-5s|%-5s|%-10s|     |\n",
                    tourID, tourName, destination, departureLocation , vehicleID, price, quantity,country,visaRequired );
    }
}
