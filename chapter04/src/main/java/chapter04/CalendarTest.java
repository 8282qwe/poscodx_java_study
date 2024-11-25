package chapter04;

import java.util.Calendar;

public class CalendarTest {

    public static void main(String[] args) {
//        Locale aLocale = Locale.getDefault(Locale.Category.FORMAT);
//        TimeZone tz = TimeZone.getDefault();
//        System.out.println(aLocale + ":" + tz);

        Calendar calendar = Calendar.getInstance();
        printDate(calendar);

        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DATE, 25);
        printDate(calendar);

        calendar.set(1998,03,26);
        calendar.add(Calendar.YEAR,70);
        printDate(calendar);
    }

    private static void printDate(Calendar calendar) {
        final String[] DAYS = {"일", "월", "화", "수", "목", "금", "토"};
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;   // 0 ~ 11, +1
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int day = calendar.get(Calendar.DAY_OF_WEEK);   // 1 ~ 7 (1: 일요일 ~ 7: 토요일)
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        System.out.printf("%s-%s-%s %s:%s:%s %s요일\n", year, month, date, hour, minute, second, DAYS[day - 1]);
    }
}
