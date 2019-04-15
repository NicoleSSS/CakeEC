package com.mumu.cake.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.delegates.web.WebDelegate;
import com.mumu.cake.delegates.web.WebDelegateImpl;

import androidx.core.content.ContextCompat;

/**
 * @ClassName: Router
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 10:09
 * @Version: 1.0
 */
public class Router {

    private Router(){
    }

    private static class Holder{
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance(){
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate, String url){
        /**如果是电话协议*/
        if(url.contains("tel:")){
            callPhone(delegate.getContext(), url);
            return true;
        }
        final CakeDelegate topDelegateDelegate = delegate.getTopDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegateDelegate.getSupportDelegate().start(webDelegate);
        return true;
    }

    private void loadWebPage(WebView webView,String url){
        if(webView != null){
            webView.loadUrl(url);
        }else{
            throw new NullPointerException("WEBVIEW IS NULL");
        }
    }

    private void loadLocalPage(WebView webView,String url){
        loadWebPage(webView, "file:///android_asset/"+url);
    }

    private void loadPage(WebView webView,String url){
        if(URLUtil.isNetworkUrl(url)|| URLUtil.isAssetUrl(url)){
            loadWebPage(webView, url);
        }else {
            loadLocalPage(webView, url);
        }
    }

    public final void loadPage(WebDelegate delegate,String url){
        loadPage(delegate.getWebView(), url);
    }


    private void callPhone(Context context,String uri){
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent,null);

    }
}
