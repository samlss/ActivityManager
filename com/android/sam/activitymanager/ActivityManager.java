package com.android.sam.activitymanager;

import android.app.Application;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @org SZ Ryan Centtech network science and Technology Co.,Ltd
 * @date 2018/4/19 0019 17:20
 */
public final class ActivityManager {
    private static Object LOCK = new Object();
    private static ActivityManager INSTANCE;
    private ActivityManagerDelegate activityManagerDelegate;

    private ActivityManager() {
        activityManagerDelegate = new ActivityManagerDelegate();
    }

    public static void init(boolean debug){
        JLogger.DEBUG = debug;
        if (INSTANCE == null){
            synchronized (LOCK){
                if (INSTANCE == null){
                    INSTANCE = new ActivityManager();
                }
            }
        }
    }

    public static ActivityManager get() {
        return INSTANCE;
    }

    public void hook(){
        activityManagerDelegate.hook();
    }


    public void registerActivityLifecycle(Application application){
        activityManagerDelegate.registerActivityLifecycle(application);
    }

    public int getMode() {
        return activityManagerDelegate.getMode();
    }

    public void clear(){
        activityManagerDelegate.clear();
    }

    public ActivityLifeCycle getActivityLifeCycle() {
        return activityManagerDelegate.getActivityLifeCycle();
    }
}
