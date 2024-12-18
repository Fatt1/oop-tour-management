package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class MyUtil {

    private static Scanner sc = new Scanner(System.in);
    public static final double VAT = 0.1;
    public static int getAnInteger(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            System.out.print(inputMsg);
            try {
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = lowerBound;
        }
        int n;
        while (true) {
            System.out.print(inputMsg);
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg) {
        String s;
        while (true) {
            System.out.print(inputMsg);
            s = sc.nextLine().toUpperCase().trim();
            if (s.isEmpty() || s.length() == 0) {
                System.out.println(errorMsg);
            } else {
                return s;
            }
        }
    }

    public static double getAnDouble(String inputMsg, String errorMsg) {
        double n;
        while (true) {
            System.out.print(inputMsg);
            try {
                n = Double.parseDouble(sc.nextLine());

                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getId(String inputMsg, String errorMsg, String format) {
        boolean match;
        String id;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().toUpperCase().trim();
            match = id.matches(format);
            if (!match || id.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }

    }

    public static String getValueOrDefault(String inputMsg, String errorMsg) {
        String s;
        while (true) {
            System.out.print(inputMsg);
            s = sc.nextLine().toUpperCase().trim();
            if (s.compareToIgnoreCase("YES") == 0) {
                return s;
            } else if (s.compareToIgnoreCase("NO") == 0) {
                return s;
            } else {
                System.out.println(errorMsg);
            }
        }
    }

    public static LocalDate getDate(String inputMsg, String errorMsg, DateTimeFormatter format) {
        LocalDate date;
        String stringDate;
        while (true) {
            try {
                System.out.print(inputMsg);
                stringDate = sc.nextLine();
                date = LocalDate.parse(stringDate, format); // chuyển từ String sang kiểu LocalDate có format ở trong để theo cái chuỗi định dạng nào đó
                // vd: dd-mm-yyyy 
                return date;
            } catch (DateTimeParseException e) { // đây để bắt lỗi nếu không nhập đúng định dạng hoặc nhập tháng kh tồn tại như tháng 13...
                System.out.println(errorMsg);
            }

        }
    }
    public static LocalDateTime getDateTime(String inputMsg, String errorMsg, DateTimeFormatter format) {
        LocalDateTime dateTime;
        String stringDateTime;
        while (true) {            
            try {
            System.out.print(inputMsg);
            stringDateTime = sc.nextLine().trim();
            dateTime = LocalDateTime.parse(stringDateTime, format); // chuyển từ String sang kiểu LocalDate có format ở trong để theo cái chuỗi định dạng nào đó
                                                        // vd: dd-mm-yyyy 
            return dateTime;
            } catch (DateTimeParseException e) { // đây để bắt lỗi nếu không nhập đúng định dạng hoặc nhập tháng kh tồn tại như tháng 13...
                System.out.println(errorMsg);
            }
            
        }

    }
}
