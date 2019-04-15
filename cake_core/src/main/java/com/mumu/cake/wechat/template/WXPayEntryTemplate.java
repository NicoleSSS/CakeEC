package com.mumu.cake.wechat.template;

import android.widget.Toast;

import com.mumu.cake.activities.ProxyActivity;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.wechat.BaseWXPayEntryActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;

/**
 * @ClassName: WXPayEntryTemplate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/13 17:27
 * @Version: 1.0
 */
public class WXPayEntryTemplate extends BaseWXPayEntryActivity {

    @Override
    protected void onPaySuccesss() {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        fileList();
        overridePendingTransition(0, 0);

    }

    @Override
    protected void onPayFail() {
        Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
        fileList();
        overridePendingTransition(0, 0);

    }

    @Override
    protected void onPayCancel() {
        Toast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
        fileList();
        overridePendingTransition(0, 0);

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}
