
package model;

import java.io.Serializable;


public class Restaurant implements Serializable{
    private String restaurantID;
    private String restaurantName;
    private String phoneNumber;
    private String address;
    
    public Restaurant(String restaurantID, String restaurantName, String phoneNumber, String address){
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    public Restaurant(){
        
    }
    
    public String getRestaurantID(){
        return restaurantID;
    }
    
    public String getRestaurantName(){
        return restaurantName;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public String getaddress(){
        return address;
    }
    
    public void restaurantID(){
        this.restaurantID = restaurantID;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "restaurantID=" + restaurantID + ", restaurantName=" + restaurantName + ", phoneNumber=" + phoneNumber + ", address=" + address + '}';
    }
    
    public void display() {
        System.out.printf("|%-6s|%-25s|%-14s|%-25s|\n", restaurantID, restaurantName, phoneNumber, address);
    }
}
