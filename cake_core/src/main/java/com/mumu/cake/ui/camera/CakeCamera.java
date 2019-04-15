package com.mumu.cake.ui.camera;

import android.net.Uri;

import com.mumu.cake.delegates.PermissionCheckerDelegate;
import com.mumu.cake.util.file.FileUtil;

/**
 * @ClassName: CakeCamera
 * @Description: 照相机调用类
 * @Author: 范琳琳
 * @CreateDate: 2019/3/24 19:43
 * @Version: 1.0
 */
public class CakeCamera {

    public static Uri createCropFile(){
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate){
        new CameraHandler(delegate).beginCameraDialog();
    }

}
