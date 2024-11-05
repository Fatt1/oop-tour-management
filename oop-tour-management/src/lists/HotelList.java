package lists;

import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import model.Hotel;
import util.MyUtil;

public class HotelList implements IManager<Hotel> {

    private Hotel[] hotelList;
    private int existedHotel;
    private Scanner sc = new Scanner(System.in);

    public HotelList() {
        hotelList = new Hotel[0];
        existedHotel = 0;
    }

    @Override

    public void add() {
        String id;
        int duplicatedId;
        do {
            id = MyUtil.getId("Input hotel id (HXXX): ", "The format of Id is (HXXX) "
                    + "X stands for digit(0-9)", "^[h|H]\\d{3}$");
            duplicatedId = searchById(id);
            if (duplicatedId >= 0) {
                System.out.println("ID is duplicated. Please input another id!");
            }
        } while (duplicatedId >= 0);
        String hotelName = MyUtil.getString("Please input the hotel name: ", "!This information is crucial!");
        String phoneNumber = MyUtil.getString("Please input hotel's phone numebr: ", "!This information is crucial!");
        String address = MyUtil.getString("Please input hotel's address: ", "!This information is crucial!");

        hotelList = Arrays.copyOf(hotelList, hotelList.length + 1);
        hotelList[existedHotel++] = new Hotel(id, hotelName, phoneNumber, address);
        System.out.println("Hotel has been added completely!");
    }

    @Override
    public void update() {
        int choice;
        String id = MyUtil.getString("Enter the id you want to find(Hxxx)", "This information is crucial");
        Hotel h1 = searchObjectById(id);
        if (h1 == null) {
            System.out.println("Not Found!");
            return;
        }

        do {
            System.out.println("#1.Update Hotel Name   ");
            System.out.println("#2.Update Phone Number ");
            System.out.println("#3.Update Address      ");
            System.out.println("#4.Exit update function");
            choice = MyUtil.getAnInteger("Opt from 1 to 3 different properties to update: ", "Please input a numeber!");
            System.out.println("------------------------------------------------");
            int point; // point to a particular object in the list
            switch (choice) {
                case 1:
                    String newHotelName = MyUtil.getString("Enter new Hotel Name: ", "This information is crucial");
                    h1.setHotelName(newHotelName);
                    break;
                case 2:
                    String newPhoneNumber = MyUtil.getString("Enter new Hotel New Phone Number: ", "This information is crucial");
                    h1.setPhoneNumber(newPhoneNumber);
                    break;
                case 3:
                    String newAddress = MyUtil.getString("Enter new Hotel New Address: ", "This information is crucial");
                    h1.setAddress(newAddress);
                    break;
                case 4:
                    break;
                default:
                    throw new AssertionError("The option cant be found!");
            }
            if (choice > 0 && choice < 4) {
                System.out.println("!-CHANGE SUCCESSFULLY-!");
                h1.display();
                System.out.println("");
            }
        } while (choice != 4);
    }

    @Override
    public void remove() {
        String id = MyUtil.getString("Enter the HotelID you want to remove(Hxxx): ", "This information is crucial");
        int position = searchById(id);
        if (position < 0) {
            System.out.println("Not Found!");
            return;
        }
        String choice;
        do {
            System.out.print("Choose Y/N: ");
            choice = sc.nextLine().toLowerCase().trim();
            if (choice.equalsIgnoreCase("Y")) {
                for (int i = position; i < existedHotel - 1; i++) {
                    hotelList[i] = hotelList[i + 1];
                }

                hotelList = Arrays.copyOf(hotelList, hotelList.length - 1); // update the list length again

                existedHotel--;
                System.out.println("!-THE HOTEL HAS SUCCESSFULLY REMOVED-!");
                return;
            } else if (choice.equalsIgnoreCase("N")) {
                return;
            } else {
                System.out.println("Please choose Y/N");
            }
        } while (true);
    }

    @Override
    public void printListAscendingById() {
        for (int i = 0; i < existedHotel - 1; i++) {
            for (int j = i + 1; j < existedHotel; j++) {
                if (hotelList[i].getHotelID().compareTo(hotelList[j].getHotelID()) > 0) {
                    Hotel HH1 = hotelList[i];
                    hotelList[i] = hotelList[j];
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
        String newID = MyUtil.getString("Enter the ID: ", "The ID is required");
        for (int i = 0; i < existedHotel; i++) {
            if (hotelList[i].getHotelID().equalsIgnoreCase(newID) == true) {
                hotelList[i].display();
            }
        }
    }

    @Override
    public int searchById(String id) {
        if (hotelList.length == 0) {
            return -1;
        } else {
            for (int i = 0; i < existedHotel; i++) {
                if (hotelList[i].getHotelID().equalsIgnoreCase(id)) {
                    return i;
                }
            }
            return -1;
        }
    }

    @Override
    public Hotel searchObjectById(String id) {
        if (hotelList.length == 0) {
            System.out.println("Not Found!");
            return null;
        }
        for (Hotel x : hotelList) {
            if (x.getHotelID().equalsIgnoreCase(id)) {
                return x;
            }
        }
        return null;
    }

    @Override
    public void ReadData(LoadData loadData) {
    }

    @Override
    public void saveToDate(SaveData saveData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        HotelList hh1 = new HotelList();
        hh1.add();
        hh1.add();
        hh1.printListAscendingById();
        hh1.update();
        hh1.remove();
        hh1.printListAscendingById();
    }
}
