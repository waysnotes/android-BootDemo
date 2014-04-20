package com.way;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.os.Environment;

/**
 * 记录文本日志
 * 
 * @author zhangqi
 * 
 */
public final class RecordHelper {

	private static RecordHelper instance;
	private OutputStream output;
	private File logFile;
	private String filename;
	public static String appId = "";

	private RecordHelper() {
		open(this.filename);
	}

	public static RecordHelper getInstance() {
		if (null == instance) {
			instance = new RecordHelper();
		}

		return instance;
	}

	private boolean open(String sFileName) {
		try {
			String path = null;
			if (!Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				return false;
			} else {
				path = Environment.getExternalStorageDirectory().toString()
						+ "/BootDemo/log/";
			}

			File logPath = new File(path);
			if (!logPath.exists()) {
				logPath.mkdirs();
			}
			if (sFileName == null || sFileName.length() == 0) {
				String sDateStr = getTimeStr("yyyy-MM-dd");
				sFileName = path + sDateStr + "-app-" + ".log";
			} else {
				sFileName = path + sFileName + ".log";
			}

			logFile = new File(sFileName);
			if (!logFile.exists())
				logFile.createNewFile();

			output = new FileOutputStream(logFile, true);

		} catch (IOException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		if (output == null)
			return false;
		return true;
	}

	public void close() {
		try {
			if (null != output) {
				output.close();
				output = null;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public synchronized void writeLog(String str) {
		try {
			if (output == null) {
				if (!open(this.filename)) {
					return;
				}
			}
			if (null != output) {
				String content = "[" + getTimeStr("yyyy-MM-dd HH:mm:ss") + "] "
						+ str + "\r\n";
				output.write(content.getBytes("UTF-8"));
				output.flush();
			}
			close();
		} catch (UnsupportedEncodingException ee) {
			System.out.println("UnsupportedEncodingException:"
					+ ee.getMessage());
		} catch (IOException ioe) {
			System.out.println("IOerror:" + ioe.getMessage());
		}

	}

	@SuppressLint("SimpleDateFormat")
	private String getTimeStr(String datetype) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(datetype);
		Calendar calendarLocal = Calendar.getInstance();
		return dateFormat.format(calendarLocal.getTime());
	}

}
