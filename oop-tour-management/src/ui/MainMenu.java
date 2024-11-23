/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class MainMenu implements ManagementMenu{

    @Override
    public void openMenu() {
    // Tiêu đề menu
    System.out.println("=============================================");
    System.out.println("          Travel Tour Management             ");
    System.out.println("=============================================");

    // Lựa chọn menu
    Menu travelMenu = new Menu("Options:");
    travelMenu.addNewOption("1.  ➤ Manage Tour");
    travelMenu.addNewOption("2.  ➤ Manage Tour Schedule");
    travelMenu.addNewOption("3.  ➤ Manage Tour Schedule Details");
    travelMenu.addNewOption("4.  ➤ Manage Restaurant");
    travelMenu.addNewOption("5.  ➤ Manage Hotel");
    travelMenu.addNewOption("6.  ➤ Manage Employee");
    travelMenu.addNewOption("7.  ➤ Manage Customer");
    travelMenu.addNewOption("8.  ➤ Manage Invoice");
    travelMenu.addNewOption("9.  ➤ Statistic");
    travelMenu.addNewOption("10. ➤ Exit");

    int choice;

    do {
        System.out.println("---------------------------------------------");
        travelMenu.printMenu();
        System.out.println("---------------------------------------------");
        System.out.print("Your choice: ");
        choice = travelMenu.getChoice();
        
        System.out.println("\nProcessing...\n");
        switch (choice) {
            case 1:
                System.out.println("★ Managing Tour ★");
                new TourMenu().openMenu();
                break;
            case 2:
                System.out.println("★ Managing Tour Schedules ★");
                new TourScheduleMenu().openMenu();
                break;
            case 3:
                System.out.println("★ Managing Tour Schedule Details ★");
                new TourDetailsMenu().openMenu();
                break;
            case 4:
                System.out.println("★ Managing Restaurant ★");
                new RestaurantMenu().openMenu();
                break;
            case 5:
                System.out.println("★ Managing Hotel ★");
                new HotelMenu().openMenu();
                break;
            case 6:
                System.out.println("★ Managing Employee ★");
                new EmployeeMenu().openMenu();
                break;
            case 7:
                System.out.println("★ Managing Customer ★");
                new CustomerMenu().openMenu();
                break;
            case 8:
                System.out.println("★ Managing Invoice ★");
                new InvoiceMenu().openMenu();
                break;
            case 9:
                System.out.println("★ Accessing Statistics ★");
                new StatisticMenu().openMenu();
                break;
            case 10:
                System.out.println("★ Exiting Travel Tour Management ★");
                break;
        }

        if (choice != 10) {
            System.out.println("\nPress Enter to return to the menu...");
            new Scanner(System.in).nextLine();
        }
    } while (choice != 10);

    System.out.println("=============================================");
    System.out.println("      Thank you for using the system!        ");
    System.out.println("=============================================");
}
    
}
