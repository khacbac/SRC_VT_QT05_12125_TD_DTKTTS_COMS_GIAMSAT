package com.viettel.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.viettel.common.GlobalInfo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class StringUtil {
    public static final String EMPTY_STRING = "";

    /**
     * Lay resource tu string.xml
     *
     * @param id
     * @return
     */
    public static String getString(int id) {
        return GlobalInfo.getInstance().getAppContext().getResources()
                .getString(id);
    }

    private static final String TAG = "StringUtil";

    public static String getPath(Context context, Uri uri) {
        Log.e(TAG, "getPath: " + uri.getScheme() + " " + uri.getPath());
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = new String[] { "_data" };
//            String[] projection = {"dat"};
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(projection[0]);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }


        return null;
    }

    public static boolean isNullOrEmpty(String aString) {
        return (aString == null) || ("".equals(aString.trim()));
    }

    public static int convertToInt(String sValue) {
        int iReturnValue = Integer.valueOf(sValue);
        return iReturnValue;
    }

    public static long convertToLong(String sValue) {
        long iReturnValue = Long.valueOf(sValue);
        return iReturnValue;
    }

    public static double convertToDouble(String sValue) {
        double iReturnValue = Double.valueOf(sValue);
        return iReturnValue;
    }

    public static double convertToDoubleRound2(String sValue) {
        double iReturnValue = Math.round(Double.parseDouble(sValue) * 100) / 100d;
        return iReturnValue;
    }

    /**
     * Chuyen doi so duoi dinh dang
     *
     * @param d
     * @return
     */
    public static String formatNumber(double d) {
        if (d == 0) {
            return "0";
        } else {
            Locale locale = Locale.US;
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(locale);
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat format = new DecimalFormat("##########.##",
                    otherSymbols);
            return format.format(d);
        }
    }

    /**
     * Chuyen doi so duoi dinh dang
     *
     * @param d
     * @return
     */
    public static String formatNumber(long d) {
        if (d == 0) {
            return "0";
        } else {
            Locale locale = Locale.US;
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(locale);
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat format = new DecimalFormat("##########.##",
                    otherSymbols);
            return format.format(d);
        }
    }

    /**
     * Chuyen doi so duoi dinh dang
     *
     * @param d
     * @return
     */
    public static String formatNumber(float d) {
        if (d == 0) {
            return "0";
        } else {
            Locale locale = Locale.US;
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(locale);
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat format = new DecimalFormat("##########.###",
                    otherSymbols);
            return format.format(d);
        }
    }

    public static String getDirectoryPath(String str) {
        String sResult = "";
        if (str != null && str.length() > 0) {
            int endIndex = str.lastIndexOf("/");
            if (endIndex != -1) {
                sResult = str.substring(0, endIndex + 1);
            }
        }
        return sResult;
    }
}