package com.wind.notch;

import android.app.Activity;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;

/**
 * Created By wind
 * on 2020/3/14
 *
 * android P 才支持 刘海接口
 *
 * 不能直接在 @link{android.app.Activity}的 onCreate里直接调用，因此此时还未进行布局，无法获得WindowInsets；如需则用post
 */
class AndroidNotch implements Notchable{
    @Override
    public boolean isNotch(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Window window=activity.getWindow();
            DisplayCutout displayCutout;
            View rootView = window.getDecorView();

            WindowInsets insets = rootView.getRootWindowInsets();
            if (insets != null) {
                displayCutout = insets.getDisplayCutout();
                if (displayCutout != null) {
                    if (displayCutout.getBoundingRects() != null &&
                            displayCutout.getBoundingRects().size() > 0 &&
                            displayCutout.getSafeInsetTop() > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
