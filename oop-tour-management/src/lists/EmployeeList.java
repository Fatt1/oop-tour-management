package lists;

import java.util.Arrays;
import ui.Menu;
import java.util.Scanner;

import IOFile.LoadDataFromFile;
import IOFile.SaveDataToFile;
import model.Employee;
import model.Employee.Gender;
import model.Employee.Title;
import model.TourScheduleDetails;
import util.MyUtil;
import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;

public class EmployeeList implements IManager<Employee> {

    private int existedEmployee;
    private static EmployeeList instance;
    public Employee[] employeeList;
    private Scanner sc = new Scanner(System.in);
    private String header = String.format("|%-6s|%-25s|%-15s|%-14s|%-25s|%-15s|",
            "Id", "Full name", "Gender", "Phone number", "Address", "Title");

    private EmployeeList() {
        employeeList = new Employee[0];
        existedEmployee = 0;
        ReadData(new LoadDataFromFile("Files/Employees.dat"));

    }

    public static EmployeeList getInstance() {
        if (instance == null) {
            return instance = new EmployeeList();
        }
        return instance;
    }

    @Override
    public void add() {
        existedEmployee = employeeList.length;
        String id;
        int duplicatedId;
        do {
            id = MyUtil.getId("Input Employee id (EXXX): ", "The format of Id is (EXXX) " + "X stands for digit(0-9)", "^E[0->9]{3}$");
            duplicatedId = searchById(id);
            if (duplicatedId >= 0) {
                System.out.println("ID is duplicated, please enter again.");
            }
        } while (duplicatedId >= 0);
        String firstName = MyUtil.getString("Enter first name of Employee: ", "Invalid input, please try again.");
        String lastName = MyUtil.getString("Enter last name of Employee: ", "Invalid input, please try again.");
        int optionGender = MyUtil.getAnInteger("Choose gender option: 1: Male, 2: Female, 3: Other", "Invalid input, please try again (1-3)", 1, 3);
        Gender gender;// class Employee da viet ham thiet lap khoi tao cho no roi, khong biet qua day co can khoi tao lai khong
        switch (optionGender) {
            case 1: {
                gender = Gender.Male;
                break;
            }
            case 2: {
                gender = Gender.Female;
                break;
            }
            case 3: {
                gender = Gender.Other;
                break;
            }
        }
        int number = MyUtil.getAnInteger("Enter phone number of Employee: ", "Invalid input, please try again.");
        String phoneNumber = "0" + number;
        String address = MyUtil.getString("Enter address of Employee: ", "Invalid input, please try again.");
        int optionTitle = MyUtil.getAnInteger("Choose title option: 1: Cashier, 2: Guide, 3: Accountant", "Invalid input, please try again (1-3)", 1, 3);
        Title title;// class Employee da viet ham thiet lap khoi tao cho no roi, khong biet qua day co can khoi tao lai khong
        switch (optionTitle) {
            case 1: {
                title = Title.Cashier;
                break;
            }
            case 2: {
                title = Title.Guide;
                break;
            }
            case 3: {
                title = Title.Accountant;
                break;
            }
        }
        employeeList = Arrays.copyOf(employeeList, employeeList.length + 1);
        employeeList[existedEmployee] = new Employee(id, firstName, lastName, optionGender, phoneNumber, address, optionTitle);
        existedEmployee++;
    }

