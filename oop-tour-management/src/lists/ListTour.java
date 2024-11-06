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
public class ListTour implements IManager<Tour> {
    public static ListTour instance; // đây là Singleton Pattern của nghĩa đó, biến cái cái ListTour thành chứ không phải là 1 cái class
                                    // ngoài giống bữa nghĩa thiết kế
    private int existedTour;
    private Tour tourList[];
    private final String header = String.format("|%-6s|%-10s|%-10s|%-10s|%-10s|%-10s|%-5s|%-15s|%-5s|%-5s|",
            "ID", "NAME", "DESTINATION", "DEPARTURE LOCAL", "VEHICLE ID", "PRICE", "QUANTITY", "COUNTRY", "VISA", "DISCOUNT");

    private ListTour() {
        tourList = new Tour[0];
        existedTour = 0;
        ReadData(new LoadDataFromFile("Files/Tours.dat"));
    }
    
    public static ListTour getInstance() {  // nghĩa có thể đọc thêm về cách implmemnt cái này, có rất nhiều cách, nhưng fat sử dụng '
                                            // cách lazy intialization...
        if(instance == null){
           instance = new ListTour();
        }
        return instance;
    }

    @Override
    public void add() {
        Menu menuAdd = new Menu("Choose type of tour");
        menuAdd.addNewOption("1. Demestic Tour");
        menuAdd.addNewOption("2. International Tour");
        menuAdd.printMenu();
        int choice = menuAdd.getChoice();
        Tour tour = null;
        if (choice == 1) {
            tour = new DomesticTour();
        }
        if (choice == 2) {
            tour = new InternationalTour();
        }
        tour.input();
        tourList = Arrays.copyOf(tourList, tourList.length + 1);
        tourList[existedTour++] = tour;
        System.out.println("The tour has been add successully");
    }

    public String getUniqueTourID() { // viết cái này để lấy ra id duy nhất không bị trùng lặp để sử dụng bên Tour
        int isDuplicate;
        String id;
        do {
            id = MyUtil.getId("ENTER TOUR ID(VD: TO1234): ", "ERROR", "TO\\d{4}$");
            isDuplicate = searchById(id);
            if(isDuplicate >= 0){ 
                System.out.println("Id is duplicated. Please input another id");
            }
        } while (isDuplicate >= 0);
        return id;
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
                    int quantity = MyUtil.getAnInteger("ENTER QUANTITY: ", "ERROR");
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
                    int quantity = MyUtil.getAnInteger("ENTER QUANTITY: ", "ERROR");
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
        for (int i = 0; i < existedTour - 1; i++) {
            for (int j = i + 1; j < existedTour; j++) {
                if (tourList[i].getTourID().compareToIgnoreCase(tourList[j].getTourID()) > 0) {
                    Tour temp = tourList[i];
                    tourList[i] = tourList[j];
                    tourList[j] = temp;
                }
            }
        }
        System.out.println(header);
        for (int i = 0; i < existedTour; i++) {
            tourList[i].showInfor();
        }
    }

    @Override
    public void searchById() {
        if (existedTour == 0) {
                System.out.println("NO MORE THAN A TOUR IN THE LIST");
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
        if (existedTour == 0) 
            return -1;
        for (int i = 0; i < existedTour; i++) 
                if (tourList[i].getTourID().equalsIgnoreCase(id)) 
                    return i;
        return -1;
        
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
        Object[] object = loadData.read();
        if(object == null){
            System.out.println("kh co du lieu");
            return;
        }
        for (Object o : object) {
            tourList = Arrays.copyOf(tourList, tourList.length + 1);
            tourList[existedTour++] = (Tour)o;
        }
            
    }

    @Override
    public void saveToDate(SaveData saveData) {
       saveData.save(tourList);
    }
    public static void main(String[] args) {
        ListTour l = ListTour.getInstance();
//        l.add();
//        l.add();
        l.printListAscendingById();
 //       l.saveToDate(new SaveDataToFile("Files/Tours.dat"));
    }
}
