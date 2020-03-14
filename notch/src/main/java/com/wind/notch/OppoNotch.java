package com.wind.notch;

import android.app.Activity;
import android.os.Build;

/**
 * Created By wind
 * on 2020/3/14
 * https://open.oppomobile.com/wiki/doc#id=10159
 */
class OppoNotch extends AndroidNotch{

    @Override
    public boolean isNotch(Activity activity) {

        if (Build.VERSION.SDK_INT<=Build.VERSION_CODES.O){
            return activity.getPackageManager().
                    hasSystemFeature("com.oppo.feature.screen.heteromorphism");

        }else {
            return super.isNotch(activity);
        }
    }
}
