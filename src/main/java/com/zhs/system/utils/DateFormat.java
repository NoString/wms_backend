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
                "HH:mm:ss-dd-MMM-yyyy");
        return dateFormat.format(date);
    }
}
