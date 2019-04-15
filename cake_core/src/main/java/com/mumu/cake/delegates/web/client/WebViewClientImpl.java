package com.mumu.cake.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mumu.cake.app.Cake;
import com.mumu.cake.delegates.IPageLoadListener;
import com.mumu.cake.delegates.web.WebDelegate;
import com.mumu.cake.delegates.web.route.Router;
import com.mumu.cake.ui.loader.CakeLoader;
import com.mumu.cake.util.log.CakeLogger;

/**
 * @ClassName: WebViewClientImpl
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 10:07
 * @Version: 1.0
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Cake.getHandler();

    public void setPageLoadListener(IPageLoadListener listener){
        this.mIPageLoadListener = listener;
    }
    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        CakeLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if(mIPageLoadListener != null){
            mIPageLoadListener.onLoadStart();
        }
        CakeLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if(mIPageLoadListener != null){
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                CakeLoader.stopLoading();
            }
        }, 1000);
    }
}
