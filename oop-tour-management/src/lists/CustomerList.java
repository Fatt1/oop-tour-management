/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lists;

import IOFile.LoadDataFromFile;
import IOFile.SaveDataToFile;
import IOFile.SaveFileText;
import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Customer;
import model.InvoiceDetails;
import ui.Menu;
import util.MyUtil;

/**
 *
 * @author User
 */
public class CustomerList implements IManager<Customer>{
    private static CustomerList instance; // b1; khai báo static CustomerList, sử dụng static để nó có thể lưu riêng trong bộ nhớ
                                            // vì biến static sẽ không biến mất khi kết thúc cái hết sử dụng class này
                                          // mà nó sẽ kết thúc khi kết thúc chương trình
                                          // nên sử dụng static để có thể sử dụng ở những class khác mà không cần phải khai báo New lại lần nữa
    private  Customer[] cusList;
    private int existedCustomer;
    private String header = String.format("|%-6s|%-25s|%-15s|%-14s|%-25s|%-15s|",
                                            "ID", "FULL NAME", "NATIONALITY", "PHONE", "ADDRESS", "Birthday");
    private Scanner sc = new Scanner(System.in);
    private CustomerList(){ // Singleton Pattern, có 6 7 cách để implement cái Singleton này, nhưng t sẽ sử dụng cách,
                            // Lazy Initialization , b2:
                            // vì sao t muốn sử dụng cách design pattern này. Vì t muốn nó chỉ cần tao ra duy nhất 1 lần instance,
                            // sau đó những class đó có thể sử dụng cái CustomerList này qua hàm getInstace mà không cần phải
                            // khai báo new lại, rồi xong đọc file các kiểu nữa
        cusList = new Customer[0];
        existedCustomer = 0;
        ReadData(new LoadDataFromFile("Files/Customers.dat"));
    }
    
    public static CustomerList getInstance(){ b3:
        if(instance == null){ // lần gọi đầu tiên  instance sẽ bằng null, những lần gọi tiếp có sẵn trong bộ nhớ luôn
            instance = new CustomerList();
        }
        return instance;
    }
    
