/**
 * Copyright 2013 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.common;

import android.annotation.SuppressLint;

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
	public static final String sFormat = "yyyy-MM-dd";
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
