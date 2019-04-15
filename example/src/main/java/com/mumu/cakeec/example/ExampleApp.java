package com.mumu.cakeec.example;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mumu.cake.app.Cake;
import com.mumu.cake.util.callback.CallbackManager;
import com.mumu.cake.util.callback.CallbackType;
import com.mumu.cake.util.callback.IGlobalCallback;
import com.mumu.cakeec.example.event.ShareEvent;
import com.mumu.cakeec.example.event.TestEvent;
import com.mumu.cake.ec.database.DatabaseManager;
import com.mumu.cake.ec.icon.FontEcModule;
import com.mumu.cake.net.interceptors.DebugInterceptor;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;
import cn.jpush.android.api.JPushInterface;

/**
 * @ClassName: ExampleApp
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/11 9:30
 * @Version: 1.0
 */
public class ExampleApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Cake.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://192.168.1.9:8888/cake/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withJavascriptInterface("cake")
                .withWebEvent("test", new TestEvent())
                .withWebEvent("share", new ShareEvent())
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);

        initPush();
        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@NonNull Object args) {
                        if(JPushInterface.isPushStopped(Cake.getApplicationContext())){
                            initPush();
                        }
                    }
                }).addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
            @Override
            public void executeCallback(@NonNull Object args) {
                if(! JPushInterface.isPushStopped(Cake.getApplicationContext())){
                    JPushInterface.stopPush(Cake.getApplicationContext());
                }
            }
        });
    }

    private void initPush(){
        /**开启极光推送*/
        JPushInterface.setDebugMode(true);
        JPushInterface.init(Cake.getApplicationContext());
    }

    private void initStetho(){
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
