package com.wind.notch;

import android.app.Activity;
import android.os.Build;
import android.provider.Settings;

import java.lang.reflect.Method;

/**
 * Created By wind
 * on 2020/3/14
 */
class HwNotch extends AndroidNotch {
    //用户是否设置了隐藏刘海
    public static final String DISPLAY_NOTCH_STATUS = "display_notch_status";//0 显示刘海， 1 隐藏刘海

    @Override
    public boolean isNotch(Activity activity) {
        boolean notch = false;
        int notchSwitchOpen =
                Settings.Secure.getInt(activity.getContentResolver(), DISPLAY_NOTCH_STATUS, 0);//0表示“默认”， 1表示“隐藏显示区域”


        if (notchSwitchOpen==0) {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {
                notch = hwNotch(activity);
            } else {
                //厂商没有适配 android P 检测刘海的官方接口
                notch = super.isNotch(activity);
                if (!notch) {//所以为false时再调用一次厂商刘海接口
                    notch = hwNotch(activity);
                }
            }
        }

        return notch;
    }


    private boolean hwNotch(Activity activity) {
        boolean notch = false;
        try {
            ClassLoader cl = activity.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            notch = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notch;
    }

}
