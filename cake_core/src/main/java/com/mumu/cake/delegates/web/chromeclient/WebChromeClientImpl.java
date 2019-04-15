package com.mumu.cake.delegates.web.chromeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * @ClassName: WebChromeClientImpl
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 10:49
 * @Version: 1.0
 */
public class WebChromeClientImpl extends WebChromeClient {

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