    @Override
    public void update() {
        String id = MyUtil.getId("Input update employee id: ", "The employee id is required", "^E[0-9]{3}$");
        Employee x = searchObjectById(id);
        if (x == null) {
            System.out.println("Not found!!");
            return;
        }
        System.out.println("Here is the employee that you want to update");
        System.out.println(header);
        x.display();
        Menu menu = new Menu("Update Employee");
        menu.addNewOption("1 : Update firstName");
        menu.addNewOption("2 : Update lastName");
        menu.addNewOption("3 : Update gender");
        menu.addNewOption("4 : Update phoneNumber");
        menu.addNewOption("5 : Update address");
        menu.addNewOption("6 : Update title");
        menu.addNewOption("7. Exit");
        int choice;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1: {
                    String newFisrtName = MyUtil.getString("Input new fisrt name: ", "The fisrt name is required");
                    x.setFirstName(newFisrtName);
                    break;
                }
                case 2: {
                    String newLastName = MyUtil.getString("Input new last name: ", "The last name is required");
                    x.setLastName(newLastName);
                    break;
                }
                case 3: {
                    int optionGender = MyUtil.getAnInteger("Input option new Gender (1->3)", "The option last name is required", 1, 3);
                    x.setGender(optionGender);
                    break;
                }
                case 4: {
                    //String newPhoneNumber = MyUtil.getString("Input new last name: ", "The last name is required");
                    int number = MyUtil.getAnInteger("Input option new Phone Number", "The phone number is required");
                    String newPhoneNumber = "0" + number;
                    x.setPhoneNumber(newPhoneNumber);
                    break;
                }
                case 5: {
                    String newAddress = MyUtil.getString("Input new address: ", "The address is required");
                    x.setAddress(newAddress);
                    break;
                }
                case 6: {
                    int optionTitle = MyUtil.getAnInteger("Input option new Title (1->3)", "The option title is required", 1, 3);
                    x.setTitle(optionTitle);
                    break;
                }
            }
            if (choice < 7) {
                System.out.println("Update successfully");

                System.out.println("The Employee after updating");
                System.out.println(header);
                x.display();
                System.out.println("Press enter to continute...");
                sc.nextLine();
            }
        } while (choice != 7);
    }

    @Override
    public void remove() {
        if (employeeList.length == 0) {
            System.out.println("Not found!!");
            return;
        }
        String k;
        do {
            String id = MyUtil.getId("Input remove Employee id (EXXX): ", "The format of Id is (EXXX) " + "X stands for digit(0-9)", "^E[0-9]{3}$");
            int index = searchById(id);
            if (index >= 0) {
                for (int i = index; i < employeeList.length - 1; i++) {
                    employeeList[i] = employeeList[i + 1];
                }
                //employeeList[employeeList.length-1]=null;
                System.out.println("Employee removed successfully.");
                employeeList = Arrays.copyOf(employeeList, employeeList.length - 1);
                existedEmployee--;

            } else {
                System.out.println("Employee not found.");
            }
            do {
                System.out.println("Do you want to continue (Y/N)?");
                k = sc.nextLine().toLowerCase().trim();
            } while (k.equalsIgnoreCase("Y") == false && (k.equalsIgnoreCase("N") == false));
        } while (k.equalsIgnoreCase("Y"));
    }

    @Override
    public void printListAscendingById() {
        if (employeeList.length == 0) {
            System.out.println("Not found!!");
            return;
        }
        for (int i = 0; i < employeeList.length; i++) {
            int indexMin = i;
            String idMin = employeeList[i].getEmployeeId();
            for (int j = i + 1; j < employeeList.length; j++) {
                if (idMin.compareTo(employeeList[j].getEmployeeId()) > 0) {
                    indexMin = j;
                    idMin = employeeList[j].getEmployeeId();
                }
            }
            Employee temp;
            temp = employeeList[i];
            employeeList[i] = employeeList[indexMin];
            employeeList[indexMin] = temp;
        }
        System.out.println("Employee list sorted by ID:");
        System.out.println(header);
        for (Employee x : employeeList) {
            x.display();
        }
    }

    @Override
    public void searchById() {
        if (employeeList.length == 0) {
            System.out.println("No employees found!");
        }
        String k;
        do {
            String id = MyUtil.getId("Input search Employee id (EXXX): ", "The format of Id is (EXXX) " + "X stands for digit(0-9)", "^E[0-9]{3}$");
            int index = searchById(id);
            System.out.println(header);
            if (index >= 0) {
                System.out.println("Employee found:");
                employeeList[index].display();
            } else {
                System.out.println("Employee not found.");
            }
            do {
                System.out.println("Do you want to search another Employee? (Y/N)");
                k = sc.nextLine().toLowerCase().trim();
            } while ((k.equalsIgnoreCase("Y") == false) && (k.equalsIgnoreCase("N") == false));
        } while (k.equalsIgnoreCase("Y"));
    }

    @Override
    public int searchById(String id) {
        if (employeeList.length == 0) {
            return -1;
        }
        int index = -1;
        for (int i = 0; i < employeeList.length; i++) {
            index++;
            if (employeeList[i].getEmployeeId().equalsIgnoreCase(id)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public Employee searchObjectById(String id) {
        if (employeeList.length == 0) {
            return null;
        }
        int index = searchById(id);
        if (index >= 0) {
            return employeeList[index];
        }
        return null;
    }
    
    public String getEmployeeId(){
        Employee x;
        String employeeId;
        
        do {     
        printListAscendingById();
        employeeId = MyUtil.getString("Input id employee from the list  (EXXX): ", "The employee id is required");
        x = searchObjectById(employeeId);
        if(x == null)
            System.out.println("Please input the employee has in the list");
        
        } while (x == null);
        
        return employeeId;
    }

    @Override
    public void ReadData(LoadData loadData) {
        Object[] a = loadData.read();
        if (a == null) {
            System.out.println("Rong");
            return;
        }
        for (Object o : a) {
            employeeList = Arrays.copyOf(employeeList, employeeList.length + 1);
            employeeList[existedEmployee] = (Employee) o;
            existedEmployee++;
        }

    }
    
    
    @Override
    public void saveToDate(SaveData saveData) {
        saveData.save(employeeList, header);

    }

}
