package com.mumu.cakeec.example.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.mumu.cake.delegates.web.event.Event;

/**
 * @ClassName: TestEvent
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 12:13
 * @Version: 1.0
 */
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if(getAction().equals("test")){
            final WebView webView = getWebview();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
