/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lists;

import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;
import java.util.Arrays;
import model.Customer;
import model.tour.DomesticTour;
import model.tour.InternationalTour;
import model.tour.Tour;
import ui.Menu;
import util.MyUtil;

/**
 *
 * @author nghialam
 */
public class ListTour implements IManager<Tour> {

    private int existedTour;
    private Tour tourList[];
    private final String header = String.format("|%-6s|%-10s|%-10s|%-10s|%-10s|%-10s|%-5s|%-10s|%-5s|%-5s|",
            "ID", "NAME", "DESTINATION", "DEPARTURE LOCAL", "VEHICLE ID", "PRICE", "QUANTITY", "COUNTRY", "VISA", "DISCOUNT");

    public ListTour() {
        tourList = new Tour[1];
        existedTour = 0;
    }

    @Override
    public void add() {

        int choice = choiceKindOfTour();
        String id = enterTourID();

        if (choice == 1) {
            this.addAInternationalTour();
        }
        if (choice == 2) {
            this.addDomesticTour();
        }
    }

    private String enterTourID() {
        int isDuplicate;
        String id;
        do {
            System.out.println("ENTER YOUR ID: ");
            id = MyUtil.getId("ENTER YOUR ID(VD: TO1234): ", "ERROR", "TO\\d{4}$");
            isDuplicate = searchById(id);
        } while (isDuplicate >= 0);
        return id;
    }

    private int choiceKindOfTour() {
        System.out.println("1. International Tour");
        System.out.println("2. Dometics Tour");
        return MyUtil.getAnInteger("Enter Your CHOICE: ", "Error", 1, 2);

    }

    private void addAInternationalTour() {
        System.out.println(header);
        String tourName = MyUtil.getString("ENTER YOUR TOUR NAME: ", "ERROR");
        String destination = MyUtil.getString("ENTER YOUR DESTINATION: ", "ERROR");
        String departureLocation = MyUtil.getString("ENTER YOUR DEPARTURE LOCATION: ", "ERROR");
        String vehicleID = MyUtil.getString("ENTER YOUR VEHICLE ID: ", "ERROR"); // cần tìm 1 id phù hợp bên vehicle
        int price = MyUtil.getAnInteger("ENTER YOUR PRICE: ", "ERROR");
        String quantity = MyUtil.getString("ENTER YOUR QUATITY: ", "ERROR"); // đề xuất để chọn 1 -> 5 s

        String country = MyUtil.getString("ENTER YOUR COUNTRY: ", "ERROR");
        boolean visaRequired;

        while (true) {
            System.out.println("VISA REQUIRED: TRUE OR FALSE");
            String s = MyUtil.getString("ENTER: ", "ERROR");
            if (s.isEmpty() || s.length() == 0 || s.compareToIgnoreCase("true") != 0 || s.compareToIgnoreCase("false") != 0) {
                System.out.println("ERROR");
            } else {
                visaRequired = Boolean.parseBoolean(s);
                break;
            }
        }
        tourList = Arrays.copyOf(tourList, tourList.length + 1);
        tourList[existedTour++] = new InternationalTour(country, visaRequired, tourName, tourName, destination, departureLocation, vehicleID, price, quantity);
    }

    private void addDomesticTour() {
        System.out.println(header);
        String tourName = MyUtil.getString("ENTER YOUR TOUR NAME: ", "ERROR");
        String destination = MyUtil.getString("ENTER YOUR DESTINATION: ", "ERROR");
        String departureLocation = MyUtil.getString("ENTER YOUR DEPARTURE LOCATION: ", "ERROR");
        String vehicleID = MyUtil.getString("ENTER YOUR VEHICLE ID: ", "ERROR"); // cần tìm 1 id phù hợp bên vehicle
        int price = MyUtil.getAnInteger("ENTER YOUR PRICE: ", "ERROR");
        String quantity = MyUtil.getString("ENTER YOUR QUATITY: ", "ERROR"); // đề xuất để chọn 1 -> 5 s

        double localDiscount = MyUtil.getAnDouble("ENTER YOU LOCAL DISCOUNT: ", "ERROR");

        tourList = Arrays.copyOf(tourList, tourList.length + 1);
        tourList[existedTour++] = new DomesticTour(localDiscount, tourName, tourName, destination, departureLocation, vehicleID, price, quantity);
    }

    @Override
    public void update() {
        String id = MyUtil.getId("ENTER YOUR ID(VD: TO1234): ", "ERROR", "TO\\d{4}$");
        Tour tour = searchObjectById(id);

        if (tour == null) {
            System.out.println("NOT FIND!!!!");
            return;
        }
        System.out.println(header);
        tour.showInfor();
        if (tour instanceof InternationalTour) {
            InternationalTour tour1 = (InternationalTour) tour;
            this.updateITour(tour1);

        } else if (tour instanceof DomesticTour) {
            DomesticTour tour1 = (DomesticTour) tour;
            this.updateDTour(tour1);
        }
    }

