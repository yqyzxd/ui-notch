package com.wind.notch;

import android.app.Activity;

import java.lang.reflect.Method;

/**
 * Created By wind
 * on 2020/3/14
 * https://dev.vivo.com.cn/documentCenter/doc/103
 */
class VivoNotch extends AndroidNotch {
    /**
     * Vivo判断是否有刘海， Vivo的刘海高度小于等于状态栏高度
     */
    private static final int VIVO_MASK_NOTCH = 0x00000020;//是否有刘海
    private static final int VIVO_MASK_ROUND_CORNERS = 0x00000008;//是否有圆角
    @Override
    public boolean isNotch(Activity activity) {
        boolean notch = false;
        try {
            ClassLoader classLoader = activity.getClassLoader();
            Class FtFeature = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            notch = (boolean) method.invoke(FtFeature, VIVO_MASK_NOTCH);
        } catch (Exception e) {
            notch=super.isNotch(activity);
        }
        return notch;
    }




}
