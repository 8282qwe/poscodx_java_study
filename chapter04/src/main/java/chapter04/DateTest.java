package chapter04;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) {
        Date now = new Date();

        System.out.println(now);
        printDate01(now);
        printDate02(now);
    }

    private static void printDate02(Date now) {
        // +1900
        int year = now.getYear()+1900;

        // 0 ~ 11, +1
        int month = now.getMonth()+1;

        int date = now.getDate();
        int hour = now.getHours();
        int minute = now.getMinutes();
        int second = now.getSeconds();

        System.out.printf("%s-%s-%s %s:%s:%s\n", year, month, date, hour, minute, second);
    }

    private static void printDate01(Date now) {
        // 2024-11-25 14:23:30
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(now));
    }

}
