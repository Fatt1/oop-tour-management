/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lists;

import model.InvoiceDetails;
import IOFile.LoadDataFromFile;
import IOFile.SaveDataToFile;
import IOFile.SaveFileText;
import interfaces.Filter;
import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;
import model.Customer;
import model.Invoice;
import ui.Menu;
import util.MyUtil;

/**
 *
 * @author User
 */
public class InvoiceDetailsList implements Serializable{
    private static InvoiceDetailsList instance;
    private InvoiceDetails [] invDetailsList;
    private int exsitedInvoiceDetails;
    private String header = String.format("|%-12s|%-12s|%-10s|\n","INVOICE ID", "CUSTOMER ID", "PRICE");
    private SaveDataToFile saveBinaryFile = new SaveDataToFile("Files/InvoiceDetails.dat");
    
    private InvoiceDetailsList() {
        invDetailsList = new InvoiceDetails[0];
        exsitedInvoiceDetails = 0;
        ReadData(new LoadDataFromFile("Files/InvoiceDetails.dat"));
        
    }
    
    public static InvoiceDetailsList getInstance() {
        if(instance == null)
            instance = new InvoiceDetailsList();
        return instance;
    }
    
    
    public void add(String invoiceId){
        // còn phải code việc bắt không cho trùng id khách hàng nếu 1 chi tiết hóa đơn mà có 2 người
        String customerId = getUniqueAnCustomer(invoiceId);
        double price = getCustomerPrice(customerId);
        invDetailsList = Arrays.copyOf(invDetailsList, exsitedInvoiceDetails + 1);
        invDetailsList[exsitedInvoiceDetails++] = new InvoiceDetails(invoiceId,customerId, price);
        System.out.println("Add successfully");
        saveToDate(saveBinaryFile);
        showInvoiceDetails(invoiceId);
        
        
    }
    
    private double getCustomerPrice (String customerId) {
        double customerPrice = 0;
        CustomerList cusList = CustomerList.getInstance();
        Customer x = cusList.searchObjectById(customerId);
        if(x == null){
            return customerPrice;
        }
        else if(x.getAge() >= 14) {
            customerPrice = 20.99; // tính theo tiền đô, đây là ví dụ, vì vẫn chưa có TourScheduleList để lấy giá người lớn và giá trẻ em
        }
        else if(x.getAge() < 14) {
            customerPrice = 10.99;
        }
        return customerPrice;
    }
    
    public double getTotalPrice(String invoiceId){
        double totalPrice = 0;
        for (InvoiceDetails invDetails : invDetailsList) {
            if(invDetails.getInvoiceId().equalsIgnoreCase(invoiceId))
                totalPrice += invDetails.getPrice();
        }
        return totalPrice;
    }
    
    
    
    public void update(String invoiceId) {
                       
        String customerId = MyUtil.getString("Input customer id: ", "The customer id is required");
        InvoiceDetails x = searchObjectById(invoiceId, customerId);
        if(x == null){
            System.out.println("Not found!!");
            return;
        }
        System.out.println("Here is the customer' invoice details that you want to update");
        x.display();
        String newCustomerId = getUniqueAnCustomer(invoiceId);
        x.setCustomerId(newCustomerId);
        x.setPrice(getCustomerPrice(newCustomerId));
        System.out.println("Update successfully");
        saveToDate(saveBinaryFile);
        System.out.println("List after updating");
        showInvoiceDetails(invoiceId);
        
    }
    
    
    private String getUniqueAnCustomer(String invoiceId){
        String customerId;
        int duplicate;
        
        do {    
            customerId = CustomerList.getInstance().getIdCustomer();
            duplicate = searchById(customerId, invoiceId);
            if(duplicate >= 0){
                System.out.println("In the invoice details have had this customer. Please input another customer id");
                System.out.print("Press enter to continue...");
                new Scanner(System.in).nextLine();
            }
        } while (duplicate >= 0);
        return customerId;
    }
    

    
    public InvoiceDetails[] getInvoiceDetails(Filter<InvoiceDetails> filter){
        InvoiceDetails[] result = new InvoiceDetails[0];
        int count = 0;
        for (InvoiceDetails x : invDetailsList) {
            if(filter.check(x)){
                result = Arrays.copyOf(result, count + 1);
                result[count++] = x;
            }
        }
        return result;
    }
    

    
    public void editInvoiceDetails(Invoice x) {
        Menu updateDetailMenu = new Menu("Choose");
        updateDetailMenu.addNewOption("1. Add");
        updateDetailMenu.addNewOption("2. Update customer id"); 
        updateDetailMenu.addNewOption("3. Remove");
        updateDetailMenu.addNewOption("4. Exit");
        int choice;
        
        do {
            updateDetailMenu.printMenu();
            choice = updateDetailMenu.getChoice();
            switch (choice) {
            case 1:
                add(x.getId());
                
                break;    
                
            case 2:
                update(x.getId());
                
                break;
            case 3:
                remove(x.getId());
                break;
            }
            if(choice != 4){
                x.setTotalAmount(getTotalPrice(x.getId()));
                
            }
                // cập nhật lại giá tổng vì có thể chỉnh lại id Khách hàng khách
                                                                            // thì giá sẽ khác nên tổng giá sẽ khác.
        } while (choice != 4);
        
    }
    
