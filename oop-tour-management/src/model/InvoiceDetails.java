/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class InvoiceDetails implements Serializable{
    private String invoiceId;
    private String customerId;
    private double price;

    public InvoiceDetails(String invoiceId,String customerId, double price) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.price = price;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public String toString() {
        return String.format("|%-12s|%-12s|%10.2f|\n",invoiceId, customerId, price);
    }
    
    public void display(){
        System.out.printf("|%-12s|%10.2f|\n", customerId, price);
    }
}