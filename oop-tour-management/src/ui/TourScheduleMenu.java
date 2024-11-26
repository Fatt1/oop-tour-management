/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import IOFile.SaveDataToFile;
import interfaces.ManagementMenu;
import java.util.Scanner;
import lists.TourScheduleList;


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
        tourMenu.addNewOption("1. ➤ Add new tour schedule");
        tourMenu.addNewOption("2. ➤ Print tour schedule list");
        tourMenu.addNewOption("3. ➤ Update tour schedule");
        tourMenu.addNewOption("4. ➤ Remove tour schedule");
        tourMenu.addNewOption("5. ➤ Search tour schedule");
        tourMenu.addNewOption("6. ➤ Show filter by tour schedule");
        tourMenu.addNewOption("7. ➤ Save to file");
        tourMenu.addNewOption("8. ➤ Exit");

        int choice;
        TourScheduleList control = TourScheduleList.getInstance();
        do {
            System.out.println("---------------------------------------------");
            tourMenu.printMenu();
            System.out.println("---------------------------------------------");
            System.out.print("Your choice: ");
            choice = tourMenu.getChoice();

            System.out.println("\nProcessing...\n");
            switch (choice) {
                case 1:
                    System.out.println("★ Adding a new tour schedule★");
                    control.add();
                    break;
                case 2:
                    System.out.println("★ Printing tour schedule list ★");
                    control.printListAscendingById();
                    break;
                case 3:
                    System.out.println("★ Updating an tour schedule★");
                    control.update();
                    break;
                case 4:
                    System.out.println("★ Removing an tour schedule★");
                    control.remove();
                    control.printListAscendingById();
                    break;
                case 5:
                    System.out.println("★ Searching for an tour schedule★");
                    control.searchById();
                    break;
                case 6:
                    System.out.println("★ Showing filter by tour schedule★");
                    control.menuForFilter();
                    break;
                case 7:
                    System.out.println("★ Saving to file ★");
                    control.saveToDate(new SaveDataToFile("Files/TourSchedule.dat"));
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
}
