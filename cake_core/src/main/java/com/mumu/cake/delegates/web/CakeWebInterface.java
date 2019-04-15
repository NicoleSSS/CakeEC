package com.mumu.cake.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.mumu.cake.delegates.web.event.Event;
import com.mumu.cake.delegates.web.event.EventManager;
import com.mumu.cake.util.log.CakeLogger;


/**
 * @ClassName: CakeWebInterface
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 8:25
 * @Version: 1.0
 */
public final class CakeWebInterface {

    private final WebDelegate DELEGATE;

    private CakeWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static CakeWebInterface create(WebDelegate delegate){
        return new CakeWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        CakeLogger.d("WEB_EVENT",params);
        if(event != null){
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
