/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lists;

import IOFile.LoadDataFromFile;
import IOFile.SaveDataToFile;
import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;
import java.util.Arrays;
import model.tour.DomesticTour;
import model.tour.InternationalTour;
import model.tour.Tour;
import ui.Menu;
import util.MyUtil;

/**
 *
 * @author nghialam
 */
public class TourList implements IManager<Tour> {

    private static TourList instance;
    private int existedTour;
    private Tour tourList[];
    private final String header = String.format("|%-6s|%-10s|%-10s|%-10s|%-10s|%-10s|%-5s|%-10s|%-5s|%-5s|",
            "ID", "NAME", "DESTINATION", "DEPARTURE LOCAL", "VEHICLE ID", "PRICE", "QUANTITY", "COUNTRY", "VISA", "DISCOUNT");

    private TourList() {
        tourList = new Tour[0];
        existedTour = 0;
    }

    public static TourList getInstance() {
        if (instance == null) {
            instance = new TourList();
        }
        return instance;
    }

    @Override
    public void add() {

        int choice = choiceKindOfTour();
//        String id = enterTourID();
//        System.out.println("List don't contain " + id);
        System.out.println("---------------------------------------------");
        if (choice == 1) {
            this.addAInternationalTour();
        }
        if (choice == 2) {
            this.addDomesticTour();
        }

    }

