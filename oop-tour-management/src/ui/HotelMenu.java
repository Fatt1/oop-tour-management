
package ui;

import IOFile.SaveFileText;
import java.util.Scanner;
import lists.HotelList;


public class HotelMenu implements ManagementMenu {

    @Override
public void openMenu() {
    // Tiêu đề menu
    System.out.println("=============================================");
    System.out.println("                 Manage Hotel                ");
    System.out.println("=============================================");

    // Lựa chọn menu
    Menu HotelMenu = new Menu("Options:");
    HotelMenu.addNewOption("1. ➤ Add new Hotel");
    HotelMenu.addNewOption("2. ➤ Print Hotel list");
    HotelMenu.addNewOption("3. ➤ Update Hotel");
    HotelMenu.addNewOption("4. ➤ Remove Hotel");
    HotelMenu.addNewOption("5. ➤ Search Hotel");
    HotelMenu.addNewOption("6. ➤ Show Hotel details");
    HotelMenu.addNewOption("7. ➤ Save to file text");
    HotelMenu.addNewOption("8. ➤ Exit");
    HotelList htlList = HotelList.getInstance();

    int choice;

    do {
        System.out.println("---------------------------------------------");
        HotelMenu.printMenu();
        System.out.println("---------------------------------------------");
        System.out.print("Your choice: ");
        choice = HotelMenu.getChoice();
        
        System.out.println("\nProcessing...\n");
        switch (choice) {
            case 1:
                System.out.println("★ Adding a new hotel ★");
                htlList.add();
                break;
            case 2:
                System.out.println("★ Printing hotel list ★");
                htlList.printListAscendingById();
                break;
            case 3:
                System.out.println("★ Updating an hotel ★");
                htlList.update();
                break;
            case 4:
                System.out.println("★ Removing an hotel ★");
                htlList.remove();
                break;
            case 5:
                System.out.println("★ Searching for an hotel ★");
                htlList.searchById();
            case 6:
                System.out.println("★ Saving to file ★");
                htlList.saveToDate(new SaveFileText("FileText/Hotels.txt"));
                System.out.println("✔ Saved successfully!");
                break;
        }
        if (choice != 7) {
            System.out.println("\nPress Enter to return to the menu...");
            new Scanner(System.in).nextLine();
        }
    } while (choice != 7);

    System.out.println("=============================================");
    System.out.println("         Exiting Hotel Management          ");
    System.out.println("=============================================");
}
}

