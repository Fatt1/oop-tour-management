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
    private InvoiceDetailsList invoiceDetaiList;
    private double totalAmount;

    public Invoice(String id, String customerId, String employeeId, String tourScheduleId, InvoiceDetailsList invoiceDetaiList) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.tourScheduleId = tourScheduleId;
        this.invoiceDate = LocalDateTime.now();
        this.totalAmount = 0;
        this.invoiceDetaiList = invoiceDetaiList;
    }

    public InvoiceDetailsList getInvoiceDetaiList() {
        return invoiceDetaiList;
    }

    public void setInvoiceDetaiList(InvoiceDetailsList invoiceDetaiList) {
        this.invoiceDetaiList = invoiceDetaiList;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", customerId=" + customerId + ", employeeId=" + employeeId + ", tourScheduleId=" + tourScheduleId + ", invoiceDate=" + invoiceDate + ", totalAmount=" + totalAmount + '}';
    }
    
    public void display(){
        System.out.printf("|%-9s|%-12s|%-14s|%-20s|%-25s|%-10.2f|\n",
                            id, customerId, employeeId, tourScheduleId, invoiceDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), totalAmount);
    }
    
}
