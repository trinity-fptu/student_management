package DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Valid_Input {

    public static Scanner sc = new Scanner(System.in);

    public static String inputNonBlankString(String msg){
       String data;
       do{
           System.out.println(msg);
           data = sc.nextLine().trim();
       } while (data.length() == 0);
       return data;
    }

    public static int inputInt(String msg){
        int data;
        System.out.println(msg);
        data = Integer.parseInt(sc.nextLine());
        return data;
    }

    public static String inputPattern(String msg, String pattern){
        String data;
        do{
            System.out.println(msg);
            data = sc.nextLine().trim();
            if (!data.matches(pattern)){
                System.out.println("Wrong format!");
            }
        } while (!data.matches(pattern));
        return data;
    }

    // check leap year
    public static boolean isLeapYear(int year) {
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }

    // check valid date
    public static boolean isValidDate(int d, int m, int y) {
        if (!(0 < d && d < 32) || !(0 < m && m <= 12) || !(0 < y)) {
            return false;
        } else {
            if (m == 4 || m == 6 || m == 9 || m == 11) {
                if (d == 31) {
                    return false;
                }
            } else if (m == 2) {
                if (isLeapYear(y)) {
                    if (d > 29) {
                        return false;
                    }
                } else {
                    if (d > 28) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // get a valid date
    public static LocalDate inputDate(String msg) {
        String data;
        int d, m, y;
        do {
            data = inputPattern(msg, "\\d{2}-\\d{2}-\\d{4}");
            StringTokenizer st = new StringTokenizer(data, "-");
            d = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            if (isValidDate(d, m, y) == false) {
                System.out.println("This is not valid date. Please try again!");
            }
        } while (isValidDate(d, m, y) == false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(data, formatter);
        return date;
    }


}
