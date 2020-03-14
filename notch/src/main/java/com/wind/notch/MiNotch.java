package com.wind.notch;

import android.app.Activity;
import android.os.Build;

import java.lang.reflect.Method;

/**
 * Created By wind
 * on 2020/3/14
 * https://dev.mi.com/console/doc/detail?pId=1293
 */
class MiNotch extends AndroidNotch {

    /**
     * @param activity
     * @return
     */
    @Override
    public boolean isNotch(Activity activity) {

        if (Build.VERSION.SDK_INT<=Build.VERSION_CODES.O){
            //android O
            //系统增加了 property ro.miui.notch，值为1时则是 Notch 屏手机。
            try {
                Class propertiesClass=Class.forName("android.os.SystemProperties");
                Method getIntMethod=propertiesClass.getDeclaredMethod("SystemProperties.getInt",String.class,int.class);
                getIntMethod.setAccessible(true);
                int notch= (int) getIntMethod.invoke(null,"ro.miui.notch",0);
                return notch==1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            //android P 及以上使用系统的接口
            return super.isNotch(activity);
        }
        return false;
    }


    /**
     * int resourceId = context.getResources().getIdentifier("notch_height", "dimen", "android");
     * if (resourceId > 0)
     *    result = context.getResources().getDimensionPixelSize(resourceId);
     *
     */

}
