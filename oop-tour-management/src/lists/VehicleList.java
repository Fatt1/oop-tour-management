/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lists;

import IOFile.LoadDataFromFile;
import IOFile.SaveDataToFile;
import interfaces.IManager;
import interfaces.LoadData;
import interfaces.SaveData;
import java.util.Arrays;
import model.Vehicle;
import util.MyUtil;

/**
 *
 * @author User
 */
public class VehicleList implements IManager<Vehicle>{
    private Vehicle[] vehicleList;
    private int existedVehicle;
    private String header = String.format("|%-6s|%-15s|%-15s|%-14s|%-6s|", "ID", "NAME", "COMPANY", "PHONE", "SEATS");
    public VehicleList() {
        vehicleList = new Vehicle[0];
        existedVehicle = 0;
    }

    @Override
    public void add() {
        String id;
        int duplicateId;
        do {            
            id  = MyUtil.getId("Input vehicle id (VXXX): ", "The format of Id is (VXXX) "
                + "X stands for digit(0-9)", "^[v|V]\\d{3}$");
            duplicateId = searchById(id);
            if(duplicateId >= 0){
                System.out.println("ID is duplicate. Please input another id!");
            }
        } while (duplicateId >= 0);
        String name = MyUtil.getString("Input vehicle name: ", "The name vehicle is required");
        String company = MyUtil.getString("Input company vehicle: ", "The company is required");
        String phoneNumber = MyUtil.getString("Input phone vehicle: ", "The phone is required");
        int numberOfSeats = MyUtil.getAnInteger("Input number of seat (1 -> 100): ", "Number of seat is from 1...100", 1, 100);
        vehicleList = Arrays.copyOf(vehicleList, vehicleList.length + 1);
        vehicleList[existedVehicle++] = new Vehicle(id, name, company, phoneNumber, numberOfSeats);
        System.out.println("Vehicle has been added successully");
        
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void printListAscendingById() {
        if(vehicleList.length == 0){
            System.out.println("Empty List!!");
            return;
        }
        System.out.println(header);
        Arrays.sort(vehicleList, (o1, o2) -> o1.getId().compareToIgnoreCase(o2.getId())); // Lambda Expression
        
        for (Vehicle v : vehicleList) {
            v.display();
        }
    }

    @Override
    public void searchById() {
        String id = MyUtil.getString("Input vehicle id: ", "The vehicle id is required");
        Vehicle x  = searchObjectById(id);
        if(x == null){
            System.out.println("Not found!!");
            return;
        }
        System.out.println("Here is your vehicle that you're searching");
        System.out.println(header);
        x.display();
        
    }

    @Override
    public int searchById(String id) {
        if(vehicleList.length == 0) 
            return -1;
        for (int i = 0; i < existedVehicle; i++) {
            if(vehicleList[i].getId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    @Override
    public Vehicle searchObjectById(String id) {
         if(vehicleList.length == 0) 
            return null;
        for (Vehicle v : vehicleList) {
            if(v.getId().equalsIgnoreCase(id))
                return v;
        }
        return null;
    }

    @Override
    public void ReadData(LoadData loadData) {
        Object [] obj = loadData.read(); // vì hàm loadData.read trả về mảng Object[] nên phải khái báo 1 mảng object
                                         // không thể ép kiểu về như vehicleList = (Vehicle[])loadData.read()
                                         // vì java không cho phép ép 1 mảng tổng quát về 1 mảng cụ thể
        for (Object o : obj) {
            vehicleList = Arrays.copyOf(vehicleList, vehicleList.length + 1);
            vehicleList[existedVehicle++] = (Vehicle) o; // chỉ có thể ép kiểu từ 1 Object về 1 object cụ thể
                                                         // cho nên để giải quyế phải ép kiểu cho từng phần từ 1
        }
         
    }

    @Override
    public void saveToDate(SaveData saveData) {
        saveData.save(vehicleList);
    }
    
    // test
    public static void main(String[] args) {
        VehicleList v = new VehicleList();
        v.ReadData(new LoadDataFromFile("Files/Vehicles.dat"));
        
        v.printListAscendingById();
        
        v.saveToDate(new SaveDataToFile("Files/Vehicles.dat"));
        
    }
   
}
