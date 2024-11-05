package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class MyUtil {
    private static Scanner sc = new Scanner(System.in);
     
    public static int getAnInteger(String inputMsg, String errorMsg) {
           int n;
           while (true) {            
            System.out.print(inputMsg);
               try {
                   n = Integer.parseInt(sc.nextLine());
                   return n;
               } catch (Exception e) {
                   System.out.println(errorMsg) ;
               }
        } 
    }
    
    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound){
        if(lowerBound > upperBound){
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = lowerBound;
        }
        int n;
           while (true) {            
            System.out.print(inputMsg);
               try {
                   n = Integer.parseInt(sc.nextLine());
                   if(n < lowerBound || n > upperBound){
                       throw new Exception();
                   }
                   return n; 
               } catch (Exception e) {
                   System.out.println(errorMsg) ;
               }
        } 
    }
    
    public static String getString(String inputMsg, String errorMsg){
        String s;
        while (true) {  
            System.out.print(inputMsg);
            s = sc.nextLine().toUpperCase().trim();
            if(s.isEmpty()|| s.length() == 0)
                System.out.println(errorMsg);
            else
                return s;
        }
    }
    
    public static double getAnDouble(String inputMsg, String errorMsg){
        double n;
           while (true) {            
            System.out.print(inputMsg);
               try {
                   n = Double.parseDouble(sc.nextLine());
                   
                   return n;
               } catch (Exception e) {
                   System.out.println(errorMsg) ;
               }
        } 
    }
    
    public static String getId(String inputMsg, String errorMsg, String format){
        boolean match;
        String id;
        while (true) {            
            System.out.print(inputMsg);
            id = sc.nextLine().toUpperCase().trim();
            match = id.matches(format);
            if(!match || id.isEmpty())
                System.out.println(errorMsg);
            else
                return id;
        }

    }
    
    public static LocalDate getDate(String inputMsg, String errorMsg, DateTimeFormatter format) {
        LocalDate date;
        String stringDate;
        while (true) {            
            try {
            System.out.print(inputMsg);
            stringDate = sc.nextLine();                                                        // vd: dd-mm-yyyy 

            date = LocalDate.parse(stringDate, format); // chuyển từ String sang kiểu LocalDate có format ở trong để theo cái chuỗi định dạng nào đó
            return date;
            } catch (DateTimeParseException e) { // đây để bắt lỗi nếu không nhập đúng định dạng hoặc nhập tháng kh tồn tại như tháng 13...
                System.out.println(errorMsg);
            }
            
        }
    }
    
}
