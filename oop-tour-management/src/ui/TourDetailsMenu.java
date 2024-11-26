package ui;

import interfaces.ManagementMenu;
import IOFile.SaveFileText;
import lists.TourScheduleDetailsList;

import java.util.Scanner;


public class TourDetailsMenu implements ManagementMenu {

    @Override
public void openMenu() {
    // Tiêu đề menu
    System.out.println("=============================================");
    System.out.println("        Manage TourScheduleDetails           ");
    System.out.println("=============================================");

    // Lựa chọn menu
    Menu tourDetailsMenu = new Menu("Options:");
    tourDetailsMenu.addNewOption("1. ➤ Add new TourScheduleDetails");
    tourDetailsMenu.addNewOption("2. ➤ Print TourScheduleDetails list");
    tourDetailsMenu.addNewOption("3. ➤ Update TourScheduleDetails");
    tourDetailsMenu.addNewOption("4. ➤ Remove TourScheduleDetails");
    tourDetailsMenu.addNewOption("5. ➤ Search TourScheduleDetails");
    tourDetailsMenu.addNewOption("6. ➤ Save to file");
    tourDetailsMenu.addNewOption("7. ➤ Exit");
    TourScheduleDetailsList tourDetails = TourScheduleDetailsList.getInstance();

    int choice;

    do {
        System.out.println("---------------------------------------------");
        tourDetailsMenu.printMenu();
        System.out.println("---------------------------------------------");
        System.out.print("Your choice: ");
        choice = tourDetailsMenu.getChoice();
        
        System.out.println("\nProcessing...\n");
        switch (choice) {
            case 1:
                System.out.println("★ Adding a new TourScheduleDetails ★");
                tourDetails.add();
                break;
            case 2:
                System.out.println("★ Printing TourScheduleDetails list ★");
                tourDetails.printListAscendingById();
                break;
            case 3:
                System.out.println("★ Updating an TourScheduleDetails ★");
                tourDetails.update();
                break;
            case 4:
                System.out.println("★ Removing an TourScheduleDetails ★");
                tourDetails.remove();
                break;
            case 5:
                System.out.println("★ Searching for an TourScheduleDetails ★");
                tourDetails.searchById();
                break;
            case 6:
                System.out.println("★ Saving to file ★");
                tourDetails.saveToData(new SaveFileText("Files/TourScheduleDetails.dat"));// cái này tui ko biết ghi sao, tui truyền vô file (có sai thì sủa  lại giúp tui)
                System.out.println("✔ Saved successfully!");
                break;
        }
        if (choice != 7) {
            System.out.println("\nPress Enter to return to the menu...");
            new Scanner(System.in).nextLine();
        }
    } while (choice != 7);

    System.out.println("=============================================");
    System.out.println("   Exiting TourScheduleDetails Management     ");
    System.out.println("=============================================");
}
    public static void main(String[] args) {
        TourDetailsMenu tdm  = new TourDetailsMenu();
        tdm.openMenu();
    }
}