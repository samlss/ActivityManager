package com.android.sam.activitymanager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Stack;

/**
 * @Author: SamLeung
 * @E-mail: 729717222@qq.com
 */
class ActivityLifeCycleDelegate implements Application.ActivityLifecycleCallbacks, ActivityLifeCycle {
    private String TAG = "ActivityLifeCycleDelegate";
    private Stack<Activity> mActivityStack;

    public ActivityLifeCycleDelegate(){
        mActivityStack = new Stack<>();
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
    public Activity getCurrentActivity() {
        return mActivityStack.lastElement();
    }

    @Override
    public Stack<Activity> getActivityStack() {
        return mActivityStack;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        JLogger.e("add activity: "+activity.getClass().getCanonicalName());
        mActivityStack.add(activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        JLogger.e("remove activity: "+activity.getClass().getCanonicalName());
        mActivityStack.remove(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void clear() {
        mActivityStack.clear();
    }
}