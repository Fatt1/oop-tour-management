/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lists;

import IOFile.*;
import interfaces.Filter;
import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.Invoice;
import model.tour.DomesticTour;
import model.tour.InternationalTour;
import model.tour.Tour;
import model.tour.TourSchedule;
import ui.Menu;
import util.MyUtil;

/**
 *
 * @author nghialam
 */
public class TourList implements IManager<Tour>, Serializable {

    private static TourList instance;
    private int existedTour;
    private Tour tourList[];
    private final String header = String.format("|%-18s|%-5s|%-30s|%-20s|%-20s|%-5s|%-10s|%-10s|%-2s|%-15s|%-5s|%-5s|%-100s|",
            "Tour", "ID", "NAME", "DESTINATION", "DEPARTURE LOCAL", "VEHICLE ID", "ADULTPRICE", "CHILDPRICE", "QUANTITY", "COUNTRY", "VISA", "DISCOUNT","SHORT DESCRIPTION" );

    private TourList() {
        tourList = new Tour[0];
        existedTour = 0;
        readData(new LoadDataFromFile("Files/Tours.dat"));
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
            System.out.println(header);
            tour.showInfor();
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
        if (choice.equalsIgnoreCase("YES")) {
            for (int i = searchById(tour.getTourID()); i < existedTour - 1; i++) {
                tourList[i] = tourList[i + 1];
            }
            tourList = Arrays.copyOf(tourList, tourList.length - 1);
            existedTour--;
            System.out.println("The process of removal is successful.");
        }

    }

    public void remove(String id){
         Tour tour = searchObjectById(id);
        if (tour == null) {
            System.out.println("Sr can't find tour");
            return;
        }
        System.out.println("--------------------------------------------------------");
        String choice = MyUtil.getValueOrDefault("Are you sure YES/NO: ", "Please input YES/NO");
        if (choice.equalsIgnoreCase("YES")) {
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
        if (existedTour == 0) {
            System.out.println("No more than a tour in list");
            return;
        }
        String id = MyUtil.getString("Enter ID(VD: TO123): ", "Don't find ID");
        Tour tour = searchObjectById(id);
        if(tour == null){
            System.out.println("Dont find!!!");
            return;
        }
        System.out.println(header);
        tour.showInfor();
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
            for (int i = 0; i < tour.length; i++) {
                tour[i].showInfor();
            }
        }
    }

