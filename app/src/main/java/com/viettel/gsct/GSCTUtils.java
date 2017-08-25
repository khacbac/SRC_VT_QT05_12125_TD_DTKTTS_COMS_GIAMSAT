package com.viettel.gsct;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class GSCTUtils {
    public static final String GSCT_PATTERN = "yyyy-dd-MM";

    public static String getDateNow() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat spf= new SimpleDateFormat(GSCT_PATTERN);
        String ret = spf.format(calendar.getTime());
        return ret;
    }

    public static String getDateNow(String partten) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat spf= new SimpleDateFormat(partten);
        String ret = spf.format(calendar.getTime());
        return ret;
    }

    public static Date convertString(String input, String partten) {
        SimpleDateFormat spf= new SimpleDateFormat(partten);
        try {
            Date ret = spf.parse(input);
            return ret;
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertDate(Date date, String partten) {
        SimpleDateFormat spf= new SimpleDateFormat(partten);
        String ret = "";
        try {
             ret = spf.format(date.getTime());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static String getTimeNow() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat spf= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String ret = spf.format(calendar.getTime());
        return ret;
    }

    public static String standardlizeTime(String date ) {
        if (date == null || date.length() == 0)
            return "";
        DateFormat df = new SimpleDateFormat(GSCT_PATTERN);
        Date startDate;
        try {
            startDate = df.parse(date);
            return new SimpleDateFormat("dd-MM-yyyy").format(startDate);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return  "";
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

}
