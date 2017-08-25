/**
 * Copyright 2013 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Chuyen doi du lieu kieu DateTime
 * 
 * @author datht1
 * 
 */
public class DateConvert {
	public static final String sFormat = "yyyy-dd-MM";
	public static final SimpleDateFormat dateView = new SimpleDateFormat("yyyy-dd-MM");
	
	// check in/out date format
	public static final String checkInFormat = "yyyy-dd-MM HH:mm:ss";
	public static final SimpleDateFormat checkInDateView = new SimpleDateFormat(checkInFormat);

	public static Date convertStringToDate(String sDate) {
		Date dateOut = new Date();
		try {
			dateOut = dateView.parse(sDate);
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return dateOut;
	}

	public static String convertDateToString(Date date) {
		String outDate = "";
		try {
			outDate = dateView.format(date);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return outDate;
	}
	
	// convert to check in date format
	public static Date convertStringToDateCheckIn(String sDate) {
		Date dateOut = new Date();
		try {
			dateOut = checkInDateView.parse(sDate);
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return dateOut;
	}

	public static String convertDateToStringCheckIn(Date date) {
		String outDate = "";
		try {
			outDate = checkInDateView.format(date);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return outDate;
	}

	// convert to custom date
	public static Date convertStringToDate(String sDate, String format) {
		final SimpleDateFormat dateView1 = new SimpleDateFormat(format);
		Date dateOut = new Date();
		try {
			dateOut = dateView1.parse(sDate);
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return dateOut;
	}

	public static String convertDateToString(Date date, String format) {
		final SimpleDateFormat dateView1 = new SimpleDateFormat(format);
		String outDate = "";
		try {
			outDate = dateView1.format(date);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return outDate;
	}
}
