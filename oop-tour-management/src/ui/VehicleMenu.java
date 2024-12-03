/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import interfaces.ManagementMenu;
import IOFile.SaveDataToFile;
import IOFile.SaveFileText;
import java.util.Scanner;
import lists.CustomerList;
import lists.VehicleList;

/**
 *
 * @author User
 */
public class VehicleMenu implements ManagementMenu{

    @Override
    public void openMenu() {
    // Tiêu đề menu
    System.out.println("=============================================");
    System.out.println("                 Manage Vehicle              ");
    System.out.println("=============================================");

    // Lựa chọn menu
    Menu customerMenu = new Menu("Options:");
    customerMenu.addNewOption("1. ➤ Add new vehicle");
    customerMenu.addNewOption("2. ➤ Print list");
    customerMenu.addNewOption("3. ➤ Update vehicle");
    customerMenu.addNewOption("4. ➤ Remove vehicle");
    customerMenu.addNewOption("5. ➤ Search vehicle");
    customerMenu.addNewOption("6. ➤ Save");
    customerMenu.addNewOption("7. ➤ Save to file text");
    customerMenu.addNewOption("8. ➤ Exit");
    VehicleList vehicleList = VehicleList.getInstance();

    int choice;

    do {
        System.out.println("---------------------------------------------");
        customerMenu.printMenu();
        System.out.println("---------------------------------------------");
        System.out.print("Your choice: ");
        choice = customerMenu.getChoice();
        
        System.out.println("\nProcessing...\n");
        switch (choice) {
            case 1:
                System.out.println("★ Adding a new vehicle ★");
                vehicleList.add();
                break;
            case 2:
                System.out.println("★ Printing vehicle list  ★");
                vehicleList.printListAscendingById();
                break;
            case 3:
                System.out.println("★ Updating a vehicle ★");
                vehicleList.update();
                break;
            case 4:
                System.out.println("★ Removing for a vehicle ★");
                vehicleList.remove();
                break;
            case 5:
                System.out.println("★ Searching for a vehicle ★");
                vehicleList.searchById();
                break;
            case 6:
                System.out.println("★ Saving to file★");
                vehicleList.saveToData(new SaveDataToFile("Files/Vehicles.dat"));
                System.out.println("✔ Saved successfully!");     
                break;
            case 7:
                System.out.println("★ Saving to file text ★");
                vehicleList.saveToData(new SaveFileText("FileText/Vehicles.txt"));
                System.out.println("✔ Saved successfully!");
                break;
        }
        if (choice != 8) {
            System.out.println("\nPress Enter to return to the menu...");
            new Scanner(System.in).nextLine();
        }
    } while (choice != 8);

    System.out.println("=============================================");
    System.out.println("         Exiting Vehicle Management          ");
    System.out.println("=============================================");
    }
    
    public static void main(String[] args) {
        VehicleMenu ve  = new VehicleMenu();
        ve.openMenu();
    }
}
