package com.mumu.cake.wechat;

import com.alibaba.fastjson.JSONObject;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.IError;
import com.mumu.cake.net.callback.IFailure;
import com.mumu.cake.net.callback.ISuccess;
import com.mumu.cake.util.log.CakeLogger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 * @ClassName: BaseWXEntryActivity
 * @Description:
 * @Author: 范琳琳
 * @CreateDate: 2019/3/14 17:18
 * @Version: 1.0
 */
public abstract class BaseWXEntryActivity extends BaseWXActivity{

    /**用户登录成功后回调*/
    protected abstract void onSignInSuccess(String userInfo);

    /**微信发送请求到第三方应用后的回调*/
    @Override
    public void onReq(BaseReq baseReq) {
        //û�е���
    }

    /**第三方应用发送请求到微信后的回调*/
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp)baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(CakeWeChat.APP_ID)
                .append("&secret=")
                .append(CakeWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

        CakeLogger.d("authUrl",authUrl.toString());
        getAuth(authUrl.toString());
    }

    private void getAuth(String authUrl){
        RestClient
                .builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject authObj = JSONObject.parseObject(response);
                        final String accessToken = authObj.getString("access-token");
                        final String openId = authObj.getString("openid");
                        final StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl
                                .append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");
                        CakeLogger.d("userInfoUrl",userInfoUrl.toString());
                        getUserInfo(userInfoUrl.toString());
                    }
                })
                .build()
                .get();
    }

    private void getUserInfo(String userInfo){
        RestClient
                .builder()
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignInSuccess(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
