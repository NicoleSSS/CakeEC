package com.mumu.cake.ui.dimen;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @ClassName: ScreenUtils
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/27 10:08
 * @Version: 1.0
 */
public class ScreenUtils {

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }
}
