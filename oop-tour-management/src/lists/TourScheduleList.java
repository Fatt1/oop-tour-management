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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Scanner;
import model.tour.TourSchedule;
import ui.Menu;
import util.MyUtil;

/**
 *
 * @author nghialam
 */
public class TourScheduleList implements IManager<TourSchedule> {

    private static TourScheduleList instance;
    private TourSchedule[] tourScheduleList;
    private int existedTourSchedule;
    private final String header = String.format("|%-20s|%-8s|%-12s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|",
             "TourScheduleID", "TourID", "EmployeeID", "returnDay", "departureDay", "emptySlots", "duration", "adultPrice", "childPrice", "totalPrice");

    private TourScheduleList() {
        tourScheduleList = new TourSchedule[0];
        existedTourSchedule = 0;
        ReadData(new LoadDataFromFile("Files/TourSchedule.dat"));
    }

    public static TourScheduleList getInstance() {
        if (instance == null) {
            return instance = new TourScheduleList();
        }
        return instance;
    }

    @Override
    public void add() {
        TourSchedule tourTemp;
        tourTemp = new TourSchedule();
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("--------------------ADD A SCHEDULE TOUR----------------------------------\n");
        
        tourTemp = enterData(tourTemp);
        
        System.out.println(header);
        tourTemp.showInfor();
        tourScheduleList = Arrays.copyOf(tourScheduleList, tourScheduleList.length + 1);
        tourScheduleList[existedTourSchedule++] = tourTemp;
    }

    @Override
    public void update() {
        int choice;
        System.out.println("-----------------------------------------------------------");
        System.out.println("---------------------------UPDATE--------------------------");
        printListAscendingById();
        Menu menu = new Menu("Tour Schedule");
        menu = addMenuScheduleTour(menu);

        String id = MyUtil.getString("Enter ID: ", "Not Space or Enter");
        if (searchObjectById(id) != null) {
            chooseMenuScheduleTour(searchObjectById(id), menu);
            return;
        }
        System.out.println("Not find  " + id);
    }

    @Override
    public void remove() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("----------------------------REMOVE---------------------------");
        if (existedTourSchedule == 0) {
            System.out.println("List is empty");
            return;
        }
        String id = MyUtil.getString("Enter id: ", "");
        int isCheck = searchById(id);
        if (isCheck == -1) {
            System.out.println("Don't find id!! please again");
            return;
        }
        
