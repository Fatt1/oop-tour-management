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
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import model.Invoice;
import model.tour.TourSchedule;
import ui.Menu;
import util.MyUtil;

/**
 *
 * @author User
 */
public class InvoiceList implements IManager<Invoice> {

    private Scanner sc = new Scanner(System.in);
    private static InvoiceList instance;
    private Invoice[] invoiceList;
    private int existedInvoice;
    private int surrogateKey = 0;
    private String header = String.format("|%-9s|%-12s|%-14s|%-20s|%-25s|%-20s|",
            "ID", "CUSTOMER ID", "EMPLOYEE ID", "TOUR SCHEDULE ID", "DATE", "TOTAL");
    private SaveDataToFile saveBinaryFile = new SaveDataToFile("Files/Invoices.dat");

    private InvoiceList() {
        invoiceList = new Invoice[0];
        existedInvoice = 0;
        ReadData(new LoadDataFromFile("Files/Invoices.dat"));
    }

    public static InvoiceList getInstance() {
        if (instance == null) {
            instance = new InvoiceList();
        }
        return instance;
    }

    @Override
    public void add() {
        InvoiceDetailsList invoiceDetailList = InvoiceDetailsList.getInstance();
        TourScheduleList tourScheduleList = TourScheduleList.getInstance();
        String invoiceId = String.format("INV-%03d", ++surrogateKey); // %03d có nghĩa là mặc định sẽ có 3 số 
        // nếu surrogateKey nếu không đủ 3 số nó sẽ tự thêm số 0 đằng trước để đủ định dạng 3 số
        // nếu surrogateKey = 1 thì sẽ tự động thêm 00 vào trc và tạo thành 001
        System.out.println("Invoice Id: " + invoiceId);
        String customerId = CustomerList.getInstance().getIdCustomer();
        String employeeId = MyUtil.getString("Input employee id (EXXX): ", "The employee id is required");
        String tourScheduleId = tourScheduleList.getTourScheduleID();
        TourSchedule tourSchedule = tourScheduleList.searchObjectById(tourScheduleId);
        //check emptySlots trc khi cho nhập chi tiết, nếu mà nó = 0 thì return luôn không cho nhập tiếp
        if (tourSchedule.getEmptySlots() == 0) {
            System.out.println("Slots are full. Can't add anymore");
            return;
        }
        System.out.println("Input invoice details");

        invoiceList = Arrays.copyOf(invoiceList, existedInvoice + 1);
        do {

            invoiceDetailList.add(invoiceId, tourSchedule); // không cần phải nhập lại idInvoice và tourScheduleId trong invoiceDetailsList
            String choice = getUserConfirmation();
            if (choice.equalsIgnoreCase("N")) {
                break;
            }

        } while (true);
        Invoice invoice = new Invoice(invoiceId, customerId, employeeId, tourScheduleId);

        invoice.setTotalAmount(invoiceDetailList.getTotalPrice(invoiceId)); // set total amount sau khi nhập chi tiết hóa đơn
        invoiceList[existedInvoice++] = invoice;
        System.out.println("New invoice has been added successfully");
        saveToDate(saveBinaryFile);
        // lưu lại những emptySlots đã thay đỏi sau khi nhập invoiceDetails
        tourScheduleList.saveToDate(new SaveDataToFile("Files/TourSchedule.dat"));
    }

