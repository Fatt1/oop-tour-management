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
    private final String header = String.format("|%-20s|%-20s|%-8s|%-12s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|",
            "TourSchedule", "TourScheduleID", "TourID", "EmployeeID", "returnDay", "departureDay", "emptySlots", "duration", "currentPrice", "adultPrice", "childPrice", "totalPrice");

    private TourScheduleList() {
        tourScheduleList = new TourSchedule[0];
        existedTourSchedule = 0;
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
        //System.out.println(header);

        tourTemp.setID(enterTourScheduleID());
        //tour Id luc nay chua check ben kia nen ok vay do nhap tay
        tourTemp.setTourID(MyUtil.getId("Enter TouID(to123): ", "must be follow format", "TO\\d{3}$"));
        tourTemp.setEmployeeID(MyUtil.getId("Enter EmployeeID(E123): ", "The format is incorrect", "E\\d{3}$"));
        tourTemp.setReturnDay(MyUtil.getDate("Enter Return Day(yyyy-mm-dd): ", "The format is incorrect (yyyy-mm-dd)", DateTimeFormatter.ISO_DATE));
        tourTemp.setDepartureDay(MyUtil.getDate("Enter Departure Day(yyyy-mm-dd): ", "The format is incorrect (yyyy-mm-dd)", DateTimeFormatter.ISO_DATE));
        tourTemp.setEmptySlots(MyUtil.getAnInteger("Enter Empty Slot(1 <= slot <= 50): ", "The input is INTEGER and limit between 1 and 50", 1, 50));
        tourTemp.setDuration(ChronoUnit.DAYS.between(tourTemp.getReturnDay(), tourTemp.getDepartureDay()));
        tourTemp.setCurrentPrice(MyUtil.getAnInteger("Enter Current Price(1000000 <= 1000000000): ", "The type data is INTEGER and limit between 1000000 and 1000000000", 1000000, 1000000000));
        tourTemp.setAdultPrice(MyUtil.getAnInteger("Enter Adult Price(1000000 <= 1000000000): ", "The type data is INTEGER and limit between 1000000 and 1000000000", 1000000, 1000000000));
        tourTemp.setChildPrice(MyUtil.getAnInteger("Enter Child Price(1000000 <= 1000000000): ", "The type data is INTEGER and limit between 1000000 and 1000000000", 1000000, 1000000000));
        tourTemp.setTotalPrice(MyUtil.getAnInteger("Enter Total Price(1000000 <= 1000000000): ", "The type data is INTEGER and limit between 1000000 and 1000000000", 1000000, 1000000000));
        // totalprice tinh tat ca luong tien tu schedule detail, sau nay them
        System.out.println(header);
        tourTemp.showInfor();
        tourScheduleList = Arrays.copyOf(tourScheduleList, tourScheduleList.length + 1);
        tourScheduleList[existedTourSchedule++] = tourTemp;
    }

    private String enterTourScheduleID() {
        int isCheck;
        String id;
        do {
            id = MyUtil.getId("Enter TourScheduleID(ts123): ", "must be follow format", "TS\\d{3}$");
            isCheck = searchById(id);
            if (isCheck >= 0) {
                System.out.println("Id is duplicated!!!");
            }
        } while (isCheck >= 0);
        return id;
    }

    @Override
    public void update() {
        int choice;
        printListAscendingById();
        Menu menu = new Menu("Tour Schedule!!!");
        menu = addMenuScheduleTour(menu);

        String id = MyUtil.getString("Enter ID: ", "Not Space or Enter");
        if (searchById(id) >= 0) {
            chooseMenuScheduleTour(tourScheduleList[searchById(id)], menu);
            return;
        }
        System.out.println("Not find  " + id);
    }

    private void chooseMenuScheduleTour(TourSchedule tourSchedule, Menu menu) {
        int choice;
        String id;

        do {
            menu.printMenu();
            choice = MyUtil.getAnInteger("Enter Your Choice: ", "1 -> 9", 1, 9);
            switch (choice) {
                case 1:
                    TourList tourList = TourList.getInstance();
                    System.out.println("--------------------------------------------------------");
                    tourList.printListAscendingById();
                    System.out.println("Choose TourID from this list");
                    id = MyUtil.getId("Enter Tour ID:", "Not space or Enter and follow format (To123)", "TO\\d{3}$");
                    if (tourList.searchById(id) >= 0) {
                        tourSchedule.setTourID(id);
                    } else {
                        System.out.println("Don't find " + id);
                    }
                    break;
                case 2:
                    // EmployeeList employeeList = EmployeeList.getInstance();

                    System.out.println("--------------------------------------------------------");
                    //employeeList.printListAscendingById();
                    System.out.println("Choose EmployeeID from this list");
                    id = MyUtil.getId("Enter Employee ID:", "Not space or Enter and follow format (E123)", "E\\d{3}$");
                    System.out.println("Tinh nang chua hoan thien" + id);
                    break;
                case 3:
                    System.out.println("---------------------------------------------------------");
                    tourSchedule.setReturnDay(MyUtil.getDate("Enter Renturn Day: ", "Follow format is yyyy-mm-dd", DateTimeFormatter.ISO_DATE));
                    break;
                case 4:
                    System.out.println("---------------------------------------------------------");
                    tourSchedule.setDepartureDay(MyUtil.getDate("Enter Departure Day: ", "Follow format is yyyy-mm-dd", DateTimeFormatter.ISO_DATE));
                    break;
                case 5:
                    System.out.println("---------------------------------------------------------");
                    tourSchedule.setEmptySlots(MyUtil.getAnInteger("Enter Empty Slot(1 <= slot <= 50): ", "The input is INTEGER and limit between 1 and 50", 1, 50));
                    break;
                case 6:
                    System.out.println("---------------------------------------------------------");
                    tourSchedule.setCurrentPrice(MyUtil.getAnInteger("Enter Current Price(1000000 <= 1000000000): ", "The type data is INTEGER and limit between 1000000 and 1000000000", 1000000, 1000000000));
                    break;
                case 7:
                    System.out.println("---------------------------------------------------------");
                    tourSchedule.setAdultPrice(MyUtil.getAnInteger("Enter Adult Price(1000000 <= 1000000000): ", "The type data is INTEGER and limit between 1000000 and 1000000000", 1000000, 1000000000));
                    break;
                case 8:
                    System.out.println("---------------------------------------------------------");
                    tourSchedule.setChildPrice(MyUtil.getAnInteger("Enter Child Price(1000000 <= 1000000000): ", "The type data is INTEGER and limit between 1000000 and 1000000000", 1000000, 1000000000));
                    break;
                case 9:
                    System.out.println("OK out update");
                    break;
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
        menu.addNewOption("6. Update Current Price");
        menu.addNewOption("7. Update Adult Price");
        menu.addNewOption("8. Update Child Price");
        menu.addNewOption("9. Exit!!!");
        return menu;
    }

    @Override
    public void remove() {
        if (existedTourSchedule == 0) {
            System.out.println("List is empty");
            return;
        }
        String id = MyUtil.getString("Enter id(): ", "");
        int isCheck = searchById(id);
        if (isCheck == -1) {
            System.out.println("Don't find id!! please again");
            return;
        }
        for (int i = isCheck; i < existedTourSchedule - 1; i++) {
            tourScheduleList[i] = tourScheduleList[i + 1];
        }
        tourScheduleList = Arrays.copyOf(tourScheduleList, tourScheduleList.length - 1);
        existedTourSchedule--;
    }

    @Override
    public void printListAscendingById() {
        if (existedTourSchedule == 0) {
            System.out.println("List is empting");
            return;
        }
        for (int i = 0; i < existedTourSchedule - 1; i++) {
            for (int j = i + 1; j < existedTourSchedule; j++) {
                if (tourScheduleList[i].getID().compareToIgnoreCase(tourScheduleList[j].getID()) > 0) {
                    TourSchedule temp = tourScheduleList[i];
                    tourScheduleList[i] = tourScheduleList[j];
                    tourScheduleList[j] = temp;
                }
            }
        }
        for (int i = 0; i < existedTourSchedule; i++) {
            tourScheduleList[i].showInfor();
        }
    }

    @Override
    public void searchById() {
        if (existedTourSchedule == 0) {
            System.out.println("No more than a tour in list");
        } else {
            String id = MyUtil.getString("Enter ID(VD: TS123): ", "Don't find ID");
            for (int i = 0; i < existedTourSchedule; i++) {
                if (tourScheduleList[i].getTourID().compareToIgnoreCase(id) == 0) {
                    tourScheduleList[i].showInfor();
                    return;
                }
            }
            System.out.println("Dont find " + id);
        }
    }

    @Override
    public int searchById(String id) {
        if (existedTourSchedule == 0) {
            return -1;
        }
        for (int i = 0; i < existedTourSchedule; i++) {
            if (tourScheduleList[i].getID().compareToIgnoreCase(id) == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public TourSchedule searchObjectById(String id) {
        if (existedTourSchedule == 0) {
            return null;
        }
        for (int i = 0; i < existedTourSchedule; i++) {
            if (tourScheduleList[i].getID().compareToIgnoreCase(id) == 0) {
                return tourScheduleList[i];
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
        System.out.println(l.header);
        l.ReadData(new LoadDataFromFile("Files/TourShedule.dat"));
//        l.add();
//        l.add();
//        l.add();
        l.update();
        l.printListAscendingById();

        //l.saveToDate(new SaveDataToFile("Files/TourShedule.dat"));
    }
}
