package com.way;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import android.util.Log;

public class LogUtil {
    public static final int ASSERT = Log.ASSERT;// 7-不显示调试信�?
    public static final int ERROR = Log.ERROR;
    public static final int WARN = Log.WARN;
    public static final int INFO = Log.INFO;
    public static final int DEBUG = Log.DEBUG;
    public static final int VERBOSE = Log.VERBOSE;

    public static String TAG = "SinaPush";
    private static final String PREFIX_LOGID = "mpc";//logid: MPS SDK数据包前�?
	private static final int NUMBER_COLUMN = 8999999;//用来产生7位随机数

    private static int LOGLEVEL = VERBOSE;// 默认调试信息不开

    public static int getLOGLEVEL() {
        return LOGLEVEL;
    }

    public static void setLogLevel(int loglevel) {
        LOGLEVEL = loglevel;
    }

    public static void initTag(String appId) {
        TAG = "SinaPush" + appId;
        RecordHelper.appId = appId;
    }

    public static void debug(String s) {
        if (isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, s);
            RecordHelper.getInstance().writeLog(s);
        }
    }

    public static void error(String s) {
        if (isLoggable(TAG, Log.ERROR)) {
            Log.e(TAG, s);
            RecordHelper.getInstance().writeLog(s);
        }
    }

    public static void error(String s, Throwable t) {
        if (isLoggable(TAG, Log.ERROR)) {
            Log.e(TAG, s, t);
            RecordHelper.getInstance().writeLog(s);
        }
    }

    public static void info(String s) {
        if (isLoggable(TAG, Log.INFO)) {
            Log.i(TAG, s);
            RecordHelper.getInstance().writeLog(s);
        }
    }

    public static void info(String s, Throwable t) {
        if (isLoggable(TAG, Log.INFO)) {
            Log.i(TAG, s, t);
            RecordHelper.getInstance().writeLog(s);
        }
    }

    public static void verbose(String s) {
        if (isLoggable(TAG, Log.VERBOSE)) {
            Log.v(TAG, s);
            RecordHelper.getInstance().writeLog(s);
        }
    }

    public static void warning(String s) {
        if (isLoggable(TAG, Log.WARN)) {
            Log.w(TAG, s);
            RecordHelper.getInstance().writeLog(s);
        }
    }

    public static void warning(String s, Throwable t) {
        if (isLoggable(TAG, Log.WARN)) {
            Log.w(TAG, s, t);
            RecordHelper.getInstance().writeLog(s);
        }
    }

    public static boolean isLoggable(String s, int level) {
        return level >= LOGLEVEL;
    }

    public static String parseException(Throwable ex) {
        ByteArrayOutputStream baos = null;
        PrintStream printStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            baos = new ByteArrayOutputStream();
            printStream = new PrintStream(baos);
            ex.printStackTrace(printStream);
            byte[] data = baos.toByteArray();
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printStream);
                cause = cause.getCause();
            }
            String exStr = new String(data);
            sb.append(exStr).append("\t");
        } catch (Exception e) {
            // ignore Exception
        } finally {
            try {
                if (printStream != null) {
                    printStream.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (Exception e) {
                // ignore Exception
            }
        }
        return sb.toString();
    }
    
    /**
     * @return 按照wesync协议规则生成logid
     */
    public static String generateLogid(){
    	String logid = null;
    	int timestamp = (int)(System.currentTimeMillis()/1000);
    	Random r = new Random();
    	int rNumber = 1000000 + r.nextInt(NUMBER_COLUMN);
    	
    	logid = PREFIX_LOGID + timestamp + rNumber;
    	
    	return logid;
    }

}
