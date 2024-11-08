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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import model.Invoice;
import ui.Menu;
import util.MyUtil;

/**
 *
 * @author User
 */
public class InvoiceList implements IManager<Invoice>{
    private Scanner sc = new Scanner(System.in);
    private Invoice[] invoiceList;
    private int existedInvoice;
    private int surrogateKey = 0;
    private String header = String.format("|%-9s|%-12s|%-14s|%-20s|%-25s|%-10s|",
                                            "ID", "CUSTOMER ID", "EMPLOYEE ID","TOUR SCHEDULE ID", "DATE", "TOTAL");
    public InvoiceList() {
        invoiceList = new Invoice[0];
        existedInvoice = 0;
    }

    @Override
    public void add() {
        String formattedID = String.format("INV-%03d", ++surrogateKey); // %03d có nghĩa là mặc định sẽ có 3 số 
                                                                        // nếu surrogateKey nếu không đủ 3 số nó sẽ tự thêm số 0 đằng trước để đủ định dạng 3 số
                                                                        // nếu surrogateKey = 1 thì sẽ tự động thêm 00 vào trc và tạo thành 001
        System.out.println("Invoice Id: " + formattedID);
        // có nên bắt lỗi khi nhập customerId, employeeId,... không. Vì id phải tồn tại mới cho nhập
        String customerId = MyUtil.getString("Input customer id (CXXX): ", "The customer id is required");
        String employeeId = MyUtil.getString("Input employee id (EXXX): ", "The employee id is required");
        String tourScheduleId = MyUtil.getString("Input tour schedule id: ", "The employee id is required");
        InvoiceDetailsList invoiceDetailList = new InvoiceDetailsList(tourScheduleId);
        
        System.out.println("Input invoice details");
        do {            
            
            invoiceDetailList.add();
            String choice = getUserConfirmation();
            if(choice.equalsIgnoreCase("N"))
                break;
            
        } while (true);
        invoiceList = Arrays.copyOf(invoiceList, existedInvoice + 1);
        Invoice invoice = new Invoice(formattedID,customerId, employeeId, tourScheduleId, invoiceDetailList);
        invoice.setTotalAmount(invoiceDetailList.getTotalPrice()); // set total amount sau khi nhập chi tiết hóa đơn
        invoiceList[existedInvoice++] = invoice;
        System.out.println("New invoice has been added successfully");
    }
    
