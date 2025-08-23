/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.aria.pelaksefid.utility;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ghasemkiani.util.icu.PersianDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author user
 */
@Getter
@Setter
public class DateFormatUtil {

    public static String DATE_FORMAT = "yyyyy.MMMMM.dd GGG hh:mm:ss aaa";
    public static String YYYY_MM_DD = "yyyy/MM/dd";
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd_HH-mm-ss";
    public static String YYYYMMDD_HHMMSS = "yyyyMMdd_HHmmss";

    private Date miladiDate;
    private Date persianDate;
    private int persianDay;
    private int persianMonth;
    private int persianYear;
    private int miladiDay;
    private int miladiYear;
    private int miladiMonth;
    private final static long MILLIS_PER_DAY = 86400000L;

    public static Date parsePelakSefidDate(String input) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return sdf.parse(input);
        } catch (Exception e) {
            return null;
        }
    }

    public static int compare2MiladiDateWithoutTime(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
            return cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        }
        if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
            return cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
        }
        return cal1.get(Calendar.DAY_OF_MONTH) - cal2.get(Calendar.DAY_OF_MONTH);
    }

    public static int calculate2MiladiDateWithoutTimeDaysDiff(Date from, Date to) {
        Long diffTime = to.getTime() - from.getTime();
        return (int) Math.floor(diffTime / ((double) MILLIS_PER_DAY));
    }

    public static Long calculate2MiladiDateWithTimeDaysDiff(Date from, Date to) {
        Long diffTime = Math.abs(to.getTime() - from.getTime());
        return (diffTime / 1000 / 60);
    }

    public static String reversePersianDate(String inputDate) {
        if (inputDate == null || !inputDate.matches("[0-9]{4}/[0-9]{2}/[0-9]{2}")) {
            return inputDate;
        }
        String[] split = inputDate.split("/");
        return String.format("%s/%s/%s", split[2], split[1], split[0]);
    }

    public static String retrivePersianDate(LocalDate gDate) {
        if (gDate == null) {
            return "";
        }
        Date d = java.sql.Date.valueOf(gDate);
        return retrivePersianDate(d);
    }

    public static String retrivePersianDate(Long time) {
        if (time == null) {
            return "";
        }
        Date gDate = new Date(time);
        return retrivePersianDate(gDate);
    }

    public static String retrivePersianYear(Date gDate) {
        if (gDate == null) {
            return "";
        }
        PersianCalendar pcalendar = new PersianCalendar(gDate);
        com.ibm.icu.text.DateFormat dateFormat = new PersianDateFormat(DateFormatUtil.YYYY_MM_DD);
        String year = dateFormat.format(pcalendar.getTime()).substring(0, 4);
        if (year == null || !year.matches("[0-9]{4}")) {
            return "";
        }
        return year;
    }

    public static String retriveMiladiYear(Date gDate) {
        if (gDate == null) {
            return "";
        }
        Calendar c = Calendar.getInstance();
        c.setTime(gDate);
        String year = String.valueOf(c.get(Calendar.YEAR));
        if (year == null || !year.matches("[0-9]{4}")) {
            return "";
        }
        return year;
    }

    public static String retriveTimeDate(Date gDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(gDate);
        String time = String.format("%02d:%02d:%02d", c.get(Calendar.HOUR), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
        return time;
    }

    public static String retriveTimeDateWithoutSecond(Date gDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(gDate);
        String time = String.format("%02d:%02d", c.get(Calendar.HOUR), c.get(Calendar.MINUTE));
        return time;
    }

    public static Date retrieveDateFromString(String str_date, String format) throws ParseException {
        // Convert String to java.util.Date ================
        DateFormat formatter = new SimpleDateFormat(format);
        Date date = (Date) formatter.parse(str_date);
        // =================================================
        return date;
    }

    public static Date retrieveDateFromString(String str_date) throws ParseException {
        return retrieveDateFromString(str_date, DATE_FORMAT);
    }

    public static String formatDateAsString(Date date) {
        return formatDateAsString(date, DATE_FORMAT);

    }

    public static String formatDateAsString(Date date, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static <T> Set<T> convertArrayToSet(T[] array) {
        Set<T> set = new HashSet<T>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        return set;
    }

    public static String getCurrentPersianDate() {
        com.ibm.icu.util.Calendar persianCalendar = com.ibm.icu.util.Calendar.getInstance(
                new com.ibm.icu.util.ULocale("fa_IR@calendar=persian"));

        persianCalendar.setTimeZone(com.ibm.icu.util.TimeZone.getTimeZone("Asia/Tehran"));

        com.ibm.icu.text.SimpleDateFormat sdf = new com.ibm.icu.text.SimpleDateFormat(
                "y/M/d", new com.ibm.icu.util.ULocale("fa_IR"));

        sdf.setCalendar(persianCalendar);
        sdf.setTimeZone(com.ibm.icu.util.TimeZone.getTimeZone("Asia/Tehran"));

        com.ibm.icu.text.NumberFormat englishNumberFormat = com.ibm.icu.text.NumberFormat.getInstance(
                new com.ibm.icu.util.ULocale("en_US"));
        sdf.setNumberFormat(englishNumberFormat);

        return sdf.format(persianCalendar.getTime());
    }

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public static String getCurrentTimeWithoutSecond() {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(cal.getTime());
    }

    public static String getCurrentTimeWithoutMinute() {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        return sdf.format(cal.getTime());
    }

    public static int retriveCurrentHour() {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int retriveCurrentDayOfWeek() {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static Date retriveDateCutOffTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date addHourToDate(Date date, Integer minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public static Date addDayToDate(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static String addDaysToPersianDate(String persianDate, Integer days) {
        Date date = miladiDateByPersianDate(persianDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return retrivePersianDate(calendar.getTime());
    }

    public static Date miladiDateByPersianDate(String persianDate) {

        if (persianDate == null || persianDate.equalsIgnoreCase("")) {
            return null;
        }
        String[] date = persianDate.split("/");
        int jd = Integer.parseInt(date[2]);
        int jm = Integer.parseInt(date[1]);
        int jy = Integer.parseInt(date[0]);
        int[] gDate = jalaliToGregorian(jy, jm, jd);
        Calendar calendar = Calendar.getInstance();
        calendar.set(gDate[0], gDate[1] - 1, gDate[2], 0, 0, 0);
        return calendar.getTime();
    }

    public static String retrivePersianDate(Date gregorianDate) {
        if (gregorianDate == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(gregorianDate);
        int gy = calendar.get(Calendar.YEAR);
        int gm = calendar.get(Calendar.MONTH) + 1;
        int gd = calendar.get(Calendar.DAY_OF_MONTH);
        int[] persianDate = gregorianToJalali(gy, gm, gd);
        return String.format("%04d/%02d/%02d", persianDate[0], persianDate[1], persianDate[2]);
    }

    private static int[] jalaliToGregorian(int jy, int jm, int jd) {
        int[] g_days_in_month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] j_days_in_month = {31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};

        jy -= 979;
        int j_day_no = (jy / 33) * 12053 + (jy % 33) * 365 + ((jy % 33 + 3) / 4);
        for (int i = 0; i < jm - 1; ++i) {
            j_day_no += j_days_in_month[i];
        }
        j_day_no += jd - 1;

        int g_day_no = j_day_no + 79;
        int gy = 1600 + 400 * (g_day_no / 146097);
        g_day_no %= 146097;

        boolean leap = true;
        if (g_day_no >= 36525) {
            g_day_no--;
            gy += 100 * (g_day_no / 36524);
            g_day_no %= 36524;
            if (g_day_no >= 365) {
                g_day_no++;
            } else {
                leap = false;
            }
        }

        gy += 4 * (g_day_no / 1461);
        g_day_no %= 1461;
        if (g_day_no >= 366) {
            leap = false;
            g_day_no--;
            gy += g_day_no / 365;
            g_day_no %= 365;
        }

        int gm;
        for (gm = 0; gm < 12 && g_day_no >= g_days_in_month[gm] + ((gm == 1 && leap) ? 1 : 0); gm++) {
            g_day_no -= g_days_in_month[gm] + ((gm == 1 && leap) ? 1 : 0);
        }

        int gd = g_day_no + 1;
        return new int[]{gy, gm + 1, gd};
    }

    private static int[] gregorianToJalali(int gy, int gm, int gd) {
        int[] g_days_in_month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] j_days_in_month = {31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};

        boolean isLeapGregorian = (gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0);
        if (isLeapGregorian) {
            g_days_in_month[1] = 29;
        }

        int g_day_no = (gy - 1600) * 365 + ((gy - 1600) / 4) - ((gy - 1600) / 100) + ((gy - 1600) / 400);
        for (int i = 0; i < gm - 1; ++i) {
            g_day_no += g_days_in_month[i];
        }
        g_day_no += gd;

        int j_day_no = g_day_no - 79;
        int j_np = j_day_no / 12053;
        j_day_no %= 12053;
        int jy = 979 + j_np * 33;

        int j_leap;
        int i;
        for (i = 0; i < 33 && j_day_no >= ((j_leap = (i % 4 == 0 ? 366 : 365))); i++) {
            j_day_no -= j_leap;
        }

        jy += i;
        boolean isLeapJalali = (i % 4 == 0);

        for (i = 0; i < 12 && j_day_no >= j_days_in_month[i] + (i == 11 && isLeapJalali ? 1 : 0); i++) {
            j_day_no -= j_days_in_month[i] + (i == 11 && isLeapJalali ? 1 : 0);
        }

        int jm = i + 1;
        int jd = j_day_no + 1;

        return new int[]{jy, jm, jd};
    }
}
