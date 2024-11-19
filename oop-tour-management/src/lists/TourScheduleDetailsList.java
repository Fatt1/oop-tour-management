package lists;

import IOFile.LoadDataFromFile;
import IOFile.SaveDataToFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import ui.Menu;
import java.util.Scanner;

import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;
import model.Activity;
import model.TourScheduleDetails;
import util.MyUtil;

public class TourScheduleDetailsList implements IManager<TourScheduleDetails> {

    private static TourScheduleDetails[] tourDetailsList;
    private Scanner sc = new Scanner(System.in);
    private int extistedTourDetails;
    private String header = String.format(  "|%-6s|%-10s|%-10s|%-15s|%-15s|%-10s|%-10s|%-20s|%-30s|%-10s|%-20s|",
                                              "ID ","Day","HotelId","HotelCost","RestaurantId","MealCost","OtherCost","Tong tien 1 ngay","Description","Location","Star time");

    public TourScheduleDetailsList() {
        tourDetailsList = new TourScheduleDetails[0];
        extistedTourDetails = 0;

    }

    @Override
    public void add() {
        extistedTourDetails = tourDetailsList.length;
        String id;
        int duplicatedId;
        do {
           id = TourScheduleList.getInstance().getTourScheduleID();
            duplicatedId = searchById(id);
            if (duplicatedId >= 0) {
                System.out.println("ID is duplicated , please try again: ");
            }
        } while (duplicatedId >= 0);
        String k;
        long day = TourScheduleList.getInstance().searchObjectById(id).getDuration();
            for (int i = 1; i <= day; i++) {
            String hotelId = MyUtil.getId("Please input the hotel Id (HXXX) of day " + i + ": ", "The hotel Id id is required ,(HXXX)", "^H[0-9]{3}");
            int hotelCost = MyUtil.getAnInteger("Enter the hotel cost for day " + i + ": ", "You entered incorrectly, please try again");
            String restaurantId = MyUtil.getId("Input the Restaurant ID of day " + i + " (RXXX): ", "The format of ID is (RXXX)" + "X stands for digit(0-9)", "^R[0-9]{3}$");
            int mealCost = MyUtil.getAnInteger("Enter the meal cost for day " + i + ": ", "You entered incorrectly, please try again");
            int otherCost = MyUtil.getAnInteger("Enter the other cost for day " + i + ": ", "You entered incorrectly, please try again");
            String activityDecription = MyUtil.getString("Enter the activity decription for day " + i + ": ", "You entered incorrectly, please try again");
            String location = MyUtil.getString("Enter the location for day " + i + ": ", "You entered incorrectly, please try again");
            LocalDateTime starTime = MyUtil.getDateTime("Enter the star time for day " + i + ": ", "You entered incorrectly, please try again", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Activity activity = new Activity(activityDecription, location, starTime);
            tourDetailsList = Arrays.copyOf(tourDetailsList, tourDetailsList.length + 1);
            tourDetailsList[extistedTourDetails] = new TourScheduleDetails(id, i, hotelId, hotelCost, restaurantId, mealCost, otherCost, activity);
            System.out.println("You have successfully added the tour schedule details.");
            extistedTourDetails++;
        }   
            
           
    }

    @Override
    public void update() {
        String id = MyUtil.getId("Input update TourScheduleDetails id: ", "The TourScheduleDetails id is required ,(TSXXX): ", "^TS[0-9]{3}$");
        TourScheduleDetails[] tsd = searchListObjectById(id);
        if (tsd == null) {
            System.out.println("Not found!!");
            return;
        }
        System.out.println("Here is the Tour Schedule details that you want to update");
        System.out.println(header);
        for (int i = 0; i < tsd.length; i++) {
            tsd[i].display();
        
        }
        int day = MyUtil.getAnInteger("Enter the day you want to update (1->" + tsd.length + "): ", "You entered incorrectly, please try again (1->" + tsd.length + ")", 1, tsd.length) - 1;
        Menu menu = new Menu("Update TourScheduleDetails");
        menu.addNewOption("1: Update hotelId");
        menu.addNewOption("2: Update hotelCost");
        menu.addNewOption("3: Update restaurantId");
        menu.addNewOption("4: Update mealCost");
        menu.addNewOption("5: Update otherCost ");
        menu.addNewOption("6: Update activity decription");
        menu.addNewOption("7: Update location ");
        menu.addNewOption("8: Update start time");
        menu.addNewOption("9: Exit");

        int choice;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            System.out.println(header);
            tsd[day].display();
            switch (choice) {
                case 1: {

                    String newHotelId = MyUtil.getId("Input new hotel Id: ", "The new hotel Id is required", "^H[0-9]{3}$");
                    tsd[day].setHotelId(newHotelId);
                    break;
                }
                case 2: {
                    int newHotelCost = MyUtil.getAnInteger("Input new hotel Cost: ", "The new hotel Cost is required");
                    tsd[day].setHotelCost(newHotelCost);
                    break;
                }
                case 3: {
                    String newRestaurantId = MyUtil.getId("Input new new RestaurentId: ", "The new new RestaurentId is required", "^R[0-9] {3}$");
                    tsd[day].setRestaurantId(newRestaurantId);
                    break;
                }
                case 4: {
                    int newMealCost = MyUtil.getAnInteger("Input new meal Cost: ", "The new meal Cost is required");
                    tsd[day].setMealCost(newMealCost);
                    break;
                }
                case 5: {
                    int newOtherCost = MyUtil.getAnInteger("Input new other Cost: ", "The new other Cost is required");
                    tsd[day].setOtherCost(newOtherCost);
                    break;
                }
                case 6: {
                    String newActivityDecription = MyUtil.getString("Input new activity decription ", "The new activity decription is required");
                    tsd[day].getActivity().setActivityDecription(newActivityDecription);
                    break;
                }
                case 7: {
                    String newLocation = MyUtil.getString("Input new location ", "The new location is required");
                    tsd[day].getActivity().setLocation(newLocation);
                    break;
                }
                case 8: {
                    LocalDateTime newStarTime = MyUtil.getDateTime("Input new star time (yyyy-MM-dd HH:mm)", "The new location is required (yyyy-MM-dd HH:mm)", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    tsd[day].getActivity().setStarTime(newStarTime);
                    break;
                }

            }
            if (choice < 9) {
                System.out.println("Update successfully");
                System.out.println("The TourScheduleDetails after updating");
                System.out.println(header);
                tsd[day].display();
                System.out.println("Press enter to continute...");
                sc.nextLine();
            }
        } while (choice != 9);
    }

    @Override
    public void remove() {
        String k;
        if (tourDetailsList.length == 0) {
            System.out.println("Not found");
            return;
        }
        do {
            String id = MyUtil.getId("Input Tour Schedule details id remove (TSXXX) : ", "The Tour Schedule details id is required remove (TSXXX) : X:(0->9)", "^TS[0-9]{3}$");
            int index = searchById(id);
            if (index >= 0) {
                int sl = searchListObjectById(id).length;
                for (int i = index; i < tourDetailsList.length-sl; i++) {
                    tourDetailsList[i] = tourDetailsList[i + sl];
                }
                System.out.println("Successfully removed.");
                tourDetailsList = Arrays.copyOf(tourDetailsList, tourDetailsList.length - sl);
                extistedTourDetails = extistedTourDetails - sl;
            } else {
                System.out.println("Tour schedule details not found.");
            }
            do {
                System.out.println("Choose (Y/N)");
                k = sc.nextLine().toLowerCase().trim();
            } while ((k.equalsIgnoreCase("Y") == false) && (k.equalsIgnoreCase("N") == false));
        } while (k.equalsIgnoreCase("Y"));
    }

    @Override
    public void printListAscendingById() {
        if (tourDetailsList.length == 0) {
            System.out.println("Not found");
        }
        for (int i = 0; i < tourDetailsList.length; i++) {
            int indexMin = i;
            String idMin = tourDetailsList[i].getId();
            int dayMin = tourDetailsList[i].getDayNumber();
//			int sl = searchObjectById(tourDetailsList[i].getId()).length;
            for (int j = i + 1; j < tourDetailsList.length; j++) {
                if (idMin.equalsIgnoreCase(tourDetailsList[j].getId())) {
                    if (dayMin > tourDetailsList[j].getDayNumber()) {
                        indexMin = j;
                        idMin = tourDetailsList[j].getId();
                        dayMin = tourDetailsList[j].getDayNumber();
                    }
                }
                if (idMin.compareTo(tourDetailsList[j].getId()) > 0) {
                    indexMin = j;
                    idMin = tourDetailsList[j].getId();
                    dayMin = tourDetailsList[j].getDayNumber();
                }
            }
            TourScheduleDetails temp;
            temp = tourDetailsList[i];
            tourDetailsList[i] = tourDetailsList[indexMin];
            tourDetailsList[indexMin] = temp;
        }
        System.out.println("Tour Schedule Details list sorted by ID:");
        System.out.println(header);
        for (int i = 0; i < tourDetailsList.length; i++) {
            tourDetailsList[i].display();
    
        }
    }

    @Override
    public void searchById() {
        if (tourDetailsList.length == 0) {
            System.out.println("Not found!");
        }
        String k;
        do {
            String id = MyUtil.getId("Input sreach Tour schedule details id (TSXXX): ", "The format of Id is (TSXXX) " + "X stands for digit(0-9)", "^TS[0-9]{3}$");
            int index = searchById(id);
            if (index >= 0) {
                TourScheduleDetails[] tsd = searchListObjectById(id);
                System.out.println("Tour schedule details found:");
                System.out.println(header);
                for (int i = 0; i < tsd.length; i++) {
                    tsd[i].display();
                }
            } else {
                System.out.println("Tour schedule details not found.");
            }
            do {
                System.out.println("Choose (Y/N)");
                k = sc.nextLine().toLowerCase().trim();
            } while ((k.equalsIgnoreCase("Y") == false) && (k.equalsIgnoreCase("N") == false));
        } while (k.equalsIgnoreCase("Y"));
    }

    @Override
    public int searchById(String id) {
        if (tourDetailsList.length == 0) {
            return -1;
        }
        int index = -1;
        for (TourScheduleDetails x : tourDetailsList) {
            index++;
            if (x.getId().equalsIgnoreCase(id)) {
                return index;
            }
        }
        return -1;
    }
    
    @Override
    public TourScheduleDetails searchObjectById(String id) {
       return null;
    }
    
    
    public TourScheduleDetails[] searchListObjectById(String id) {
        if (tourDetailsList.length == 0) {
            return null;
        }
        int index = searchById(id);
        if (index >= 0) {
            TourScheduleDetails[] tsd = new TourScheduleDetails[0];
            int count = 0;
            for (int i = index; i < tourDetailsList.length; i++) {
                if (tourDetailsList[i].getId().equalsIgnoreCase(id)) {
                    tsd = Arrays.copyOf(tsd, tsd.length + 1);
                    tsd[count] = tourDetailsList[i];
                    count++;
                }
            }
            return tsd;

        }
        return null;
    }

    @Override
    public void ReadData(LoadData loadData) {
        Object[] a = loadData.read();
        if (a == null) {
            System.out.println("Rong");
            return;
        }
        for (Object o : a) {
            tourDetailsList = Arrays.copyOf(tourDetailsList, tourDetailsList.length + 1);
            tourDetailsList[extistedTourDetails] = (TourScheduleDetails) o;
            extistedTourDetails++;
        }

    }

    @Override
    public void saveToDate(SaveData saveData) {
        saveData.save(tourDetailsList, header);
    }

    public static void main(String[] args) {
        TourScheduleDetailsList tsd = new TourScheduleDetailsList();
        tsd.ReadData(new LoadDataFromFile("Files/TourScheduleDetails.dat"));
//        tsd.add();
//        tsd.add();
        tsd.printListAscendingById();
        tsd.update();
        tsd.saveToDate(new SaveDataToFile("Files/TourScheduleDetails.dat"));
    }

}
