package com.mumu.cake.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.mumu.cake.delegates.web.WebDelegate;

/**
 * @ClassName: Event
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 11:58
 * @Version: 1.0
 */
public abstract class Event implements IEvent{

    private Context mContext = null;
    private String mAction = null;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebview = null;

    public Context getContext() {
        return mContext;
    }

    public WebView getWebview(){
        return mDelegate.getWebView();
    }
    public void setContext(Context context) {
        this.mContext = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        this.mAction = action;
    }

    public WebDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate delegate) {
        this.mDelegate = delegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}
