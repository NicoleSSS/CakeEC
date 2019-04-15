package com.mumu.cake.app;

/**
 * @ClassName: ConfigKeys
 * @Description: 单例模式
 * @Author: 范琳琳
 * @CreateDate: 2019/3/10 22:19
 * @Version: 1.0
 */
public enum ConfigKeys {
    /**配置网络请求的域名*/
    API_HOST,

    /**全局上下文*/
    APPLICATION_CONTEXT,

    /**控制初始化的完成情况*/
    CONFIG_READY,

    /**字体的初始化项目*/
    ICON,

    /**拦截器*/
    INTERCEPTOR,

    /**延迟时间*/
    LOADER_DELAYED,

    /**微信id*/
    WE_CHAT_APP_ID,

    /**微信secret*/
    WECHAT_APP_SECRET,

    ACTIVITY,
    HANDLER,
    JAVASCRIPT_INTERFACE
}
