package com.mumu.cake.delegates.web;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * @ClassName: WebViewInitializer
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 10:34
 * @Version: 1.0
 */
public class WebViewInitializer {

    @SuppressLint("SetJavaScriptEnabled")
    public WebView createWebView(WebView webView) {
        /**允许调试*/
        WebView.setWebContentsDebuggingEnabled(true);
        /**不允许横向滚动*/
        webView.setHorizontalScrollBarEnabled(false);
        /**不允许纵向滚动*/
        webView.setVerticalScrollBarEnabled(false);
        /**允许截图*/
        webView.setDrawingCacheEnabled(true);
        /**屏幕长按事件*/
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        /**初始化WebSettings*/
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        final String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + "Cake");
        /**隐藏播放控件*/
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        /**禁止缩放*/
        settings.setSupportZoom(false);
        /**文件权限*/
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        /**缓存相关*/
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        return webView;
    }
}
