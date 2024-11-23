
package ui;

import interfaces.ManagementMenu;
import IOFile.SaveFileText;
import java.util.Scanner;
import lists.RestaurantList;


public class RestaurantMenu implements ManagementMenu {

    @Override
public void openMenu() {
    // Tiêu đề menu
    System.out.println("=============================================");
    System.out.println("               Manage Restaurant             ");
    System.out.println("=============================================");

    // Lựa chọn menu
    Menu RestaurantMenu = new Menu("Options:");
    RestaurantMenu.addNewOption("1. ➤ Add new Restaurant");
    RestaurantMenu.addNewOption("2. ➤ Print Restaurant list");
    RestaurantMenu.addNewOption("3. ➤ Update Restaurant");
    RestaurantMenu.addNewOption("4. ➤ Remove Restaurant");
    RestaurantMenu.addNewOption("5. ➤ Search Restaurant");
    RestaurantMenu.addNewOption("6. ➤ Show Restaurant details");
    RestaurantMenu.addNewOption("7. ➤ Save to file text");
    RestaurantMenu.addNewOption("8. ➤ Exit");
    RestaurantList rstList = RestaurantList.getInstance();

    int choice;

    do {
        System.out.println("---------------------------------------------");
        RestaurantMenu.printMenu();
        System.out.println("---------------------------------------------");
        System.out.print("Your choice: ");
        choice = RestaurantMenu.getChoice();
        
        System.out.println("\nProcessing...\n");
        switch (choice) {
            case 1:
                System.out.println("★ Adding a new Restaurant ★");
                rstList.add();
                break;
            case 2:
                System.out.println("★ Printing Restaurant list ★");
                rstList.printListAscendingById();
                break;
            case 3:
                System.out.println("★ Updating an Restaurant ★");
                rstList.update();
                break;
            case 4:
                System.out.println("★ Removing an Restaurant ★");
                rstList.remove();
                break;
            case 5:
                System.out.println("★ Searching for an Restaurant ★");
                rstList.searchById();
            case 6:
                System.out.println("★ Saving to file ★");
                rstList.saveToDate(new SaveFileText("FileText/Restaurants.txt"));
                System.out.println("✔ Saved successfully!");
                break;
        }
        if (choice != 7) {
            System.out.println("\nPress Enter to return to the menu...");
            new Scanner(System.in).nextLine();
        }
    } while (choice != 7);

    System.out.println("=============================================");
    System.out.println("        Exiting Restaurant Management        ");
    System.out.println("=============================================");
}
}
