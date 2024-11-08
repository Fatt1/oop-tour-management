package lists;

import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;
import java.util.Arrays;
import java.util.Scanner;
import model.Restaurant;
import util.MyUtil;

public class RestaurantList implements IManager<Restaurant> {

    private Restaurant[] restaurantList;
    private int existedRestaurant;
    private Scanner sc = new Scanner(System.in);

    public RestaurantList() {
        restaurantList = new Restaurant[0];
        existedRestaurant = 0;
    }

    @Override
    public void add() {
        String id;
        int duplicated;
        do {
            id = MyUtil.getId("Input the Restaurant ID (RXXX): ", "The format of ID is (RXXX)" + "X stands for digit(0-9)", "^[r|R]\\d{3}");
            duplicated = searchById(id);
            if (duplicated >= 0) {
                System.out.println("Id is duplicated, Please enter anorther ID!");
            }
        } while (duplicated >= 0);
        String restaurantName = MyUtil.getString("Please input the restaurantName: ", "!This information is crucial!");
        String phoneNumber = MyUtil.getString("Please input restaurant's phone numebr: ", "!This information is crucial!");
        String address = MyUtil.getString("Please input restaurant's address: ", "!This information is crucial!");

        restaurantList = Arrays.copyOf(restaurantList, restaurantList.length + 1);
        restaurantList[existedRestaurant++] = new Restaurant(id, restaurantName, phoneNumber, address);
        System.out.println("The Restaurant has been added completely!");
    }

    @Override
    public void update() {
        String id = MyUtil.getString("Enter the id you want to update(RXX): ", "This information is crucial!");
        Restaurant x = searchObjectById(id);
        if (x == null) {
            System.out.println("Not Found!");
            return;
        }

        int choice;
        do {
            System.out.println("#1.Update Restaurant Name   ");
            System.out.println("#2.Update Phone Number ");
            System.out.println("#3.Update Address      ");
            System.out.println("#4.Exit update function");
            choice = MyUtil.getAnInteger("Opt from 1 to 3 different properties to update: ", "Please input a numeber!");
            System.out.println("------------------------------------------------");
            switch (choice) {
                case 1:
                    String newRestaurantName = MyUtil.getString("Enter new Restaurant Name: ", "This information is crucial");
                    x.setRestaurantName(newRestaurantName);
                    break;
                case 2:
                    String newPhoneNumber = MyUtil.getString("Enter Restaurant New Phone Number: ", "This information is crucial");
                    x.setPhoneNumber(newPhoneNumber);

                    break;
                case 3:
                    String newAddress = MyUtil.getString("Enter new Restaurant New Address: ", "This information is crucial");
                    x.setAddress(newAddress);
                    break;
                case 4:
                    break;
                default:
                    throw new AssertionError("The option cant be found!");
            }
            if (choice > 0 && choice < 4) {
                x.display();
                System.out.println("!-CHANGE SUCCESSFULLY-!");
                System.out.println("");
            }
        } while (choice != 4);
    }

    @Override
    public void remove() {
        String id = MyUtil.getString("Enter the ID you want to Remove: ", "This information is crucial");
        int point = searchById(id);
        if (point < 0) {
            System.out.println("Not Found!");
        }
        String choice;
        do {
            System.out.print("Choose Y/N: ");
            choice = sc.nextLine().toUpperCase().trim();
            if (choice.equalsIgnoreCase("Y")) {
                for (int i = 0; i < existedRestaurant - 1; i++) {
                    restaurantList[i] = restaurantList[i + 1];

                    restaurantList = Arrays.copyOf(restaurantList, restaurantList.length - 1);
                    existedRestaurant--;
                    System.out.println("!-THE RESTAURANT HAS SUCCESSFULLY REMOVED-!");
                    return;
                }
            } else if (choice.equalsIgnoreCase("N")) {
                return;
            } else {
                System.out.println("Please choose Y/N");
            }

        } while (true);
    }

    @Override
    public void printListAscendingById() {
        for (int i = 0; i < existedRestaurant - 1; i++) {
            for (int j = i + 1; j < existedRestaurant; j++) {
                if (restaurantList[i].getRestaurantID().compareTo(restaurantList[j].getRestaurantID()) == 1) {
                    Restaurant R1 = restaurantList[i];
                    restaurantList[i] = restaurantList[j];
                    restaurantList[j] = R1;
                }
            }
        }

        for (int i = 0; i < existedRestaurant; i++) {
            restaurantList[i].display();
        }
    }

    @Override
    public void searchById() {
        String id = MyUtil.getString("PLease input the ID you want to find: ", "This information is crucial!");
        Restaurant r2 = searchObjectById(id); // tra ve toa do
        if (r2 == null) {
            System.out.println("NOT FOUND!");
            return;
        }
       r2.display();
    }

    @Override
    public int searchById(String id) {
        if (restaurantList.length == 0) {
            return -1;
        }
        for (int i = 0; i < existedRestaurant; i++) {
            if (restaurantList[i].getRestaurantID().equalsIgnoreCase(id)) {
                return 1;
            }
        }
        return -1;
    }

    @Override
    public Restaurant searchObjectById(String id) {
        if (restaurantList.length == 0) {
            System.out.println("The List is empty");
            return null;
        }
        for (Restaurant r : restaurantList) {
            if (r.getRestaurantID().equalsIgnoreCase(id)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public void ReadData(LoadData loadData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void saveToDate(SaveData saveData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        RestaurantList rl = new RestaurantList();
        rl.add();
        rl.add();
        rl.printListAscendingById();
        rl.update();
        rl.remove();
        rl.searchById();
        rl.printListAscendingById();
    }

}
