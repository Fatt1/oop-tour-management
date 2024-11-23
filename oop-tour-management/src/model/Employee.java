package model;

import java.io.Serializable;
import model.Employee.Gender;
import model.Employee.Title;

public class Employee implements Serializable{
	
    public enum Gender {
        Male, Female, Other
    }

    public enum Title {
        Cashier, Guide, Accountant
    }


    private String employeeId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private String address;
    private Title title;

    public Employee() {
        this.employeeId = "";
        this.firstName = "";
        this.lastName = "";
        this.gender = Gender.Male;
        this.phoneNumber = "";
        this.address = "";
        this.title = Title.Cashier;  // Sửa lại tên enum
    }

    public Employee(String employeeId, String firstName, String lastName, int optionGender, String phoneNumber, String address, int optionTitle) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        switch (optionGender){
        	case 1: this.gender=Gender.Male;break;
        	case 2: this.gender=Gender.Female;break;
        	case 3: this.gender=Gender.Other;break;
        }
        this.phoneNumber = phoneNumber;
        this.address = address;
        switch(optionTitle)
        {
        	case 1: this.title=Title.Cashier;break;
        	case 2: this.title=Title.Guide;break;
        	case 3: this.title=Title.Accountant;break;
        }
    }

    public Employee(Employee employee1) {
    	employeeId = employee1.employeeId ;
        firstName = employee1.firstName;
        lastName = employee1.lastName;
        gender = employee1.gender;
        phoneNumber = employee1.phoneNumber;
        address = employee1.address;
        title = employee1.title;
    }

    public String getEmployeeId() {
        return employeeId ;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(int optionGender) {
    	switch(optionGender) {
    	case 1:gender =Gender.Male;break;
    	case 2:gender = Gender.Female;break;
    	case 3:gender = Gender.Other;break;
    	}
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(int optionTitle) {
    	switch(optionTitle){
		case 1: title =Title.Cashier;break;
		case 2: title =Title.Guide;break;
		case 3: title =Title.Accountant;break;
	}
    }
    @Override
    public String toString() {
        return String.format("|%-6s|%-25s|%-15s|%-14s|%-25s|%-15s|\n",
                            employeeId , (firstName + " " + lastName), gender,phoneNumber, address,title);
    }
    public void display() { 
        System.out.printf(this.toString());
    }
}
