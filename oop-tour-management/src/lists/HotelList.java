package lists;

import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;
import java.util.Scanner;
import model.Hotel;
import util.MyUtil;

public class HotelList implements IManager<Hotel> {

    private Hotel[] hotelList;
    private int existedHotel;

    public HotelList() {
        hotelList = new Hotel[200];
        existedHotel = 0;
    }
    
    Scanner sc = new Scanner(System.in);
    @Override

    public void add() {
        String hotelID = MyUtil.getId("Please input the hotel Id: ", "!This information is crucial!", "^[h|H]\\d{3}");
        String hotelName = MyUtil.getString("Please input the hotel name: ", "!This information is crucial!");
        String phoneNumber = MyUtil.getString("Please input hotel's phone numebr: ", "!This information is crucial!");
        String address = MyUtil.getString("Please input hotel's address: ", "!This information is crucial!");

        hotelList[existedHotel] = new Hotel(hotelID, hotelName, phoneNumber, address);
        existedHotel++;
    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {
    }

    @Override
    public void printListAscendingById() {
        for (int i = 0; i < existedHotel - 1; i++) {
            for (int j = i+1; j < existedHotel; j++) {
                if (hotelList[i].getHotelID().compareTo(hotelList[j].getHotelID()) > 0) {
                     Hotel HH1 = hotelList[i];
                     hotelList[i] =  hotelList[j];
                     hotelList[j] = HH1;
                }
            }
        }
        for (int i = 0; i < existedHotel; i++) {
            hotelList[i].display();
        }
    }

    @Override
    public void searchById() {
        System.out.print("Enter the id: ");
        String newID; 
        
        for (int i = 0; i < existedHotel; i++) {
            if(hotelList[i].getHotelID().equalsIgnoreCase(newID) == )
        }
    }

    @Override
    public void searchById(String id) {
    }

    @Override
    public Hotel searchObjectById(String id) {
        return null;
    }

    @Override
    public void ReadData(LoadData loadData) {
    }

    @Override
    public void saveToDate(SaveData saveData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