    @Override
    public void add() {
        String id;
        int duplicateId;
        do {            
             id = MyUtil.getId("Input customer id (CXXX): ", "The format of id is (CXXX) "
                    + "X stands for digit(0-9)", "^[c|C]\\d{3}$");
              duplicateId = searchById(id);
            if (duplicateId >= 0) {
                System.out.println("ID is duplicate. Please input another id!");
            }
        } while (duplicateId >= 0);
       
        String lastName = MyUtil.getString("Input customer's last name: ", "The last name is required");
        String firstName = MyUtil.getString("Input customer's first name: ", "The fist name is required");
        String nationality = MyUtil.getString("Input customer nationality: ", "The customer nationality is required");
        String phoneNumber = MyUtil.getString("Input customer phone : ", "The phone is required");
        String address = MyUtil.getString("Input customer address: ", "The address is required");
        LocalDate dateOfBirth = MyUtil.getDate("Input customer birthday(dd-mm-yyyy): ", "Please input folowing format(dd-mm-yyyy)", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
        cusList = Arrays.copyOf(cusList, cusList.length + 1);
        cusList[existedCustomer++] = new Customer(id, firstName, lastName, nationality, phoneNumber, address, dateOfBirth);
        System.out.println("New customer has been added successfully");
    }

    
    @Override
    public void update() {
        String id = MyUtil.getString("Input customer id: ", "The customer id is required");
        Customer x = searchObjectById(id);
        if(x == null){
            System.out.println("Not found!!");
            return;
        }
        System.out.println("Here is the customer that you want to update");
        System.out.println(header);
        x.display();
        Menu updateMenu = new Menu("Update customer");
        updateMenu.addNewOption("1. Update new fisrt name");
        updateMenu.addNewOption("2. Update new last name");
        updateMenu.addNewOption("3. Update new nationality");
        updateMenu.addNewOption("4. Update new phone");
        updateMenu.addNewOption("5. Update new address");
        updateMenu.addNewOption("6. Update new birthday");        
        updateMenu.addNewOption("7. Exit");
        int choice;
        do {            
            updateMenu.printMenu();
            choice = updateMenu.getChoice();
            switch (choice) {
                 case 1:
                    String newFisrtName = MyUtil.getString("Input new fisrt name: ", "The fisrt name is required");
                    x.setFirstName(newFisrtName);
                    break;
                case 2:
                    String newLastName = MyUtil.getString("Input new last name: ", "The last name is required");
                    x.setLastName(newLastName);
                    break;
                    
                case 3:
                    String newNationality = MyUtil.getString("Input new nationality", "The nationality is required");
                    x.setNationality(newNationality);
                    break;
                case 4:
                    String newPhoneNumber = MyUtil.getString("Input new phone: ", "The phone is required");
                    x.setPhoneNumber(newPhoneNumber);
                    break;  
                case 5:
                    String newAddress = MyUtil.getString("Input new address: ", "The address is required");
                    x.setAddress(newAddress);
                    break;
                
                case 6:
                    LocalDate newBirthday = MyUtil.getDate("Input new bithrday (dd-mm-yyyy): ",
                            "Please input folowing format(dd-mm-yyyy)", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    x.setDateOfBirth(newBirthday);
                    break;   
            }
            
             if(choice != 7){
                    System.out.println("Update successfully");
                    System.out.println("The customer after updating");
                    System.out.println(header);
                    x.display();
                    System.out.println("Press enter to continute...");
                    sc.nextLine();
                }
            
        } while (choice != 7);
        
    }

    
    @Override
    public void remove() {
        String id = MyUtil.getString("Input customer id: ", "The customer id is required");
        int pos = searchById(id);
        if (pos < 0) {
            System.out.println("Not found!!");
            return;
        }
        String choice;
        do {
            System.out.print("Choose Y/N: ");
            choice = sc.nextLine().toLowerCase().trim();
            if (choice.equalsIgnoreCase("Y")) {
                for (int i = pos; i < existedCustomer - 1; i++) 
                    cusList[i] = cusList[i + 1]; // dịch chuyển những con trỏ từ vị trí pos sang bên trái cho đến khi hết mảng
                
                cusList = Arrays.copyOf(cusList, cusList.length - 1); // sau khi di chuyển thì phần tử cuối cùng vẫn tồn tại chưa biến mất hoàn toàn
                // cần phải cấp phát lại, giảm 1 ô nhớ để vừa đủ danh sách
                existedCustomer--;
                System.out.println("Customer is removed successfully");
                return;
            }
            else if(choice.equalsIgnoreCase("N"))
                return;
            else{
                System.out.println("Please choose Y/N"); 
            }

        } while (true);
    }

    
    @Override
    public void printListAscendingById() {
        if(existedCustomer == 0){
            System.out.println("Empty List");
            return;
        }
        Arrays.sort(cusList, (o1, o2) -> o1.getId().compareToIgnoreCase(o2.getId()));
        System.out.println(header);
        for (Customer c : cusList) {
            c.display();
        }
        
    }
    // in danh sach tang ten theo ten(first name)
    public void printListAscendingByName(){ 
        if(existedCustomer == 0){
            System.out.println("Empty List");
            return;
        }
        Arrays.sort(cusList, (o1, o2) -> o1.getFirstName().compareToIgnoreCase(o2.getFirstName())); // sap xep tang dan theo ten, su dung lambda expression
        System.out.println(header);
        for (Customer c : cusList) {
            c.display();
        }
        
    }

    
    @Override
    public void searchById() {
        String id = MyUtil.getString("Input customer id: ", "The customer id is required");
        Customer x = searchObjectById(id);
        if (x == null) {
            System.out.println("Not found!!");
            return;
        }
        System.out.println("Here is your customer that you're searching");
        System.out.println(header);
        x.display();
    }

    
    public int searchById(String id) {
        if(existedCustomer == 0) return -1;
        for (int i = 0; i < existedCustomer; i++) {
            if(cusList[i].getId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    
    @Override
    public Customer searchObjectById(String id) {
        if(cusList.length == 0) return null;
        for (Customer c : cusList) {
            if(c.getId().equalsIgnoreCase(id))
                return c;
        }
        return null;
    }
    
    // tìm kiếm theo tên, nên tìm kiếm tên gần đúng
    public Customer[] searchCustomerByName(String name){ // nên trả về mảng vì sẽ có nhiều người trùng tên
        Customer [] result = new Customer[0];
        int count = 0;
        
        for (Customer c : cusList) {
            if(c.getFirstName().contains(name) || c.getLastName().contains(name)){ // sử dụng hàm contain 
                                                                                    // hàm contain để xem trong chuỗi của mình
                                                                                    // có tồn tại cái chuỗi đang kiếm không
                                                                                    // có thì sẽ trả về true,
                result = Arrays.copyOf(result, count + 1);
                result[count++] = c;
            }
        }
        return result;
    }
    
    // xuat ra danh sách những người có tên đang tim kiếm
    public void searchCustomerByName(){
        
        String name = MyUtil.getString("Input customer name: ", "The customer is required");
        Customer [] result = searchCustomerByName(name);
        if(result.length == 0) {
            System.out.println("Not Found!!");
            return;
        }
        System.out.println(header);
        for (Customer r : result) {
            r.display();
        }
    }
    
    
    @Override
    public void ReadData(LoadData loadData) {
        Object[] obj = loadData.read();
        
        for (Object o : obj) {
            cusList = Arrays.copyOf(cusList, existedCustomer + 1);
            cusList[existedCustomer++] = (Customer)o;
        }
    }

    public String getIdCustomer() {
        System.out.printf("|%-10s|%-25s|\n", "ID", "FULLNAME");
        for (Customer customer : cusList) {
            System.out.printf("|%-10s|%-25s|\n", customer.getId(), customer.getLastName() + " " + customer.getFirstName());
        }
        Customer x;
        String idCustomer;
        do {            
        idCustomer = MyUtil.getString("Input id customer (CXXX): ", "The customer id is required");
        x = searchObjectById(idCustomer);
        if(x == null)
            System.out.println("Please input the customer has in the list");
        
        } while (x == null);
        
        return idCustomer;
    }
    
    @Override
    public void saveToDate(SaveData saveData) {
        saveData.save(cusList, header);
    }
    
    
    public void countToursPerCustomer() {
        InvoiceDetailsList invDetailList = InvoiceDetailsList.getInstance();
       Map <Customer, Long>  customerTourCount = new HashMap();
        for (Customer c : cusList) {
            long total = 0;
            InvoiceDetails[] invoiceListCustomer = invDetailList.getInvoiceDetails(o -> o.getCustomerId().equalsIgnoreCase(c.getId()));
            for (InvoiceDetails i : invoiceListCustomer) {
                total += i.getPrice();
            }
            customerTourCount.put(c, total);
        }
        System.out.printf("|%-6s|%-25s|%-15s|\n", "ID", "FULLNAME", "TOTAL");
        for (Map.Entry<Customer, Long> c: customerTourCount.entrySet()) {
            String id = c.getKey().getId();
            String fullName = c.getKey().getLastName() + " " + c.getKey().getFirstName();
            Long total = c.getValue();
            System.out.printf("|%-6s|%-25s|%-15d|\n", id, fullName, total);
        }
    }
    //test bam shift + f6 de test thu
    public static void main(String[] args) {
        CustomerList cl = CustomerList.getInstance();
        cl.countToursPerCustomer();
//        cl.add();
//        cl.printListAscendingById();

//        cl.saveToDate(new SaveFileText("FileText/Customers.txt"));
//        
        cl.saveToDate(new SaveDataToFile("Files/Customers.dat"));
    }
      
}