    @Override
    public void update() {
        System.out.println("-------------------------------------------------------");
        printListAscendingById();
        String id = MyUtil.getId("enter ID(VD: TO123): ", "format is incorect", "TO\\d{3}$");
        Tour tour = searchObjectById(id);
        Menu menuTour;
        if (tour == null) {
            System.out.println("sorry,I can't tourID from list!!!!");
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

    @Override
    public void remove() {
        System.out.println("--------------------------------------------------------");
        printListAscendingById();

        String id = MyUtil.getId("Enter ID(VD: TO123): ", "format is incorect", "TO\\d{3}$");
        Tour tour = searchObjectById(id);
        if (tour == null) {
            System.out.println("Sr can't find tourID");
            return;
        }

        for (int i = searchById(id); i < existedTour - 1; i++) {
            tourList[i] = tourList[i + 1];
        }
        tourList = Arrays.copyOf(tourList, tourList.length - 1);
        existedTour--;
        System.out.println("The process of removal is successful.");
    }

    @Override
    public void printListAscendingById() {
        if (existedTour == 0) {
            System.out.println("no more than a tour in list");
            return;
        }
        for (int i = 0; i < existedTour; i++) {
            for (int j = i + 1; j < existedTour - 1; j++) {
                if (tourList[i].getTourID().compareToIgnoreCase(tourList[j].getTourID()) < 0) {
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
            System.out.println("no more than a tour in list");;
        } else {
            String id = MyUtil.getId("ENTER ID(VD: TO123): ", "format is incorect", "TO\\d{3}$");
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
        Object[] obj = loadData.read();
        if (obj == null) {
            System.out.println("kh co du lieu");
            return;
        }
        for (Object o : obj) {
            tourList = Arrays.copyOf(tourList, tourList.length + 1);
            tourList[existedTour++] = (Tour) o;
        }
    }

    @Override
    public void saveToDate(SaveData saveData) {
        saveData.save(tourList);
    }

    public String enterTourID() {
        int isDuplicate;
        String id;
        do {
            id = MyUtil.getId("enter ID(VD: TO123): ", "format is incorect", "TO\\d{3}$");
            isDuplicate = searchById(id);

            if (isDuplicate >= 0) {
                System.out.println("ID is dulicated!!!");
            }

        } while (isDuplicate >= 0);
        return id;
    }

    private int choiceKindOfTour() {
        System.out.println("1. International Tour");
        System.out.println("2. Dometics Tour");
        return MyUtil.getAnInteger("Enter Your CHOICE: ", "The input is of type integer!!!", 1, 2);

    }

    private void addAInternationalTour() {
        System.out.println(header);
        Tour tourTemp = new InternationalTour();
        tourTemp.input();
        tourList = Arrays.copyOf(tourList, tourList.length + 1);
        tourList[existedTour++] = tourTemp;
    }

    private void addDomesticTour() {
        System.out.println(header);
        Tour tourTemp = new DomesticTour();
        tourTemp.input();
        tourList = Arrays.copyOf(tourList, tourList.length + 1);
        tourList[existedTour++] = tourTemp;
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
        updateMenu.printMenu();
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
        updateMenu.printMenu();
    }

    private void updateITour(InternationalTour tour) {
        int choice;
        do {
            this.showMenuITour();
            choice = MyUtil.getAnInteger("Enter your choice: ", "the input is kind of INTEGER!!!");

            switch (choice) {

                case 1:
                    String tourName = MyUtil.getString("Enter tour name: ", "the input is kind of STRING!!!");
                    tour.setTourName(tourName);
                    break;

                case 2:
                    String destination = MyUtil.getString("Enter destination: ", "the input is kind of STRING");
                    tour.setDestination(destination);
                    break;

                case 3:
                    String departureLocation = MyUtil.getString("Enter departure location: ", "the input is kind of STRING");
                    tour.setDepartureLocation(departureLocation);
                    break;

                case 4:
                    int price = MyUtil.getAnInteger("Enter price: ", "the input is kind of INTEGER");
                    tour.setPrice(price);
                    break;

                case 5:
                    int quantity = MyUtil.getAnInteger("Enter quantity: ", "The input is kind of integer (1->50)", 1, 50);
                    tour.setQuantity(quantity);
                    break;

                case 6:
                    String country = MyUtil.getString("Enter country", "the input is kind of STRINGx ");
                    tour.setCountry(country);
                    break;

                case 7:
                    boolean visaRequired;
                    System.out.println("Visa required: YES or NO");
                    String s = MyUtil.getString("Enter: ", "Struture of data is String and 'YES' or 'NO' ");
                    while (true) {
                        if (s.compareToIgnoreCase("YES") != 0 || s.compareToIgnoreCase("NO") != 0) {
                            visaRequired = Boolean.parseBoolean(s);
                            break;
                        } else {
                            System.out.println("You must enter YES or NO");
                            s = MyUtil.getString("Enter: ", "Struture of data is String 'YES' or 'NO'");
                        }
                    }
                    break;
                default:
                    break;
            }
        } while (choice <= 7 && choice >= 1);
    }

    private void updateDTour(DomesticTour tour) {
        int choice;
        do {
            this.showMenuDTour();
            choice = MyUtil.getAnInteger("Enter your CHOICE: ", "the input is kind of INTEGER!!!");

            switch (choice) {

                case 1:
                    String tourName = MyUtil.getString("Enter tour name: ", "the input is kind of STRING!!!");
                    tour.setTourName(tourName);
                    break;

                case 2:
                    String destination = MyUtil.getString("Enter destination: ", "the input is kind of STRING");
                    tour.setDestination(destination);
                    break;

                case 3:
                    String departureLocation = MyUtil.getString("Enter departure location: ", "the input is kind of STRING");
                    tour.setDepartureLocation(departureLocation);
                    break;

                case 4:
                    int price = MyUtil.getAnInteger("Enter price: ", "the input is kind of INTEGER");
                    tour.setPrice(price);
                    break;

                case 5:
                    int quantity = MyUtil.getAnInteger("Enter quantity: ", "The input is kind of integer (1->50)", 1, 50);
                    tour.setQuantity(quantity);
                    break;

                case 6:
                    double localDiscount = MyUtil.getAnDouble("Enter local discount: ", "The input is kind of DOUBLE");
                    tour.setLocalDiscount(localDiscount);
                    break;

                default:
                    break;
            }
        } while (choice <= 7 && choice >= 1);
    }

    public static void main(String[] args) {
        TourList l = TourList.getInstance();
        l.ReadData(new LoadDataFromFile("Files/Tours.dat"));
        //l.printListAscendingById();
        //l.add();
        //l.add();
        //l.saveToDate(new SaveDataToFile("Files/Tours.dat"));
        //l.printListAscendingById();
        //l.remove();
        l.update();
        l.printListAscendingById();
    }
}
