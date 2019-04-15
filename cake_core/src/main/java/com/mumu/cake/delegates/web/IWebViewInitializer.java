package com.mumu.cake.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @ClassName: IWebViewInitializer
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 7:45
 * @Version: 1.0
 */
public interface IWebViewInitializer {

    /**解析、渲染网页*/
    WebView initWebView(WebView webView);

    /**主要帮助WebView处理各种通知、请求时间的*/
    WebViewClient initWebViewClient();

    /**主要辅助WebView处理JavaScript的对话框、网站图片、网站title、加载进度*/
    WebChromeClient initWebChromeClient();
}
