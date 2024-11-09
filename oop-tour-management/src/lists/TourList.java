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
import java.util.Scanner;
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
    private final String header = String.format("|%-18s|%-5s|%-30s|%-20s|%-20s|%-5s|%-10s|%-2s|%-15s|%-5s|%-5s|",
            "Tour", "ID", "NAME", "DESTINATION", "DEPARTURE LOCAL", "VEHICLE ID", "PRICE", "QUANTITY", "COUNTRY", "VISA", "DISCOUNT");

    private TourList() {
        tourList = new Tour[0];
        existedTour = 0;
        ReadData(new LoadDataFromFile("Files/Tours.dat"));
    }

    public static TourList getInstance() {
        if (instance == null) {
            instance = new TourList();
        }
        return instance;
    }
    
    
    @Override
    public void add() {
        Tour tourTemp;
        int choice = choiceKindOfTour();
        System.out.println("---------------------------------------------");
        System.out.println(header);

        if (choice == 1) {
            tourTemp = new InternationalTour();
        } else {
            tourTemp = new DomesticTour();
        }
        String idTour = enterTourID();
        tourTemp.input(idTour);
        tourTemp.input();
        tourList = Arrays.copyOf(tourList, tourList.length + 1);
        tourList[existedTour++] = tourTemp;

    }
    
    
    public String enterTourID() {
        int isDuplicate;
        String id;
        do {
            id = MyUtil.getId("Enter ID(VD: TO123): ", "Format is incorect", "TO\\d{3}$");
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
    
    @Override
    public void update() {
        System.out.println("-------------------------------------------------------");
        printListAscendingById();
        System.out.println("-------------------------------------------------------");
        String string = MyUtil.getString("Enter ID of tour that you want choose: ", "Not space or enter");
        Tour tour = searchObjectById(string);
        if (tour == null) {
            System.out.println("Sr can't find tour");
            return;
        }

        System.out.println("--------------------------------------------------------");

        Menu menuTour = new Menu("Tour");
        int choice;
        System.out.println(header);
        tour.showInfor();
        tour.updateMenu(menuTour);
        do {
            menuTour.printMenu();
            choice = menuTour.getChoice();
            tour.setData(tour, choice);
            if(choice != 1){
                System.out.println("Tour after updating");
                tour.showInfor();
            }
                
        } while (choice != 1);
    }

    @Override
    public void remove() {
        System.out.println("--------------------------------------------------------");
        printListAscendingById();
        String string = MyUtil.getString("Enter ID of tour that you want choose: ", "Not space or enter");
        Tour tour = searchObjectById(string);

        if (tour == null) {
            System.out.println("Sr can't find tour");
            return;
        }
        System.out.println("--------------------------------------------------------");
        String choice = MyUtil.getValueOrDefault("Are you sure YES/NO: ", "Please input YES/NO");
        if(choice.equalsIgnoreCase("YES")){
             for (int i = searchById(tour.getTourID()); i < existedTour - 1; i++) {
            tourList[i] = tourList[i + 1];
        }
        tourList = Arrays.copyOf(tourList, tourList.length - 1);
        existedTour--;
        System.out.println("The process of removal is successful.");
        }
        
    }

    @Override
    public void printListAscendingById() {
        if (existedTour == 0) {
            System.out.println("No more than a tour in list");
            return;
        }
        for (int i = 0; i < existedTour - 1; i++) {
            for (int j = i + 1; j < existedTour; j++) {
                if (tourList[i].getTourID().compareToIgnoreCase(tourList[j].getTourID()) > 0) {
                    Tour tour = tourList[i];
                    tourList[i] = tourList[j];
                    tourList[j] = tour;
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
        if (existedTour == 0){
            System.out.println("No more than a tour in list");
            return;
        }
        String id = MyUtil.getString("Enter ID(VD: TO123): ", "Don't find ID");
        for (int i = 0; i < existedTour; i++) 
            if (tourList[i].getTourID().compareToIgnoreCase(id) == 0) {
                tourList[i].showInfor();
                return;
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

    public Tour[] searchObjectByName(String name) {

        if (existedTour == 0) {
            return null;
        } else {
            int count = 0;
            Tour[] tour = new Tour[0];
            for (int i = 0; i < existedTour; i++) {
                if (tourList[i].getTourName().contains(name)) {
                    tour = Arrays.copyOf(tour, tour.length + 1);
                    tour[count++] = tourList[i];
                }
            }
            return tour;
        }
    }

    public void searchObjectByName() {
        System.out.println("-------------------------------------------------------");
        String name = MyUtil.getString("Enter tour name to find: ", "the input is not space or enter");
        Tour[] tour = searchObjectByName(name);
        if (tour.length == 0) {
            System.out.println("Dont't find any tour as same as " + name);
        } else {
            System.out.println(header);
            for (int i = 0; i < tour.length; i++)
                tour[i].showInfor();
        }
    }

    @Override
    public void ReadData(LoadData loadData) {
        Object[] obj = loadData.read();
        if (obj == null) {
            System.out.println("Not data");
            return;
        }
        for (Object o : obj) {
            tourList = Arrays.copyOf(tourList, tourList.length + 1);
            tourList[existedTour++] = (Tour) o;
        }
    }

    @Override
    public void saveToDate(SaveData saveData) {
        saveData.save(tourList, header);
    }


    public static void main(String[] args) {
        TourList l = TourList.getInstance();
 //       l.printListAscendingById();
//        l.add();
//       l.add();
//        l.add();
//        l.saveToDate(new SaveDataToFile("Files/Tours.dat"));
//        l.printListAscendingById();
        //l.remove();
        //l.update();
        //l.searchObjectByName();
        l.printListAscendingById();
    }
}
