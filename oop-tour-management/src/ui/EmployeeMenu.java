package ui;

import IOFile.SaveFileText;
import java.util.Scanner;

import lists.EmployeeList;
import model.Employee;

public class EmployeeMenu implements ManagementMenu {

    @Override
public void openMenu() {
    // Tiêu đề menu
    System.out.println("=============================================");
    System.out.println("                 Manage Employee              ");
    System.out.println("=============================================");

    // Lựa chọn menu
    Menu employeeMenu = new Menu("Options:");
    employeeMenu.addNewOption("1. ➤ Add new Employee");
    employeeMenu.addNewOption("2. ➤ Print Employee list");
    employeeMenu.addNewOption("3. ➤ Update Employee");
    employeeMenu.addNewOption("4. ➤ Remove Employee");
    employeeMenu.addNewOption("5. ➤ Search Employee");
    employeeMenu.addNewOption("6. ➤ Save to file");
    employeeMenu.addNewOption("7. ➤ Exit");
    EmployeeList employee = EmployeeList.getInstance();

    int choice;

    do {
        System.out.println("---------------------------------------------");
        employeeMenu.printMenu();
        System.out.println("---------------------------------------------");
        System.out.print("Your choice: ");
        choice = employeeMenu.getChoice();
        
        System.out.println("\nProcessing...\n");
        switch (choice) {
            case 1:
                System.out.println("★ Adding a new employee ★");
                employee.add();
                break;
            case 2:
                System.out.println("★ Printing employee list ★");
                employee.printListAscendingById();
                break;
            case 3:
                System.out.println("★ Updating an employee ★");
                employee.update();
                break;
            case 4:
                System.out.println("★ Removing an employee ★");
                employee.remove();
                break;
            case 5:
                System.out.println("★ Searching for an employee ★");
                employee.searchById();
                break;
            case 6:
                System.out.println("★ Saving to file ★");
                employee.saveToDate(new SaveFileText("Files/Employees.dat"));
                System.out.println("✔ Saved successfully!");
                break;
        }
        if (choice != 7) {
            System.out.println("\nPress Enter to return to the menu...");
            new Scanner(System.in).nextLine();
        }
    } while (choice != 7);

    System.out.println("=============================================");
    System.out.println("         Exiting Employee Management          ");
    System.out.println("=============================================");
}
    public static void main(String[] args) {
        EmployeeMenu em = new EmployeeMenu();
        em.openMenu();
    }
}