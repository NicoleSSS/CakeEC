package com.mumu.cake.delegates.web;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mumu.cake.delegates.IPageLoadListener;
import com.mumu.cake.delegates.web.chromeclient.WebChromeClientImpl;
import com.mumu.cake.delegates.web.client.WebViewClientImpl;
import com.mumu.cake.delegates.web.route.RouteKeys;
import com.mumu.cake.delegates.web.route.Router;

import androidx.annotation.NonNull;

/**
 * @ClassName: WebDelegateImpl
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 8:32
 * @Version: 1.0
 */
public class WebDelegateImpl extends WebDelegate implements IWebViewInitializer{

    private IPageLoadListener mIPageLoadListener = null;

    public static WebDelegateImpl create(String url){
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    public void setPageLoadListener(IPageLoadListener loadListener){
        this.mIPageLoadListener = loadListener;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        if(getUrl() != null){
            /**用原生的方式模拟Web跳转并进行页面加载*/
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
