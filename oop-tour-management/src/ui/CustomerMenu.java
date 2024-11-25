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

/**
 *
 * @author User
 */
public class CustomerMenu implements ManagementMenu{

    @Override
    public void openMenu() {
         // Tiêu đề menu
    System.out.println("=============================================");
    System.out.println("                 Manage Customer              ");
    System.out.println("=============================================");

    // Lựa chọn menu
    Menu customerMenu = new Menu("Options:");
    customerMenu.addNewOption("1.  ➤ Add new customer");
    customerMenu.addNewOption("2.  ➤ Print list ascending by id");
    customerMenu.addNewOption("3.  ➤ Print list ascending by name");
    customerMenu.addNewOption("4.  ➤ Update customer");
    customerMenu.addNewOption("5.  ➤ Remove customer");
    customerMenu.addNewOption("6.  ➤ Search customer by id");
    customerMenu.addNewOption("7.  ➤ Search customer by name");
    customerMenu.addNewOption("8.  ➤ Save");
    customerMenu.addNewOption("9.  ➤ Save to file text");
    customerMenu.addNewOption("10. ➤ Exit");
    CustomerList cusList = CustomerList.getInstance();

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
                System.out.println("★ Adding a new customer ★");
                cusList.add();
                break;
            case 2:
                System.out.println("★ Printing customer list ascending by id ★");
                cusList.printListAscendingById();
                break;
            case 3:
                System.out.println("★ Printing customer list ascending by name ★");
                cusList.printListAscendingByName();
                break;
            case 4:
                System.out.println("★ Updating a customer ★");
                cusList.update();
                break;
            case 5:
                System.out.println("★ Removing for a customer ★");
                cusList.remove();
                break;
            case 6:
                System.out.println("★ Searching for an customer by id ★");
                cusList.searchById();
                break;
            case 7:
                System.out.println("★ Searching for an customer by name ★");
                cusList.searchCustomerByName();
                break;
            case 8:
                System.out.println("★ Saving to file ★");
                cusList.saveToDate(new SaveDataToFile("Files/Customers.dat"));
                System.out.println("✔ Saved successfully!");     
                break;
            case 9:
                System.out.println("★ Saving to file text ★");
                cusList.saveToDate(new SaveFileText("FileText/Customers.txt"));
                System.out.println("✔ Saved successfully!");
                break;
        }
        if (choice != 10) {
            System.out.println("\nPress Enter to return to the menu...");
            new Scanner(System.in).nextLine();
        }
    } while (choice != 10);

    System.out.println("=============================================");
    System.out.println("         Exiting Customer Management          ");
    System.out.println("=============================================");
    }
    
    public static void main(String[] args) {
        CustomerMenu c = new CustomerMenu();
        c.openMenu();
    }
    
}
