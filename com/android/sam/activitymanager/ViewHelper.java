package com.android.sam.activitymanager;

import android.app.Activity;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @org SZ Ryan Centtech network science and Technology Co.,Ltd
 * @date 2018/4/19 0019 18:01
 */

public class ViewHelper {
    public static void onActivitySimulateTouch(Activity activity, float x, float y){
        activity.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                MotionEvent.ACTION_DOWN, x, y, 0));

        activity.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                MotionEvent.ACTION_UP, x, y, 0));
    }

    public static List<View> getActivityViews(Activity activity){
        return getAllChildViews(activity.getWindow().getDecorView());
    }

    private static List<View> getAllChildViews(View view) {
        List<View> allViewList = new ArrayList<>();

        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;

            for (int i = 0; i < vp.getChildCount(); i++) {
                View child = vp.getChildAt(i);

                allViewList.add(child);
                allViewList.addAll(getAllChildViews(child));
            }
        }
        return allViewList;
    }
}
