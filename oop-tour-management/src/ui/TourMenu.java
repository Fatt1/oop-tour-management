/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import interfaces.ManagementMenu;
import IOFile.SaveDataToFile;
import IOFile.SaveFileText;
import java.util.Scanner;
import lists.InvoiceList;
import lists.TourList;

/**
 *
 * @author nghialam
 */
public class TourMenu implements ManagementMenu {

    @Override
    public void openMenu() {
        System.out.println("=============================================");
        System.out.println("                 Manage Tours              ");
        System.out.println("=============================================");

        // Lựa chọn menu
        Menu tourMenu = new Menu("Options:");
        tourMenu.addNewOption("1. ➤ Add new tour");
        tourMenu.addNewOption("2. ➤ Print tour list");
        tourMenu.addNewOption("3. ➤ Update tour");
        tourMenu.addNewOption("4. ➤ Remove tour");
        tourMenu.addNewOption("5. ➤ Search tour by id");
        tourMenu.addNewOption("6. ➤ Search tour by name");
        tourMenu.addNewOption("7. ➤ Show filter by tour");
        tourMenu.addNewOption("8. ➤ Save to file");
        tourMenu.addNewOption("9. ➤ Exit");

        int choice;
        TourList control = TourList.getInstance();
        do {
            System.out.println("---------------------------------------------");
            tourMenu.printMenu();
            System.out.println("---------------------------------------------");
            System.out.print("Your choice: ");
            choice = tourMenu.getChoice();

            System.out.println("\nProcessing...\n");
            switch (choice) {
                case 1:
                    System.out.println("★ Adding a new tour ★");
                    control.add();
                    break;
                case 2:
                    System.out.println("★ Printing tour list ★");
                    control.printListAscendingById();
                    break;
                case 3:
                    System.out.println("★ Updating an tour ★");
                    control.update();
                    break;
                case 4:
                    System.out.println("★ Removing an tour ★");
                    control.remove();
                    break;
                case 5:
                    System.out.println("★ Searching for an tour ★");
                    control.searchById();
                    break;
                case 6:
                    System.out.println("★ Searching for an tour ★");
                    control.searchObjectByName();
                    break;
                case 7:
                    System.out.println("★ Showing filter by tour★");
                    control.menuForFilter();
                    break;
                case 8:
                    System.out.println("★ Saving to file ★");
                    control.saveToData(new SaveDataToFile("Files/Tours.dat"));
                    System.out.println("✔ Saved successfully!");
                    break;
            }
            if (choice != 9) {
                System.out.println("\nPress Enter to return to the menu...");
                new Scanner(System.in).nextLine();
            }
        } while (choice != 9);

        System.out.println("=============================================");
        System.out.println("         Exiting Tour Management          ");
        System.out.println("=============================================");
    }
        public static void main(String[] args) {
        TourMenu in = new TourMenu();
        in.openMenu();
    }
}
