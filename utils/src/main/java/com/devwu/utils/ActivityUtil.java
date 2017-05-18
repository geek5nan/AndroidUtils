package com.devwu.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityUtil {

    private static List<Activity> activityList;
    private static ActivityUtil instance;

    private ActivityUtil() {
    }

    public static ActivityUtil getInstance() {
        if (instance == null) {
            instance = new ActivityUtil();
        }
        return instance;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void addActivity(Activity activity) {
        if (activityList == null) {
            activityList = new ArrayList<>();
        }
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activity != null && activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    public void removeLastActivity(int n){
        for (int i = 0;i<n;i++){
            activityList.remove(activityList.size()-1);
        }
    }

    /**
     * 销毁活动堆栈中最后n个活动
     * @param n
     */
    public void finishLastActivites(int n){
        for (int i = 0;i<n;i++){
            Activity activity = activityList.remove(activityList.size()-1);
            activity.finish();
        }
    }

    public void finishAllActivity() {
        if (null != activityList) {
            for (Activity activity : activityList) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
            activityList.clear();
        }
    }

}