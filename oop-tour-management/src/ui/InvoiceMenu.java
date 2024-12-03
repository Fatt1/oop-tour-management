package ui;

import interfaces.ManagementMenu;
import IOFile.SaveFileText;
import java.util.Scanner;
import lists.InvoiceList;

public class InvoiceMenu implements ManagementMenu {

    @Override
public void openMenu() {
    // Tiêu đề menu
    System.out.println("=============================================");
    System.out.println("                 Manage Invoice              ");
    System.out.println("=============================================");

    // Lựa chọn menu
    Menu invoiceMenu = new Menu("Options:");
    invoiceMenu.addNewOption("1. ➤ Add new invoice");
    invoiceMenu.addNewOption("2. ➤ Print invoice list");
    invoiceMenu.addNewOption("3. ➤ Update invoice");
    invoiceMenu.addNewOption("4. ➤ Remove invoice");
    invoiceMenu.addNewOption("5. ➤ Search invoice");
    invoiceMenu.addNewOption("6. ➤ Show invoice details");
    invoiceMenu.addNewOption("7. ➤ Save to file text");
    invoiceMenu.addNewOption("8. ➤ Exit");
    InvoiceList invList = InvoiceList.getInstance();

    int choice;

    do {
        System.out.println("---------------------------------------------");
        invoiceMenu.printMenu();
        System.out.println("---------------------------------------------");
        System.out.print("Your choice: ");
        choice = invoiceMenu.getChoice();
        
        System.out.println("\nProcessing...\n");
        switch (choice) {
            case 1:
                System.out.println("★ Adding a new invoice ★");
                invList.add();
                break;
            case 2:
                System.out.println("★ Printing invoice list ★");
                invList.printListAscendingById();
                break;
            case 3:
                System.out.println("★ Updating an invoice ★");
                invList.update();
                break;
            case 4:
                System.out.println("★ Removing an invoice ★");
                invList.remove();
                break;
            case 5:
                System.out.println("★ Searching for an invoice ★");
                invList.searchById();
                break;
            case 6:
                System.out.println("★ Showing invoice details ★");
                invList.showInvoiceDetails();
                break;
            case 7:
                System.out.println("★ Saving to file ★");
                invList.saveToData(new SaveFileText("FileText/Invoices.txt"));
                System.out.println("✔ Saved successfully!");
                break;
        }
        if (choice != 8) {
            System.out.println("\nPress Enter to return to the menu...");
            new Scanner(System.in).nextLine();
        }
    } while (choice != 8);

    System.out.println("=============================================");
    System.out.println("         Exiting Invoice Management          ");
    System.out.println("=============================================");
}
    public static void main(String[] args) {
        InvoiceMenu in = new InvoiceMenu();
        in.openMenu();
    }
}