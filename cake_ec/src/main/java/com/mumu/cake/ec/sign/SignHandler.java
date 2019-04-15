package com.mumu.cake.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mumu.cake.app.AccountManager;
import com.mumu.cake.ec.database.DatabaseManager;
import com.mumu.cake.ec.database.UserProfile;

/**
 * @ClassName: SignHandler
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/13 14:05
 * @Version: 1.0
 */
public class SignHandler {

    public static void onSingUp(String response, ISignListener signListener){

        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        /**已经注册并登录成功了*/
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }

    public static void onSingIn(String response, ISignListener signListener){

        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        /**已经注册并登录成功了*/
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    //供测试用，暂时跳过登录接口
    public static void onSingIn(ISignListener signListener){
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }
}
