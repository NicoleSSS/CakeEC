package com.mumu.cake.wechat;

import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @ClassName: BaseWXActivity
 * @Description:
 * @Author: 范琳琳
 * @CreateDate: 2019/3/14 17:15
 * @Version: 1.0
 */
public abstract class BaseWXActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**这个必须写在onCreate中*/
        CakeWeChat.getInstance().getWXAPI().handleIntent(getIntent(),this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
}
