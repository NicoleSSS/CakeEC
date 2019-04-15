package com.mumu.cake.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.mumu.cake.app.Cake;

/**
 * @ClassName: DimenUtil
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/11 18:27
 * @Version: 1.0
 */
public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Cake.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Cake.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
