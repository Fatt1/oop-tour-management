/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lists;

import model.InvoiceDetails;
import IOFile.LoadDataFromFile;
import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;
import model.Customer;
import util.MyUtil;

/**
 *
 * @author User
 */
public class InvoiceDetailsList implements IManager<InvoiceDetails>, Serializable{
    private InvoiceDetails [] invDetailsList;
    private int exsitedInvoiceDetails;
    private String tourScheduleId;
    
    
    public InvoiceDetailsList(String tourScheduleId) {
        invDetailsList = new InvoiceDetails[0];
        exsitedInvoiceDetails = 0;
        this.tourScheduleId = tourScheduleId;
    }
    @Override
    public void add(){
        // còn phải code việc bắt không cho trùng id khách hàng nếu 1 chi tiết hóa đơn mà có 2 người
        String customerId = MyUtil.getString("Input customer id: ", "The customer id is required");
        double price = getCustomerPrice(customerId);
        invDetailsList = Arrays.copyOf(invDetailsList, exsitedInvoiceDetails + 1);
        invDetailsList[exsitedInvoiceDetails++] = new InvoiceDetails(tourScheduleId,customerId, price);
        System.out.println("Add successfully");
        printListAscendingById();
        
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
    
    public double getTotalPrice(){
        double totalPrice = 0;
        for (InvoiceDetails invDetails : invDetailsList) {
            totalPrice += invDetails.getPrice();
        }
        return totalPrice;
    }
    
    @Override
    public void update() {
                       
        String customerId = MyUtil.getString("Input customer id: ", "The customer id is required");
        InvoiceDetails x = searchObjectById(customerId);
        if(x == null){
            System.out.println("Not found!!");
            return;
        }
        System.out.println("Here is the customer' invoice details that you want to update");
        x.display();
        String newCustomerId = MyUtil.getString("Input new customer id (CXXX): ", "The new customer id is required");
        x.setCustomerId(newCustomerId);
        x.setPrice(getCustomerPrice(newCustomerId));
        System.out.println("Update successfully");
        System.out.println("List after updating");
        printListAscendingById();
            
    }

    @Override
    public void remove() {
        Scanner sc = new Scanner(System.in); 
        String customerId = MyUtil.getString("Input customer id: ", "The customer id is required");
        int pos = searchById(customerId);
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
                System.out.println("List after deleting");
                printListAscendingById();
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
    public void printListAscendingById() { // in ra tang dan theo id cua khach hang
        if(exsitedInvoiceDetails == 0) {
            System.out.println("Empty List!!");
            return;
        }
        CustomerList cusList = CustomerList.getInstance();
        
        Arrays.sort(invDetailsList, (o1, o2) -> o1.getCustomerId().compareToIgnoreCase(o2.getCustomerId()));
        System.out.printf("|%-20s|%-12s|%-10s|\n", "CUSTOMER NAME", "CUSTOMER ID", "PRICE");
        for (InvoiceDetails invDetails : invDetailsList) {
            Customer x = cusList.searchObjectById(invDetails.getCustomerId());
            if(x == null){
                continue;
            }
            
            System.out.printf("|%-20s",x.getLastName() + " " + x.getFirstName());
            invDetails.display();
        }
    }

    @Override
    public void searchById() {
        String customerId = MyUtil.getString("Input customer id: ", "The customer id is required");
        InvoiceDetails x = searchObjectById(customerId);
        if(x == null) {
            System.out.println("Not found!!");
            return;
        }
        System.out.println("Here is customer's the invoice details");
        x.display();
        
    }

    @Override
    public int searchById(String id) {
        if(exsitedInvoiceDetails == 0) return -1;
        for (int i = 0; i < exsitedInvoiceDetails; i++) {
            if(invDetailsList[i].getCustomerId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    @Override
    public InvoiceDetails searchObjectById(String id) {
        if(exsitedInvoiceDetails == 0) return null;
        for (InvoiceDetails invoiceDetails : invDetailsList) {
            if(invoiceDetails.getCustomerId().equalsIgnoreCase(id))
                return invoiceDetails;
        }
        return null;
    }

    @Override
    public void ReadData(LoadData loadData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void saveToDate(SaveData saveData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
