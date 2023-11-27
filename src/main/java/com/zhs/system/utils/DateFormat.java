package com.zhs.system.utils;

import java.text.SimpleDateFormat;
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
}
