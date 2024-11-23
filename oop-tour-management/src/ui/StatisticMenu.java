/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import interfaces.ManagementMenu;
import java.util.Scanner;
import lists.CustomerList;
import lists.InvoiceList;
import lists.TourList;

/**
 *
 * @author User
 */
public class StatisticMenu implements ManagementMenu {

    @Override
    public void openMenu() {
        // Tiêu đề menu
        System.out.println("=============================================");
        System.out.println("               Statistic Menu                ");
        System.out.println("=============================================");

        // Lựa chọn menu
        Menu statisticMenu = new Menu("Options:");
        statisticMenu.addNewOption("1. ➤ Revenue Statistics by Day");
        statisticMenu.addNewOption("2. ➤ Revenue Statistics by Month");
        statisticMenu.addNewOption("3. ➤ Revenue Statistics by Quarter");
        statisticMenu.addNewOption("4. ➤ Revenue Statistics by Year");
        statisticMenu.addNewOption("5. ➤ Revenue Statistics by Tour");
        statisticMenu.addNewOption("6. ➤ Customer Statistics");
        statisticMenu.addNewOption("7. ➤ Exit");

        int choice;

        do {
            System.out.println("---------------------------------------------");
            statisticMenu.printMenu();
            System.out.println("---------------------------------------------");
            System.out.print("Your choice: ");
            choice = statisticMenu.getChoice();

            System.out.println("\nProcessing...\n");
            switch (choice) {
                case 1:
                    System.out.println("★ Showing Revenue Statistics by Day ★");
                    InvoiceList.getInstance().statisticFromDateToDate();
                    break;
                case 2:
                    System.out.println("★ Showing Revenue Statistics by Month ★");
                    InvoiceList.getInstance().statisticMonthRevenue();
                    break;
                case 3:
                    System.out.println("★ Showing Revenue Statistics by Quarter ★");
                    InvoiceList.getInstance().getQuarterPerYear();
                    break;
                case 4:
                    System.out.println("★ Showing Revenue Statistics by Year ★");
                    // Call method to handle yearly revenue statistics // chưa làm
                    break;
                case 5:
                    System.out.println("★ Showing Revenue Statistics by Tour ★");
                    TourList.getInstance().renvenueEachTour();
                    break;
                case 6:
                    System.out.println("★ Showing Customer Statistics ★");
                    CustomerList.getInstance().countToursPerCustomer();
                    break;
                case 7:
                    System.out.println("★ Exiting Statistic Menu ★");
                    break;
                
            }

            if (choice != 7) {
                System.out.println("\nPress Enter to return to the menu...");
                new Scanner(System.in).nextLine();
            }
        } while (choice != 7);

        System.out.println("=============================================");
        System.out.println("          Exitting Statistic Menu!           ");
        System.out.println("=============================================");
    }
}
