package com.mumu.cake.wechat;


import android.app.Activity;

import com.mumu.cake.app.Cake;
import com.mumu.cake.app.ConfigKeys;
import com.mumu.cake.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @ClassName: CakeWeChat
 * @Description:
 * @Author: 范琳琳
 * @CreateDate: 2019/3/14 16:51
 * @Version: 1.0
 */
public class CakeWeChat {

    public static final String APP_ID = Cake.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Cake.getConfiguration(ConfigKeys.WECHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback;

    private static final class Holder{
        private static final CakeWeChat INSTANCE = new CakeWeChat();
    }

    public static CakeWeChat getInstance(){
        return Holder.INSTANCE;
    }

    private CakeWeChat(){
        final Activity activity = Cake.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI(){
        return WXAPI;
    }

    public CakeWeChat onSignSuccess(IWeChatSignInCallback callback){
        this.mSignInCallback = callback;
        return this;
    }
    public IWeChatSignInCallback getSignInCallback(){
        return mSignInCallback;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }


}