    public void remove(String invoiceId) { // cần thêm cái invoice nào để biết xóa chính xóa
        Scanner sc = new Scanner(System.in); 
        String customerId = MyUtil.getString("Input customer id: ", "The customer id is required");
        int pos = searchById(customerId, invoiceId);
        if(pos == -1){
            System.out.println("Not found!!");
            return;
        }
        
        String choice;
        do {            
            System.out.print("Choose Y/N: ");
            choice = sc.nextLine();
            if(choice.equalsIgnoreCase("Y")){
                for (int i = pos; i < exsitedInvoiceDetails - 1; i++) 
                    invDetailsList[i] = invDetailsList[i + 1];
                invDetailsList = Arrays.copyOf(invDetailsList, exsitedInvoiceDetails - 1);
                exsitedInvoiceDetails--;
                System.out.println("The invoice details is removed successully");
                saveToDate(saveBinaryFile);
                System.out.println("List after deleting");
                showInvoiceDetails(invoiceId);
                return;
            }
            else if(choice.equalsIgnoreCase("N"))
                return;
            else{
                System.out.println("Please choose Y/N");
            }
        } while (true);
        
       
    }

    public int searchById(String customerId, String invoiceId) {
        if(exsitedInvoiceDetails == 0) return -1;
        for (int i = 0; i < exsitedInvoiceDetails; i++) {
            InvoiceDetails x = invDetailsList[i];
            if(x.getCustomerId().equalsIgnoreCase(customerId) && x.getInvoiceId().equalsIgnoreCase(invoiceId))
                return i;
        }
        return -1;
    }

    
    
    
    public void showInvoiceDetails (String invoiceId){ // cái hàm để dùng bên invoice để show chi tiết của invoice
        CustomerList cusList = CustomerList.getInstance();
        System.out.println("Invoice Details List");
        InvoiceDetails result[] = getInvoiceDetails(o -> o.getInvoiceId().equalsIgnoreCase(invoiceId)); // trả về mảng invoiceId mà mình muốn tìm
        System.out.printf("|%-20s|%-12s|%-10s|\n", "CUSTOMER NAME", "CUSTOMER ID", "PRICE");
        for (InvoiceDetails x : result) {
            Customer cus = cusList.searchObjectById(x.getCustomerId());
            System.out.printf("|%-20s",cus.getLastName() + " " + cus.getFirstName());
            x.display();
        }
        
    }
    
    public InvoiceDetails searchObjectById(String invoiceId, String customerId) {
        if(exsitedInvoiceDetails == 0) return null;
        for (InvoiceDetails invoiceDetails : invDetailsList) {
            if(invoiceDetails.getCustomerId().equalsIgnoreCase(customerId) && invoiceDetails.getInvoiceId().equalsIgnoreCase(invoiceId))
                return invoiceDetails;
        }
        return null;
    }

    
    public void ReadData(LoadData loadData) {
        Object [] object = loadData.read();
        if(object == null){
            System.out.println("No data");
            return;
        }
        for (Object o : object) {
        invDetailsList = Arrays.copyOf(invDetailsList, exsitedInvoiceDetails + 1);
        invDetailsList[exsitedInvoiceDetails++] = (InvoiceDetails)o;
        }
    }

    
    public void saveToDate(SaveData saveData) {
        saveData.save(invDetailsList, header);
    }
    
}
