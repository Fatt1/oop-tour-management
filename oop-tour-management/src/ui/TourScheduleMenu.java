/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import IOFile.SaveDataToFile;
import IOFile.SaveFileText;
import java.util.Scanner;
import model.tour.controller.ControllerTSandT;

/**
 *
 * @author nghialam
 */
public class TourScheduleMenu implements ManagementMenu {
    
    @Override
    public void openMenu() {
        System.out.println("=============================================");
        System.out.println("           Manage Tours Schedule             ");
        System.out.println("=============================================");

        // Lựa chọn menu
        Menu tourMenu = new Menu("Options:");
        tourMenu.addNewOption("1. ➤ Add new Schedule tour");
        tourMenu.addNewOption("2. ➤ Print tour Schedule list");
        tourMenu.addNewOption("3. ➤ Update tour Schedule");
        tourMenu.addNewOption("4. ➤ Remove tour Schedule");
        tourMenu.addNewOption("5. ➤ Search tour Schedule");
        tourMenu.addNewOption("6. ➤ Show filter by tour Schedule");
        tourMenu.addNewOption("7. ➤ Save to file");
        tourMenu.addNewOption("8. ➤ Exit");

        int choice;
        ControllerTSandT control = new ControllerTSandT();
        do {
            System.out.println("---------------------------------------------");
            tourMenu.printMenu();
            System.out.println("---------------------------------------------");
            System.out.print("Your choice: ");
            choice = tourMenu.getChoice();

            System.out.println("\nProcessing...\n");
            switch (choice) {
                case 1:
                    System.out.println("★ Adding a new tour Schedule★");
                    control.getTourScheduleList().add();
                    break;
                case 2:
                    System.out.println("★ Printing tour Schedule list ★");
                    control.getTourScheduleList().printListAscendingById();
                    break;
                case 3:
                    System.out.println("★ Updating an tour Schedule★");
                    control.getTourScheduleList().update();
                    break;
                case 4:
                    System.out.println("★ Removing an tour Schedule★");
                    control.removeTour();
                    control.getTourScheduleList().printListAscendingById();
                    break;
                case 5:
                    System.out.println("★ Searching for an tour Schedule★");
                    control.getTourScheduleList().searchById();
                    break;
                case 6:
                    System.out.println("★ Showing filter by tour Schedule★");
                    control.getTourScheduleList().menuForFilter();
                    break;
                case 7:
                    System.out.println("★ Saving to file ★");
                    control.getTourScheduleList().saveToDate(new SaveDataToFile("Files/TourSchedule.dat"));
                    System.out.println("✔ Saved successfully!");
                    break;
            }
            if (choice != 8) {
                System.out.println("\nPress Enter to return to the menu...");
                new Scanner(System.in).nextLine();
            }
        } while (choice != 8);

        System.out.println("=============================================");
        System.out.println("      Exiting TourSchedule Management          ");
        System.out.println("=============================================");
    }
        public static void main(String[] args) {
        TourScheduleMenu in = new TourScheduleMenu();
        in.openMenu();
    }
    
}