    private String getUserConfirmation() {
        do {
            String choice;
            System.out.print("Do you want to continute to input(Y/N): ");
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")) {
                return choice;
            }
            if (!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y")) {
                System.out.println("Please choose Y/N");
            }
        } while (true);
    }

    @Override
    public void update() {
        String id = MyUtil.getString("Input invoice id: ", "The invoice id is required");
        Invoice x = searchObjectById(id);
        if (x == null) {
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
                    String newCustomerId = CustomerList.getInstance().getIdCustomer();
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
            if (choice != 4) {
                System.out.println("Update successully");
                System.out.println("The invoice after updating");
                x.display();
                saveToDate(saveBinaryFile);
                System.out.print("Press enter to continue...");
                sc.nextLine();
            }
        } while (choice != 4);

    }

    @Override
    public void remove() {
        String id = MyUtil.getString("Input invoice id: ", "The invoice id is required");
        int pos = searchById(id);
        if (pos == -1) {
            System.out.println("Not found!!");
            return;
        }
        // lúc xóa thì nên hỏi lại người dùng chắc chắn muốn xóa hay không, cho ngta cơ hội đc chọn!!
        String choice;
        do {
            System.out.print("Choose Y/N: ");
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                for (int i = pos; i < existedInvoice - 1; i++) {
                    invoiceList[i] = invoiceList[i + 1];
                }
                invoiceList = Arrays.copyOf(invoiceList, existedInvoice - 1);
                existedInvoice--;
                System.out.println("The invoice is removed successully");
                saveToDate(saveBinaryFile);
                return;
            } else if (choice.equalsIgnoreCase("N")) {
                return;
            } else {
                System.out.println("Please choose Y/N");
            }
        } while (true);
    }

    @Override
    public void printListAscendingById() {
        if (existedInvoice == 0) {
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
        if (x == null) {
            System.out.println("Not found!!");
            return;
        }
        System.out.println("Here is the invoice that you're searching");
        System.out.println(header);
        x.display();
    }

    @Override
    public int searchById(String id) {
        if (existedInvoice == 0) {
            return -1;
        }
        for (int i = 0; i < existedInvoice; i++) {
            if (invoiceList[i].getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Invoice searchObjectById(String id) {
        if (existedInvoice == 0) {
            return null;
        }
        for (int i = 0; i < existedInvoice; i++) {
            if (invoiceList[i].getId().equalsIgnoreCase(id)) {
                return invoiceList[i];
            }
        }
        return null;
    }

    public void showInvoiceDetails() {
        InvoiceDetailsList invDetails = InvoiceDetailsList.getInstance();
        String id = MyUtil.getString("Input invoice id: ", "The invoice id is required");
        Invoice x = searchObjectById(id);
        if (x == null) {
            System.out.println("Not found!!");
            return;
        }
        invDetails.showInvoiceDetails(x.getId());
        invDetails.editInvoiceDetails(x);

    }

    public Invoice[] getInvoiceListByTourScheduleId(String tourScheduleId) {
        Invoice[] result = new Invoice[0];
        int count = 0;
        for (Invoice invoice : invoiceList) {
            if (invoice.getTourScheduleId().equalsIgnoreCase(tourScheduleId)) {
                result = Arrays.copyOf(result, count + 1);
                result[count++] = invoice;
            }
        }
        return result;
    }

    public void getQuarterPerYear() { // ver2

        long quarterOne = 0, quarterTwo = 0, quarterThree = 0, quarterFour = 0;

        int year = MyUtil.getAnInteger("Opt the year you want to manage:  ", "The year is required");

        for (Invoice inv : invoiceList) {

            LocalDateTime dateTime = inv.getInvoiceDate();

            if (dateTime.getYear() == year) {

                if (dateTime.getMonthValue() >= 1 && dateTime.getMonthValue() < 4) 
                    quarterOne += inv.getTotalAmount();
                 else if (dateTime.getMonthValue() >= 4 && dateTime.getMonthValue() < 7) 
                    quarterTwo += inv.getTotalAmount();
                 else if (dateTime.getMonthValue() >= 7 && dateTime.getMonthValue() < 10) 
                    quarterThree += inv.getTotalAmount();
                 else 
                    quarterFour += inv.getTotalAmount();
            }

        }

        System.out.printf("|%-10s|%-15s|%-15s|%-15s|%-15s|\n", "Quartter", "Quartter 1", "Quartter 2", "Quartter 3", "Quartter 4");

        System.out.printf("|%-10s|%-15d|%-15d|%-15d|%-15d|\n", "Revenue", quarterOne, quarterTwo, quarterThree, quarterFour);
    }
    
    public void statisticMonthRevenue() {
        String stringDate = MyUtil.getId("Input (mm-yyyy): ", "Input folowing format(mm-yyyy)", "^\\d{2}-\\d{4}$");
        YearMonth yearMonth = YearMonth.parse(stringDate, DateTimeFormatter.ofPattern("MM-yyyy"));
        long revenue = 0;
        for (Invoice inv : invoiceList) {
            if(inv.getInvoiceDate().getMonthValue() == yearMonth.getMonthValue() && inv.getInvoiceDate().getYear() == yearMonth.getYear())
                revenue += inv.getTotalAmount();
        }
        System.out.printf("Revenue %10s: %15d\n", yearMonth.format(DateTimeFormatter.ofPattern("MM-yyyy")), revenue);
    }
    
    public void statisticFromDateToDate(){
        LocalDate startDay;
        LocalDate endDay ;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        do {            
             startDay = MyUtil.getDate("Input the start day(dd-mm-yyyy): ", "Input folowing format(dd-mm-yyyy)", format);
             endDay = MyUtil.getDate("Input the end day(dd-mm-yyyy): ", "Input folowing format(dd-mm-yyyy)", format);
            if(startDay.isAfter(endDay)){
                System.out.println("Please input the end day greater than start day");
            }
        } while (startDay.isAfter(endDay));
        long total = 0;
        for (Invoice inv : invoiceList) {
            LocalDate day = inv.getInvoiceDate().toLocalDate();
            
            if(!day.isBefore(startDay) && !day.isAfter(endDay)) // <==>(day.isBefore(startDay) == false && day.isAfter(endDay) == false )
                                                                // tại sao phải check day có phải đằng trc startDay hay không.
                                                                // vì nếu sử dụng day.isAfter(startDay) == true thì nó sẽ kiểm tra cái ngày của invoiceDate có phải là ngày đằng sau của startDay hay không
                                                                // như vậy thì nó sẽ kh thể nào tính từ tiền ngày startDay đc
                                                                // vd: day: 20-11-2024 ~ invDate
                                                                // ngày mính muốn tìm là từ 20-11-2024 tới...
                                                                // thì nếu mà day.isAfter(startDay) thì nó sẽ trả về false, vì nó invDate ko phải
                                                                // là ngày đằng sau startDay, mà là trùng luôn
                                                                // nên lúc thống kê từ ngày này đến ngày kia thì sẽ bị bỏ mất ngày đầu tiền và ngày kết thúc
                                                                // nếu phải tư duy ngược lại là day.isBefore(startDay) == false
                                                                // để có thể lấy đc ngày bắt đầu luôn
                                                                // vẫn vd trên, day.isBefore(startDay) thì chắc chắn nó sẽ trả về false vì day ko trước startDay
                                                                // vì vậy mình có thể lấy đc ngày bắt đầu để tính thống kê
                total += inv.getTotalAmount();
        }
        
        System.out.printf("Total revenue from %s to %s: %d\n",
                                           startDay.format(format), endDay.format(format), total);
       
    }
    
    
    @Override
    public void ReadData(LoadData loadData) {

        Object[] obj = loadData.read();
        if (obj == null) {
            System.out.println("No data");
            return;
        }
        for (Object o : obj) {
            invoiceList = Arrays.copyOf(invoiceList, existedInvoice + 1); // load dữ liệu từ file rồi gán vào invoiceList
            Invoice invoice = (Invoice) o;
            int getSurrogateKey = Integer.parseInt(invoice.getId().substring(4)); // lấy ra cái surrogateKey của thằng mình đọc ra
            if (getSurrogateKey > surrogateKey) // sau đó check xem nó lớn hơn cái hiện tại không thì đổi
            {
                surrogateKey = getSurrogateKey;
            }

            invoiceList[existedInvoice++] = invoice;

        }
    }

    @Override
    public void saveToDate(SaveData saveData) {
        saveData.save(invoiceList, header);
    }

    //test, xóa comment, bấm shift + F6 để test
    public static void main(String[] args) {
        InvoiceList i = InvoiceList.getInstance();
     
        i.printListAscendingById();
        i.statisticFromDateToDate();
        i.add();
        i.add();

        i.printListAscendingById();

        i.saveToDate(new SaveDataToFile("Files/Invoices.dat"));
        i.saveToDate(new SaveFileText("FileText/Invoices.txt"));

    }
}
