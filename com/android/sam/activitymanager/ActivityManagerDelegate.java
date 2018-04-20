package com.android.sam.activitymanager;

import android.app.Application;
import android.app.Instrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @org SZ Ryan Centtech network science and Technology Co.,Ltd
 * @date 2018/4/19 0019 19:10
 */
class ActivityManagerDelegate {
    public final static int MODE_ACTIVITY_LIFECYCLE = 0;
    public final static int MODE_HOOK = 1;

    private int mode;
    private ActivityLifeCycle activityLifeCycle;

    public ActivityManagerDelegate() {

    }

    public void hook() {
        clear();

        mode = MODE_HOOK;
        Class<?> activityThread = null;
        try {
            activityThread = Class.forName("android.app.ActivityThread");
            Method sCurrentActivityThread = activityThread.getDeclaredMethod("currentActivityThread");
            sCurrentActivityThread.setAccessible(true);
            Object activityThreadObject = sCurrentActivityThread.invoke(activityThread);

            Field mInstrumentation = activityThread.getDeclaredField("mInstrumentation");
            mInstrumentation.setAccessible(true);

            Instrumentation instrumentation = (Instrumentation) mInstrumentation.get(activityThreadObject);
            ActivityInstrumentation activityInstrumentation = new ActivityInstrumentation(instrumentation);

            mInstrumentation.set(activityThreadObject, activityInstrumentation);
            activityLifeCycle = activityInstrumentation;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    public void registerActivityLifecycle(Application application) {
        mode = MODE_ACTIVITY_LIFECYCLE;
        clear();

        ActivityLifeCycleDelegate activityLifeCycleDelegate = new ActivityLifeCycleDelegate();
        application.registerActivityLifecycleCallbacks(activityLifeCycleDelegate);

        activityLifeCycle = activityLifeCycleDelegate;
    }

    /**
     * {@link #MODE_ACTIVITY_LIFECYCLE}、{@link #MODE_HOOK}
     */
    public int getMode() {
        return mode;
    }

    public void clear() {
        if (activityLifeCycle != null) {
            activityLifeCycle.clear();
        }
    }

    public ActivityLifeCycle getActivityLifeCycle() {
        return activityLifeCycle;
    }


}
