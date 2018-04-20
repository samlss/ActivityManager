package com.android.sam.activitymanager;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import java.util.Stack;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @org SZ Ryan Centtech network science and Technology Co.,Ltd
 * @date 2018/4/19 0019 16:46
 */
public class ActivityInstrumentation extends Instrumentation implements ActivityLifeCycle{
    private String TAG = "ActivityInstrumentation";
    private Instrumentation base;
    private Stack<Activity> mActivityStack;

    public ActivityInstrumentation(Instrumentation base) {
        this.base = base;
        mActivityStack = new Stack<>();
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Activity activity = super.newActivity(cl, className, intent);
        JLogger.e(TAG, "add activity: "+className);
        mActivityStack.add(activity);
        return activity;
    }

    @Override
    public void callActivityOnDestroy(Activity activity) {
        super.callActivityOnDestroy(activity);
        JLogger.e(TAG, "remove activity: "+activity.getClass().getCanonicalName());
        mActivityStack.remove(activity);
    }

    public Instrumentation getInstrumentation() {
        return base;
    }

    @Override
    public void finishActivity(Activity activity){
        if(activity != null){
            mActivityStack.remove(activity);
            activity.finish();
        }
    }

    @Override
    public void finishActivity(Class<?> cls){
        for (Activity activity : mActivityStack) {
            if(activity.getClass().equals(cls) ){
                finishActivity(activity);
                return;
            }
        }
    }

    @Override
    public void finishAllActivity(){
        for (int i = 0, size = mActivityStack.size(); i < size; i++){
            if (null != mActivityStack.get(i)){
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

    @Override
    public void clear() {
        mActivityStack.clear();
    }

    @Override
    public Activity getCurrentActivity() {
        return mActivityStack.lastElement();
    }

    @Override
    public Stack<Activity> getActivityStack() {
        return mActivityStack;
    }
}
