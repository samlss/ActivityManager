package com.android.sam.activitymanager;

import android.util.Log;

/**
 * @Author: SamLeung
 * @E-mail: 729717222@qq.com
 */
class JLogger {
    public static boolean DEBUG = true;

    private static String TAG = "JLogger";
    public final static String MATCH = "%s->%s->%d";
    public final static String CONNECTOR = ":<--->:";

    public static String buildHeader() {
        StackTraceElement stack = Thread.currentThread().getStackTrace()[4];
        return String.format(MATCH, stack.getClassName(), stack.getMethodName(),
                stack.getLineNumber()) + CONNECTOR;
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void v(String msg) {
        if (DEBUG) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void i( String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }

    }

    public static void w(String msg) {
        if (DEBUG) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            Log.e(TAG, msg==null?"null":msg);
        }
    }

    public static void printError(int code){
        if (DEBUG) {
            Log.e(TAG, "error code: "+code);
        }
    }
}