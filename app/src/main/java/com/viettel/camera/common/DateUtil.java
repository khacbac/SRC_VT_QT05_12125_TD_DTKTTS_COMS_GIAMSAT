package com.viettel.camera.common;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final String sFormat = "dd/MM/yyyy";
	@SuppressLint("SimpleDateFormat")
	public static final SimpleDateFormat dateView = new SimpleDateFormat(
			sFormat);
	
	public static Date convertStringToDate(String sDate) {
		Date dateOut = new Date();
		try {
			dateOut = dateView.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateOut;
	}

	public static String convertDateToString(Date date) {
		String outDate = "";
		try {
			outDate = dateView.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outDate;
	}
}
