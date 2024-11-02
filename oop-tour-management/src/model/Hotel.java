
package model;


public class Hotel {

    private String hotelID;
    private String hotelName;
    private String phoneNumber;
    private String address;

    public Hotel(String hotelID, String hotelName, String phoneNumber, String address) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

    public Hotel() {

    }

    public String getHotelID() {
        return hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Hotel{" + "hotelID=" + hotelID + ", hotelName=" + hotelName + ", phoneNumber=" + phoneNumber + ", address=" + address + '}';
    }

    public void display() {
        System.out.printf("|%-6s|%-25s|%-14s|%-25s|\n", hotelID, hotelName, phoneNumber, address);
    }
    
}