    @Override
    public void readData(LoadData loadData) {
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
    public void saveToData(SaveData saveData) {
        saveData.save(tourList, header);
    }

    public void filterTourList(Filter<Tour> filter){
        Tour []result = new Tour[0];
        int count = 0;
        for (Tour t : tourList) {
            if(filter.check(t)){
                result = Arrays.copyOf(result, count + 1);
                result[count++] = t;
            }
        }
        if(count == 0){
            System.out.println("Not found!!");
            return;
        }
        System.out.println(header);
        for (Tour re : result) {
            re.showInfor();
        }
    }

    public void menuForFilter() {
        Menu menu = new Menu("Filter");
        menu.addNewOption("1. Filter by local discount.");
        menu.addNewOption("2. Filter by visa.");
        menu.addNewOption("3. Filter by international tour.");
        menu.addNewOption("4. Filter by domestic tour.");
        menu.addNewOption("5. Filter by destination.");
        menu.addNewOption("6. Filter by country.");
        menu.addNewOption("7. Filter by Adult Price.");
        menu.addNewOption("8. Filter bygChild Price");
        menu.addNewOption("9. Exit.");
        int choice;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    double minLocalDiscount = MyUtil.getAnDouble("Enter min local discount: ", "The discount is number");
                    double maxLocalDiscount = MyUtil.getAnDouble("Enter max local discount: ", "The discount is number");
                    filterTourList((o) -> { 
                        if(o instanceof DomesticTour){
                            DomesticTour domesticTour = (DomesticTour)o;
                            if((domesticTour.getLocalDiscount() >= minLocalDiscount && domesticTour.getLocalDiscount() <= maxLocalDiscount)
                                    ||(domesticTour.getLocalDiscount() <= minLocalDiscount && domesticTour.getLocalDiscount() >= maxLocalDiscount) )
                                return true;
                            return false;
                        }
                         return false;                
                    });
                    break;
                    
                case 2:
                    String visaRequired = MyUtil.getValueOrDefault("Enter Yes or No: ", "Not space or enter(Yes or no)");
                    filterTourList((o) -> {
                        if(o instanceof InternationalTour){
                            InternationalTour internationalTour = (InternationalTour)o;
                            return internationalTour.getVisaRequired().equalsIgnoreCase(visaRequired);      
                        }
                         return false;                
                    });
                    break;
                case 3:
                    filterTourList((o) -> o instanceof InternationalTour );                  
                    break;
                case 4:
                    filterTourList((o) -> o instanceof DomesticTour);
                    break;
                case 5:
                    String destination = MyUtil.getString("Enter the destination: ", "The destination is required");
                    filterTourList(o -> o.getDestination().contains(destination));  
                    break;
                case 6:
                    String country = MyUtil.getString("Enter the country: ", "The country is required");
                    filterTourList((o) -> {
                        if(o instanceof InternationalTour){
                            InternationalTour internationalTour = (InternationalTour)o;
                            return internationalTour.getCountry().equalsIgnoreCase(country);      
                        }
                         return false;                
                    });
                    break;
                    
                case 7:
                    int minPrice = MyUtil.getAnInteger("Enter minimum price: ", "Price must be a number.");
                    int maxPrice = MyUtil.getAnInteger("Enter maximum price: ", "Price must be a number.");
                    filterTourList(o -> o.getAdultPrice()>= minPrice && o.getAdultPrice() <= maxPrice);
                    break;
                case 8:
                    int minPriceChild = MyUtil.getAnInteger("Enter minimum price: ", "Price must be a number.");
                    int maxPriceChild = MyUtil.getAnInteger("Enter maximum price: ", "Price must be a number.");
                    filterTourList(a -> a.getChildPrice() >= minPriceChild && a.getChildPrice() <= maxPriceChild);
                    break;
            }
            if(choice != 9){
                System.out.print("Press enter to continue...");
                new Scanner(System.in).nextLine();
            }
        } while (choice != 9);

    }

    public String getTourId() { // lúc nhạp ở class khác thì gọi hàm này ra để cho nhập dữ liệu có sẵn trong tourList chứ không đc nhập ở bên ngoài
        String id;
        do {
            System.out.println("--------------------------------------------------------");
            printListAscendingById();
            System.out.println("Choose TourID from this list");
            id = MyUtil.getId("Enter Tour ID(TOXXX):", "Not space or Enter and follow format (To123)", "TO\\d{3}$");
            if (searchById(id) >= 0) {
                return id;
            }
            System.out.println("Please input again!!");
        } while (searchById(id) < 0);
        return null;
    }
    
    public void renvenueEachTour() {
        Map<Tour, Long> renvenue = new HashMap();
        TourScheduleList tourScheduleList = TourScheduleList.getInstance(); // lấy ra ds sách để chuẩn bị tìm kiếm trong invoice
        for (Tour t : tourList) { // đầu tiền là sẽ chạy từ đầu hết để lấy tất cả tourId để thống kê
            long total = 0;
            TourSchedule[] tourSchedule = tourScheduleList.getTourScheduleSameTourID(t.getTourID());
            for (TourSchedule ts : tourSchedule) {
                total += getRevenueEachTourSchdule(ts.getID());
            }
         renvenue.put(t, total);
        }
        System.out.printf("|%-10s|%-25s|%-13s|\n", "TOUR ID", "TOUR NAME", "REVENUE");
        for (Map.Entry<Tour, Long> r : renvenue.entrySet()) {
            String tourName = r.getKey().getTourName();
            String tourId = r.getKey().getTourID();
            System.out.printf("|%-10s|%-25s|%-13d|\n",tourId, tourName, r.getValue());
        }
    }
    
    private long getRevenueEachTourSchdule(String tourScheduleId) {
        long total = 0;
        InvoiceList ivl = InvoiceList.getInstance();
        Invoice[] invoice = ivl.getInvoiceListByTourScheduleId(tourScheduleId);
        for (Invoice i : invoice) {
            total += i.getTotalAmount();
        }
        return total;
    }
    
}