    private void updateITour(InternationalTour tour) {
        int choice;
        do {
            this.showMenuITour();
            choice = MyUtil.getAnInteger("ENTER YOUR CHOICE", "ERROR");

            switch (choice) {

                case 1:
                    String tourName = MyUtil.getString("ENTER TOUR NAME: ", "ERROR");
                    tour.setTourName(tourName);
                    break;

                case 2:
                    String destination = MyUtil.getString("ENTER DESTINATION: ", "ERROR");
                    tour.setDestination(destination);
                    break;

                case 3:
                    String departureLocation = MyUtil.getString("ENTER DEPARTURE LOCATION: ", "ERROR");
                    tour.setDepartureLocation(departureLocation);
                    break;

                case 4:
                    int price = MyUtil.getAnInteger("ENTER PRICE: ", "ERROR");
                    tour.setPrice(price);
                    break;

                case 5:
                    String quantity = MyUtil.getString("ENTER QUANTITY: ", "ERROR");
                    tour.setQuantity(quantity);
                    break;

                case 6:
                    String country = MyUtil.getString("ENTER COUNTRY", "ERROR");
                    tour.setCountry(country);
                    break;

                case 7:
                    boolean visaRequired;
                    while (true) {
                        System.out.println("VISA REQUIRED: TRUE OR FALSE");
                        String s = MyUtil.getString("ENTER: ", "ERROR");
                        if (s.isEmpty() || s.length() == 0 || s.compareToIgnoreCase("true") != 0 || s.compareToIgnoreCase("false") != 0) {
                            System.out.println("ERROR");
                        } else {
                            visaRequired = Boolean.parseBoolean(s);
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
        } while (choice <= 7 && choice >= 1);
    }

    private void showMenuITour() {
        Menu updateMenu = new Menu("Update tour");
        updateMenu.addNewOption("1. Update new tour name");
        updateMenu.addNewOption("2. Update new destination");
        updateMenu.addNewOption("3. Update new departureLocation");
        updateMenu.addNewOption("4. Update new price");
        updateMenu.addNewOption("5. Update new quantity");
        updateMenu.addNewOption("6. Update new country");
        updateMenu.addNewOption("7. Update new visaRequired");

        updateMenu.addNewOption("8. Exit");
    }

    private void updateDTour(DomesticTour tour) {
        int choice;
        do {
            this.showMenuDTour();
            choice = MyUtil.getAnInteger("ENTER YOUR CHOICE", "ERROR");

            switch (choice) {

                case 1:
                    String tourName = MyUtil.getString("ENTER TOUR NAME: ", "ERROR");
                    tour.setTourName(tourName);
                    break;

                case 2:
                    String destination = MyUtil.getString("ENTER DESTINATION: ", "ERROR");
                    tour.setDestination(destination);
                    break;

                case 3:
                    String departureLocation = MyUtil.getString("ENTER DEPARTURE LOCATION: ", "ERROR");
                    tour.setDepartureLocation(departureLocation);
                    break;

                case 4:
                    int price = MyUtil.getAnInteger("ENTER PRICE: ", "ERROR");
                    tour.setPrice(price);
                    break;

                case 5:
                    String quantity = MyUtil.getString("ENTER QUANTITY: ", "ERROR");
                    tour.setQuantity(quantity);
                    break;

                case 6:
                    double localDiscount = MyUtil.getAnDouble("ENTER LOCAL DISCOUNT: ", "ERROR");
                    tour.setLocalDiscount(localDiscount);
                    break;

                default:
                    break;
            }
        } while (choice <= 7 && choice >= 1);
    }

    private void showMenuDTour() {
        Menu updateMenu = new Menu("Update tour");
        updateMenu.addNewOption("1. Update new tour name");
        updateMenu.addNewOption("2. Update new destination");
        updateMenu.addNewOption("3. Update new departureLocation");
        updateMenu.addNewOption("4. Update new price");
        updateMenu.addNewOption("5. Update new quantity");
        updateMenu.addNewOption("6. Update new localDiscount");

        updateMenu.addNewOption("7. Exit");
    }

    @Override
    public void remove() {
        String id = MyUtil.getId("ENTER YOUR ID(VD: TO1234): ", "ERROR", "TO\\d{4}$");
        Tour tour = searchObjectById(id);
        if (tour == null) {
            System.out.println("SR CANT FIND");
        }
        for (int i = searchById(id); i < existedTour; i++) {
            tourList[i] = tourList[i + 1];
        }
        tourList = Arrays.copyOf(tourList, tourList.length - 1);
        existedTour--;
    }

    @Override
    public void printListAscendingById() {
        if (existedTour == 0) {
            System.out.println("NO MORE THAN A TOUR IN LIST");
            return;
        }
        for (int i = 0; i < existedTour; i++) {
            for (int j = i + 1; j > existedTour - 1; j++) {
                if (tourList[i].getTourID().compareToIgnoreCase(tourList[j].getTourID()) > 0) {
                    Tour tour = tourList[i];
                    tourList[i] = tourList[j];
                    tourList[j] = tour;
                }
            }
        }

        for (int i = 0; i < existedTour; i++) {
            tourList[i].showInfor();
        }
    }

    @Override
    public void searchById() {
        if (existedTour == 0) {
            System.out.println("NO MORE THAN A TOUR IN THE LIST");;
        } else {
            String id = MyUtil.getString("ENTER YOUR ID: ", "SORRY, ERROR");
            for (int i = 0; i < existedTour; i++) {
                if (tourList[i].getTourID().compareToIgnoreCase(id) == 0) {
                    tourList[i].showInfor();
                    return;
                }
            }
        }
    }

    @Override
    public int searchById(String id) {
        if (existedTour == 0) {
            return -1;
        } else {
            for (int i = 0; i < existedTour; i++) {
                if (tourList[i].getTourID().compareToIgnoreCase(id) == 0) {
                    return i;
                }
            }
            return -1;
        }
    }

    @Override
    public Tour searchObjectById(String id) {
        if (existedTour == 0) {
            return null;
        } else {
            for (int i = 0; i < existedTour; i++) {
                if (tourList[i].getTourID().compareToIgnoreCase(id) == 0) {
                    return tourList[i];
                }
            }
            return null;
        }
    }

    @Override
    public void ReadData(LoadData loadData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void saveToDate(SaveData saveData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//    public static void main(String[] args) {
//        ListTour l = new ListTour();
//        l.add();
//    }
}
