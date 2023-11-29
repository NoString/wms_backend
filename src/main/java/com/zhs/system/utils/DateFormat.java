package com.zhs.system.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormat {
    /**
     * Day Month Year
     * @param date
     * @return
     */
    public static String dmy(Date date){
        if (date == null){
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MMM-dd-HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String[] getRecentSevenDays() {
        String[] recentSevenDays = new String[7];
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            recentSevenDays[6 - i] = date.format(formatter);
        }

        return recentSevenDays;
    }
}