    private String getUserConfirmation() {
       do {               
        String choice;
        System.out.print("Do you want to continute to input(Y/N): ");
        choice = sc.nextLine();
        if(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N"))
            return choice;
        if(!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y"))
            System.out.println("Please choose Y/N");       
        } while (true);
    }
    
    @Override
    public void update() {
        String id = MyUtil.getString("Input invoice id: ", "The invoice id is required");
        Invoice x = searchObjectById(id);
        if(x == null){
            System.out.println("Not found!!");
            return;
        }
        System.out.println("Here is the invoice that you want to update");
        System.out.println(header);
        x.display();
        Menu menuUpdate = new Menu("Update invoice");
        menuUpdate.addNewOption("1. Update customer id");
        menuUpdate.addNewOption("2. Update employee id");
        menuUpdate.addNewOption("3. Update tour schedule id");
        menuUpdate.addNewOption("4. Exit");
        
        int choice;
        do {
            menuUpdate.printMenu();
            choice = menuUpdate.getChoice();
            switch (choice) {
                case 1: // những cái customerId, emloyeeId,... nên hiện ra 1 cái list để lựa chọn instead of nhập mà kh nhìn thấy gì
                    String newCustomerId = MyUtil.getString("Input new customer id(CXXX): ", "The customer id is required");
                    x.setCustomerId(newCustomerId);
                    break;
                
                case 2:
                    String newEmployeeId = MyUtil.getString("Input new employee id(CXXX): ", "The employee id is required");
                    x.setEmployeeId(newEmployeeId);
                    break;
                            
                case 3:
                    String newScheduleId = MyUtil.getString("Input new tour schedule id: ", "The tour schedule id is required");
                    x.setTourScheduleId(newScheduleId);
                    break;
                
            }
            if(choice != 4){
                x.setInvoiceDate(LocalDateTime.now()); // update luôn thời gian mình cập nhật lại cái invoice
                System.out.println("Update successully");
                System.out.println("The invoice after updating");
                x.display();
                System.out.println("Press enter to continue...");
                sc.nextLine();
            }
        } while (choice != 4);
        
    }

    @Override
    public void remove() {
        String id = MyUtil.getString("Input invoice id: ", "The invoice id is required");
        int pos = searchById(id);
        if(pos == -1){
            System.out.println("Not found!!");
            return;
        }
        // lúc xóa thì nên hỏi lại người dùng chắc chắn muốn xóa hay không, cho ngta cơ hội đc chọn!!
        String choice;
        do {            
            System.out.print("Choose Y/N: ");
            choice = sc.nextLine();
            if(choice.equalsIgnoreCase("Y")){
                for (int i = pos; i < existedInvoice - 1; i++) 
                    invoiceList[i] = invoiceList[i + 1];
                invoiceList = Arrays.copyOf(invoiceList, existedInvoice - 1);
                existedInvoice--;
                System.out.println("The invoice is removed successully");
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
        if(existedInvoice == 0) {
            System.out.println("Empty List!!");
            return;
        }
        
        Arrays.sort(invoiceList, (o1, o2) -> o1.getId().compareToIgnoreCase(o2.getId()));
        System.out.println(header);
        for (Invoice inv : invoiceList) {
            inv.display();
        }
        
    }

    @Override
    public void searchById() {
        String id = MyUtil.getString("Input invoice id: ", "The invoice id is required");
        Invoice x = searchObjectById(id);
        if(x == null){
            System.out.println("Not found!!");
            return;
        }
        System.out.println("Here is the invoice that you're searching");
        System.out.println(header);
        x.display();
    }

    @Override
    public int searchById(String id) {
        if(existedInvoice == 0) return -1;
        for (int i = 0; i < existedInvoice; i++) {
            if(invoiceList[i].getId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    @Override
    public Invoice searchObjectById(String id) {
        if(existedInvoice == 0) return null;
        for (int i = 0; i < existedInvoice; i++) {
            if(invoiceList[i].getId().equalsIgnoreCase(id))
                return invoiceList[i];
        }
        return null;
    }
    
    public void showInvoiceDetails (){
        
        String id = MyUtil.getString("Input invoice id: ", "The invoice id is required");
        Invoice x = searchObjectById(id);
        if(x == null) {
            System.out.println("Not found!!");
            return;
        }
        System.out.println("Invoice Details List");
        x.getInvoiceDetaiList().printListAscendingById();
        updateInvoiceDetails(x);
        
    }
    
    public void updateInvoiceDetails(Invoice x) {
        Menu updateDetailMenu = new Menu("Choose");
        updateDetailMenu.addNewOption("1. Add");
        updateDetailMenu.addNewOption("2. Update customer id"); // còn delete nữa, từ từ...
        updateDetailMenu.addNewOption("3. Remove");
        updateDetailMenu.addNewOption("4. Exit");
        int choice;
        
        do {
            updateDetailMenu.printMenu();
            choice = updateDetailMenu.getChoice();
            switch (choice) {
            case 1:
                x.getInvoiceDetaiList().add();
                
                break;    
                
            case 2:
                x.getInvoiceDetaiList().update();
                
                break;
            case 3:
                x.getInvoiceDetaiList().remove();
                break;
            }
            if(choice != 4){
                x.setTotalAmount(x.getInvoiceDetaiList().getTotalPrice()); 
                
            }
                // cập nhật lại giá tổng vì có thể chỉnh lại id Khách hàng khách
                                                                            // thì giá sẽ khác nên tổng giá sẽ khác.
        } while (choice != 4);
        
    }
    
    @Override
    public void ReadData(LoadData loadData) {
      
        Object[] obj = loadData.read();
          for (Object o : obj) {
         invoiceList = Arrays.copyOf(invoiceList, existedInvoice + 1); // load dữ liệu từ file rồi gán vào invoiceList
         Invoice invoice = (Invoice) o;
         int getSurrogateKey = Integer.parseInt(invoice.getId().substring(4)); // lấy ra cái surrogateKey của thằng mình đọc ra
         if(getSurrogateKey > surrogateKey)  // sau đó check xem nó lớn hơn cái hiện tại không thì đổi
            surrogateKey = getSurrogateKey;
         
         invoiceList[existedInvoice++] = invoice;

          }
    }

    @Override
    public void saveToDate(SaveData saveData) {
        saveData.save(invoiceList,"");
    }
   
    //test, xóa comment, bấm shift + F6 để test
    public static void main(String[] args) {
        InvoiceList i = new InvoiceList();
        i.ReadData(new LoadDataFromFile("Files/Invoices.dat"));
//        i.add();
//        i.remove();
        i.add();
        
        i.printListAscendingById();
//        i.searchById();
        i.showInvoiceDetails();
        
        i.saveToDate(new SaveDataToFile("Files/Invoices.dat"));
    }
}
