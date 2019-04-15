package com.mumu.cake.app;

import android.content.Context;
import android.os.Handler;

/**
 * @ClassName: Cake
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/10 22:15
 * @Version: 1.0
 */
public final class Cake {

    public static Configurator init(Context context){
        Configurator.getInstance()
                .getCakeConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static Context getApplicationContext(){
        return  (Context) getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

}
