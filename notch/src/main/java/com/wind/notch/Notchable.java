package com.wind.notch;

import android.app.Activity;

/**
 * Created By wind
 * on 2020/3/14
 */
public interface Notchable {

    /**
     * 是否有刘海
     * @return
     */
    boolean isNotch(Activity activity);

    /**
     * 获取刘海高
     * @return
     */
    default int getNotchHeight(Activity activity){
        int statusBarHeightId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (statusBarHeightId > 0){
            return activity.getResources().getDimensionPixelSize(statusBarHeightId);
        }
        return 0;
    }



}


