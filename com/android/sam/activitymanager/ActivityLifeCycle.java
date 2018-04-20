package com.android.sam.activitymanager;

import android.app.Activity;

import java.util.Stack;

/**
 * @Author: SamLeung
 * @E-mail: 729717222@qq.com
 */
public interface ActivityLifeCycle {
    void finishActivity(Activity activity);
    void finishActivity(Class<?> cls);
    void finishAllActivity();
    void clear();
    Activity getCurrentActivity();
    Stack<Activity> getActivityStack();
}