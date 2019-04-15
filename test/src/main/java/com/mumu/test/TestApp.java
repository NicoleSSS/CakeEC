package com.mumu.test;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mumu.cake.app.Cake;
import com.mumu.cake.net.interceptors.DebugInterceptor;

import androidx.multidex.MultiDexApplication;

/**
 * @ClassName: TestApp
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/28 18:54
 * @Version: 1.0
 */
public class TestApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Cake.init(this)
                .withIcon(new FontAwesomeModule())
                .withLoaderDelayed(1000)
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withApiHost("http://192.168.1.11:8888/alarm/")
                .withJavascriptInterface("cake")
                .configure();
    }
}
