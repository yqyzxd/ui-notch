package com.wind.notch;

import android.app.Activity;
import android.os.Build;

import java.lang.reflect.Method;

/**
 * Created By wind
 * on 2020/3/14
 */
class HwNotch extends AndroidNotch {
    @Override
    public boolean isNotch(Activity activity) {
        boolean notch = false;
        if (Build.VERSION.SDK_INT<=Build.VERSION_CODES.O){
            try {
                ClassLoader cl = activity.getClassLoader();
                Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
                Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
                notch = (boolean) get.invoke(HwNotchSizeUtil);
            } catch (Exception e) {
            }
        }else {
            notch=super.isNotch(activity);
        }

        return notch;
    }
}
