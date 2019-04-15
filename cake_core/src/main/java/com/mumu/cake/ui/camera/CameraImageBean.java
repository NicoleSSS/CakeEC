package com.mumu.cake.ui.camera;

import android.net.Uri;

/**
 * @ClassName: CameraImageBean
 * @Description: 存储一些照相过程的中间值
 * @Author: 范琳琳
 * @CreateDate: 2019/3/24 19:45
 * @Version: 1.0
 */
public final class CameraImageBean {

    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri path) {
        this.mPath = path;
    }
}
