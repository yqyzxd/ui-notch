package com.wind.ui.notch;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.wind.notch.Notchs;
import com.wind.rom.detector.Rom;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1. 设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        ImageView iv = findViewById(R.id.iv);
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584174825641&di=fe71461ac3daac61ed854fae96a8d3a3&imgtype=0&src=http%3A%2F%2Fy2.ifengimg.com%2F5a18722e4865e716%2F2012%2F1215%2Frdn_50cbeff733531.jpg";
        Glide.with(this)
                .load(url)
                .into(iv);


        //3. 设置window属性允许布局延伸进刘海区  需要在第一步操作 否则miui刘海区将返回null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            /**
             *
             *   #LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT  全屏模式，内容下移，非全屏不受影响
             *   #LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES  允许内容去延伸进刘海区(需要结合沉浸式状态栏才行)
             *   #LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER  不允许内容延伸进刘海区
             */
            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(params);


        }
        //4. 设置成沉浸式
        int flags = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        int visibility = getWindow().getDecorView().getSystemUiVisibility();
        visibility |= flags; //追加沉浸式设置
        getWindow().getDecorView().setSystemUiVisibility(visibility);


        iv.post(new Runnable() {
            @Override
            public void run() {
                //2. 判断是否有刘海
                boolean hasNotch = Notchs.getInstance().isNotch(MainActivity.this);
                System.out.println("hasNotch:" + hasNotch);
            }
        });


        //5. 控件避开刘海区
        int notchHeight = Notchs.getInstance().getNotchHeight(this);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                @Override

                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                        DisplayCutout cutout = windowInsets.getDisplayCutout();

                        System.out.println("cutout==null:"+(cutout==null));

                    }

                    return windowInsets;

                }
            });
        }*/

    }


}
