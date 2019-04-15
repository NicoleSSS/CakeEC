package com.mumu.cake.ui.loader;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.mumu.cake.R;
import com.mumu.cake.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatDialog;

/**
 * @ClassName: CakeLoader
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/11 18:16
 * @Version: 1.0
 */
public class CakeLoader {

    /**缩放比例*/
    private static final int LOADER_SIZE_SCALE = 8;
    /**偏移量*/
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context, Enum<LoaderStyle> type){
        showLoading(context, type.name());
    }
    public static void showLoading(Context context, String type){

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        /**获取屏幕宽和高*/
        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if(dialogWindow != null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context){
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading(){
        for(AppCompatDialog dialog: LOADERS){
            if(dialog != null){
                if(dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }

}