        for (int i = isCheck; i < existedTourSchedule - 1; i++) 
            tourScheduleList[i] = tourScheduleList[i + 1];  
        tourScheduleList = Arrays.copyOf(tourScheduleList, tourScheduleList.length - 1);
        existedTourSchedule--;
    }

    @Override
    public void printListAscendingById() {
        if (existedTourSchedule == 0) {
            System.out.println("List is empting");
            return;
        }
        System.out.println(header);
        for (int i = 0; i < existedTourSchedule - 1; i++) 
            for (int j = i + 1; j < existedTourSchedule; j++) 
                if (tourScheduleList[i].getID().compareToIgnoreCase(tourScheduleList[j].getID()) > 0) {
                    TourSchedule temp = tourScheduleList[i];
                    tourScheduleList[i] = tourScheduleList[j];
                    tourScheduleList[j] = temp;
                }           
        
        for (int i = 0; i < existedTourSchedule; i++) 
            tourScheduleList[i].showInfor();     
    }

    @Override
    public void searchById() {
        if (existedTourSchedule == 0) {
            System.out.println("No more than a tour in list");
            return;
        }
        
        String id = MyUtil.getString("Enter ID(VD: TS123): ", "Don't find ID");
        if(searchById(id) >= 0){
            searchObjectById(id).showInfor();
            return;
       }
       System.out.println("Dont find " + id);     
    }

    @Override
    public int searchById(String id) {
        if (existedTourSchedule == 0) 
            return -1;    
        for (int i = 0; i < existedTourSchedule; i++) 
            if (tourScheduleList[i].getID().compareToIgnoreCase(id) == 0) 
                return i;
        return -1;
    }

    @Override
    public TourSchedule searchObjectById(String id) {
        if (existedTourSchedule == 0) 
            return null;
        for (int i = 0; i < existedTourSchedule; i++) 
            if (tourScheduleList[i].getID().compareToIgnoreCase(id) == 0) 
                return tourScheduleList[i];
        return null;
    }
    
    private String enterTourScheduleID() {
        int isCheck;
        String id;
        do {
            id = MyUtil.getId("Enter TourScheduleID(ts123): ", "must be follow format", "TS\\d{3}$");
            isCheck = searchById(id);
            if (isCheck >= 0) 
                System.out.println("Id is duplicated!!!"); 
        } while (isCheck >= 0);
        return id;
    }

    private void chooseMenuScheduleTour(TourSchedule tourSchedule, Menu menu) {
        int choice;
        String id;

        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    tourSchedule.setTourID(TourList.getInstance().getTourId());
                    break;
                case 2:
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Choose EmployeeID from this list");
                    id = MyUtil.getId("Enter Employee ID:", "Not space or Enter and follow format (E123)", "E\\d{3}$");
                    System.out.println("Tinh nang chua hoan thien" + id);
                    break;
                case 3:
                    System.out.println("---------------------------------------------------------");
                    tourSchedule.setReturnDay(MyUtil.getDate("Enter Renturn Day: ", "Follow format is yyyy-mm-dd", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    tourSchedule.showInfor();
                    break;
                case 4:
                    System.out.println("---------------------------------------------------------");
                    tourSchedule.setDepartureDay(MyUtil.getDate("Enter Departure Day: ", "Follow format is yyyy-mm-dd", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    tourSchedule.showInfor();
                    break;
                case 5:
                    System.out.println("---------------------------------------------------------");
                    tourSchedule.setEmptySlots(MyUtil.getAnInteger("Enter Empty Slot (1 <= slot <= 50): ", "The input is INTEGER and limit between 1 and 50", 1, 50));
                    break;
                case 6:
                    System.out.println("OK out update");
                    System.out.println(header);
                    tourSchedule.showInfor();
                    break;
            }
            if(choice != 9){
                System.out.println(header);
                tourSchedule.showInfor();
            }
        } while (choice >= 1 && choice <= 8);

    }

    private Menu addMenuScheduleTour(Menu menu) {
        menu = new Menu("TourSchedule");
        menu.addNewOption("1. Update Tour ID");
        menu.addNewOption("2. Update Employee ID");
        menu.addNewOption("3. Update Return Day");
        menu.addNewOption("4. Update Departure Day");
        menu.addNewOption("5. Update Empty Slots");
        menu.addNewOption("6. Exit!!!");
        return menu;
    }

    private TourSchedule enterData(TourSchedule tourTemp){
        TourList l = TourList.getInstance();
        String id = l.getTourId();
        tourTemp.setTourID(id);
        tourTemp.setID(enterTourScheduleID());
        tourTemp.setAdultPrice(l.searchObjectById(id).getAdultPrice());
        tourTemp.setChildPrice(l.searchObjectById(id).getChildPrice());
        tourTemp.setEmployeeID(MyUtil.getId("Enter EmployeeID(E123): ", "The format is incorrect", "E\\d{3}$"));
        tourTemp.setDepartureDay(MyUtil.getDate("Enter Departure Day(dd-mm-yyyy): ", "The format is incorrect (dd-mm-yyyy)", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        tourTemp.setReturnDay(MyUtil.getDate("Enter Return Day(dd-mm-yyyy): ", "The format is incorrect (dd-mm-yyyy)", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        tourTemp.setEmptySlots(MyUtil.getAnInteger("Enter Empty Slot(1 <= slot <= 50): ", "The input is INTEGER and limit between 1 and 50", 1, 50));
        tourTemp.setDuration(ChronoUnit.DAYS.between( tourTemp.getDepartureDay(),tourTemp.getReturnDay()));
        tourTemp.setTotalPrice(0);
        // totalprice tinh tat ca luong tien tu schedule detail, sau nay them
        return tourTemp;
    }
    
    public TourSchedule[] getTourScheduleSameTourID(String tourID){
        TourSchedule[] ts = new TourSchedule[0];
        int count = 0;
        if(existedTourSchedule == 0){
            return null;
        }
        for (int i = 0; i < existedTourSchedule; i++){
            if(tourScheduleList[i].getTourID().compareToIgnoreCase(tourID) == 0){
                ts = Arrays.copyOf(ts, ts.length + 1);
                ts[count++] = tourScheduleList[i];
            }
        }
        return ts;
    }
       
    public TourSchedule[] getTourScheduleCheapPrice(int price){
        TourSchedule[] ts = new TourSchedule[0];
        int count = 0;
        for (int i = 0; i < existedTourSchedule; i++)
            if(tourScheduleList[i].getTotalPrice()< price){
                ts = Arrays.copyOf(ts, ts.length + 1);
                ts[count++] = tourScheduleList[i];                
            }
        return ts;
    }
//    public void printListAscendingByCost(){
//        if (existedTourSchedule == 0) {
//            System.out.println("List is empting");
//            return;
//        }
//        System.out.println(header);
//        for (int i = 0; i < existedTourSchedule - 1; i++) 
//            for (int j = i + 1; j < existedTourSchedule; j++) 
//                if (tourScheduleList[i].getCurrentPrice() > tourScheduleList[j].getCurrentPrice()) {
//                    TourSchedule temp = tourScheduleList[i];
//                    tourScheduleList[i] = tourScheduleList[j];
//                    tourScheduleList[j] = temp;
//                }           
//        
//        for (int i = 0; i < existedTourSchedule; i++) 
//            tourScheduleList[i].showInfor();           
//    }
    public String getTourScheduleID(){
         if (existedTourSchedule == 0) {
            System.out.println("No more than a tour in list");
            return null;
        } 
            String id;
            TourSchedule x; 
            do {
                printListAscendingById();
                id =MyUtil.getString("Enter ID(VD: TS123): ", "The id is required");
                x = searchObjectById(id);
                if (x == null){
                     System.out.println("Please!!! Input id in the list");
                }
            } while (x ==null);
            return id;
    } 
    
    @Override
    public void ReadData(LoadData loadData) {
        Object[] obj = loadData.read();
        if (obj == null) {
            System.out.println("Not data");
            return;
        }
        for (Object o : obj) {
            tourScheduleList = Arrays.copyOf(tourScheduleList, tourScheduleList.length + 1);
            tourScheduleList[existedTourSchedule++] = (TourSchedule) o;
        }
    }

    @Override
    public void saveToDate(SaveData saveData) {
        saveData.save(tourScheduleList, header);
    }
    
    public static void main(String[] args) {
        TourScheduleList l = TourScheduleList.getInstance();
//       System.out.println(l.header);
        l.ReadData(new LoadDataFromFile("Files/TourSchedule.dat"));
 l.add();
  l.add();
 l.add();
 l.add();
 l.add();
// l.add();
// l.add();
// l.add();
// l.add();
// l.add();

   //     l.update();
        l.printListAscendingById();
       // l.remove();
        l.saveToDate(new SaveDataToFile("Files/TourSchedule.dat"));
    }
}
