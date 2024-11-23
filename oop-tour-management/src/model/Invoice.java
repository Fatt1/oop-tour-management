/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lists.InvoiceDetailsList;

/**
 *
 * @author User
 */
public class Invoice implements Serializable{
    private String id;
    private String customerId;
    private String employeeId;
    private String tourScheduleId;
    private LocalDateTime invoiceDate;
    private int totalAmount;

    public Invoice(String id, String customerId, String employeeId, String tourScheduleId) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.tourScheduleId = tourScheduleId;
        this.invoiceDate = LocalDateTime.now();
        this.totalAmount = 0;
        
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getTourScheduleId() {
        return tourScheduleId;
    }

    public void setTourScheduleId(String tourScheduleId) {
        this.tourScheduleId = tourScheduleId;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return String.format("|%-9s|%-12s|%-14s|%-20s|%-25s|%-20d|\n",
                            id, customerId, employeeId, tourScheduleId, invoiceDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), totalAmount);
    }
    
    public void display(){
        System.out.printf("|%-9s|%-12s|%-14s|%-20s|%-25s|%-20d|\n",
                            id, customerId, employeeId, tourScheduleId, invoiceDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), totalAmount);
    }
    
}
