package lists;

import IOFile.LoadDataFromFile;
import IOFile.SaveDataToFile;
import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;
import java.util.Arrays;
import java.util.Scanner;
import model.Hotel;
import util.MyUtil;

public class HotelList implements IManager<Hotel> {

    private static HotelList instance;
    private int existedHotel;
    private Hotel[] hotelList;
    private final String header = String.format("|%-9s|%-25s|%-14s|%-25s|", "hotelID", "hotelName", "phoneNumber", "address");
    private Scanner sc = new Scanner(System.in);

    private HotelList() {
        hotelList = new Hotel[0];
        existedHotel = 0;
        ReadData(new LoadDataFromFile("Files/Hotels.dat"));
    }

    public static HotelList getHotelList() {
        if (instance == null) {
            instance = new HotelList();
        }
        return instance;
    }

    public String getHotelId() {
        String id;
        int check;
        do {
            printListAscendingById();
            id = MyUtil.getId("Please input the hotel Id (HXXX)" + ": ", "The hotel Id id is required ,(HXXX)", "^H[0-9]{3}");
            check = searchById(id);
            if (check < 0) {
                System.out.println("Again!!!");
            }
        } while (check < 0);
        return id;
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

        String hotelID = MyUtil.getId("Please input the hotel Id: ", "!This information is crucial!", "^[h|H]\\d{3}");
        String hotelName = MyUtil.getString("Please input the hotel name: ", "!This information is crucial!");
        String phoneNumber = MyUtil.getString("Please input hotel's phone numebr: ", "!This information is crucial!");
        String address = MyUtil.getString("Please input hotel's address: ", "!This information is crucial!");

        hotelList = Arrays.copyOf(hotelList, hotelList.length + 1);
        hotelList[existedHotel++] = new Hotel(hotelID, hotelName, phoneNumber, address);
        System.out.println("Hotel has been added completely!");
    }

    @Override
    public void update() {
        int n;
        do {
            System.out.println("#1.Update Hotel Name   ");
            System.out.println("#2.Update Phone Number ");
            System.out.println("#3.Update Address      ");
            System.out.println("#4.Exit update function");
            n = MyUtil.getAnInteger("Opt from 1 to 3 different properties to update: ", "Please input a numeber!");
            System.out.println("------------------------------------------------");
            int point; // point to a particular object in the list
            switch (n) {
                case 1:
                    point = MyUtil.getAnInteger("Enter the position of the object you want to update: ", "The position cant be found");
                    if (point > existedHotel) {
                        break;
                    }
                    String newHotelName = MyUtil.getString("Enter new Hotel Name: ", "This information is crucial");
                    hotelList[point].setHotelName(newHotelName);
                    System.out.println("!-CHANGE SUCCESSFULLY-!");
                    hotelList[point].display();
                    System.out.println("            ");
                    break;
                case 2:
                    point = MyUtil.getAnInteger("Enter the position of the object you want to update: ", "The position cant be found");
                    if (point > existedHotel) {
                        break;
                    }
                    String newPhoneNumber = MyUtil.getString("Enter new Hotel New Phone Number: ", "This information is crucial");
                    hotelList[point].setPhoneNumber(newPhoneNumber);
                    System.out.println("!-CHANGE SUCCESSFULLY-!");
                    hotelList[point].display();
                    System.out.println("            ");
                    break;
                case 3:
                    point = MyUtil.getAnInteger("Enter the position of the object you want to update: ", "The position cant be found");
                    if (point > existedHotel) {
                        break;
                    }
                    String newAddress = MyUtil.getString("Enter new Hotel New Address: ", "This information is crucial");
                    hotelList[point].setAddress(newAddress);
                    System.out.println("!-CHANGE SUCCESSFULLY-!");
                    hotelList[point].display();
                    System.out.println("            ");
                    break;
                case 4:
                    break;
                default:
                    throw new AssertionError("The option cant be found!");
            }
        } while (n != 4);
    }

    @Override
    public void remove() {
        String id = MyUtil.getString("Enter the ID you want to find: ", "This information is crucial");
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
        System.out.println(header);
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
                    return 1;
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
        Object[] obj = loadData.read();
        if (obj == null) {
            System.out.println("Not data");
            return;
        }
        for (Object o : obj) {
            hotelList = Arrays.copyOf(hotelList, hotelList.length + 1);
            hotelList[existedHotel++] =(Hotel) o;
        }
    }

    @Override
    public void saveToDate(SaveData saveData) {
        saveData.save(hotelList, header);
    }

    public static void main(String[] args) {

        HotelList l = HotelList.getHotelList();
        l.printListAscendingById();
    }
}
