package com.wind.notch;

import android.app.Activity;

import com.wind.rom.detector.Rom;

/**
 * Created By wind
 * on 2020/3/14
 */
public class Notchs implements Notchable {

    private Notchable notchable;

    private static Notchs sInstance=null;
    private Notchs(){
        if (Rom.isEmui()){
            notchable=new HwNotch();
        }else if (Rom.isMiui()){
            notchable=new MiNotch();
        }else if (Rom.isOppo()){
            notchable=new OppoNotch();
        }else if (Rom.isVivo()){
            notchable=new VivoNotch();
        }else {
            notchable=new AndroidNotch();
        }
    }

    public static Notchs getInstance(){
        if (sInstance==null){
            synchronized (Notchs.class){
                if (sInstance==null){
                    sInstance=new Notchs();
                }
            }
        }
        return sInstance;
    }

    @Override
    public boolean isNotch(Activity activity) {
        return notchable.isNotch(activity);
    }
}
