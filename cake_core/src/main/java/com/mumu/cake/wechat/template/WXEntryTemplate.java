package com.mumu.cake.wechat.template;

import com.mumu.cake.wechat.BaseWXEntryActivity;
import com.mumu.cake.wechat.CakeWeChat;

/**
 * @ClassName: WXEntryTemplate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/13 17:26
 * @Version: 1.0
 */
public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        CakeWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);

    }
}
