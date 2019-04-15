package com.mumu.cakeec.example.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.mumu.cake.util.log.CakeLogger;
import com.mumu.cakeec.example.ExampleActivity;

import java.util.Set;

import androidx.core.content.ContextCompat;
import cn.jpush.android.api.JPushInterface;

/**
 * @ClassName: PushReceiver
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/25 19:15
 * @Version: 1.0
 */
public class PushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        final Set<String> kegys = bundle.keySet();
        JSONObject json = new JSONObject();
        for (String key : kegys) {
            final Object val = bundle.get(key);
            json.put(key, val);
        }

        CakeLogger.json("PushReceiver", json.toJSONString());

        /**获取事件类型*/
        final String pushAction = intent.getAction();
        if(pushAction.equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)){
            /**处理接收到的数据*/
            onReceiveMessage(bundle);
        }else if(pushAction.equals(JPushInterface.ACTION_NOTIFICATION_OPENED)){
            /**打开相应的Notification*/
            onOpenNotification(context, bundle);
        }
    }

    private void onReceiveMessage(Bundle bundle){
        final String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        final String msgId = bundle.getString(JPushInterface.EXTRA_MSG_ID);
        final int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
        final String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        final String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        final String alert = bundle.getString(JPushInterface.EXTRA_ALERT);
    }

    private void onOpenNotification(Context context, Bundle bundle) {
        final String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        final Bundle openActivityBundle = new Bundle();
        /**跳转到相应的Activity*/
        final Intent intent = new Intent(context, ExampleActivity.class);
        intent.putExtras(openActivityBundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ContextCompat.startActivity(context, intent, null);
    }
}
