package com.viettel.common;

import android.content.Context;
import android.os.Environment;

import com.viettel.utils.StringUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

//import org.apache.log4j.Logger;
//import de.mindpipe.android.logging.log4j.LogConfigurator;

public class LogManager {
	// private static LogConfigurator mLogConfigrator = new LogConfigurator();
	// private static Logger fileLog;
	public static LogManager instance = null;
	private Context context;

	public LogManager(Context context) {
		this.context = context;
	}

	public static LogManager getInstance() {
		if (instance == null) {
			instance = new LogManager(GlobalInfo.getInstance().getAppContext());
		}
		return instance;
	}

	public void writeFile(String text) {
		if (!StringUtil.isNullOrEmpty(GlobalInfo.getInstance().getEmployeeCode())) {
			File sdCard = Environment.getExternalStorageDirectory();
			File logFileFolder = new File(
					sdCard.getAbsolutePath() + "/GSCT/" + GlobalInfo.getInstance().getEmployeeCode());

			if (!logFileFolder.exists()) {
				logFileFolder.mkdirs();
			}

			try {
				File logFile = new File(logFileFolder,
						GlobalInfo.getInstance().getEmployeeCode() + "_" + getTimeCreateLog() + ".txt");

				if (!logFileFolder.exists()) {
					logFileFolder.createNewFile();
				}

				FileOutputStream fostream = new FileOutputStream(logFile, true);
				OutputStreamWriter oswriter = new OutputStreamWriter(fostream);
				BufferedWriter bwriter = new BufferedWriter(oswriter);
				bwriter.write(text);
				bwriter.newLine();
				bwriter.close();
				oswriter.close();
				fostream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void saveLogcatToFile(Context context) {
		
		File sdCard = Environment.getExternalStorageDirectory();
		File logFileFolder = new File(
				sdCard.getAbsolutePath() + "/GSCT/LOG/" + GlobalInfo.getInstance().getEmployeeCode()+"/");

		if (!logFileFolder.exists()) {
			logFileFolder.mkdirs();
		}
		if(GlobalInfo.getInstance().getEmployeeCode()==null || GlobalInfo.getInstance().getEmployeeCode().length()==0){
			return;
		}
		String fileName = "logcat_" + GlobalInfo.getInstance().getEmployeeCode() + "_ktts_" + getTimeCreateLog() + ".txt";
		try {
			File outputFile = new File(logFileFolder, fileName);
			if(!outputFile.exists()){
				outputFile.createNewFile();
			}
			String[] cmd = new String[] {"logcat","-f",outputFile.getAbsolutePath(),"-v","time","System.err:V,hungkm:V,Location:V,ktts_checkin:V,CheckinService:V,AndroidRuntime:V,ANDRO_ASYNC:V","*:S"};
//			String cmd2= "logcat -f " + outputFile.getAbsolutePath();
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getTimeCreateLog() {
		Calendar calendar = Calendar.getInstance();
		String day = String.valueOf(calendar.getTime().getDate());
		String month = String.valueOf(calendar.getTime().getMonth() + 1);
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		return day + month + year;
	}

	// private static void configure(String fileName, String filePattern) {
	// mLogConfigrator.setFileName(fileName);
	// mLogConfigrator.setFilePattern(filePattern);
	// mLogConfigrator.setUseLogCatAppender(true);
	// mLogConfigrator.configure();
	// }
}
