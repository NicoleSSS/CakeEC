package com.mumu.cake.ec.pay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mumu.cake.app.Cake;
import com.mumu.cake.app.ConfigKeys;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.ISuccess;
import com.mumu.cake.ui.loader.CakeLoader;
import com.mumu.cake.util.log.CakeLogger;
import com.mumu.cake.wechat.CakeWeChat;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import androidx.appcompat.app.AlertDialog;

/**
 * @ClassName: FastPay
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/22 19:58
 * @Version: 1.0
 */
public class FastPay implements View.OnClickListener {

    /**设置支付回调监听*/
    private IAlipayResultListener mIAlipayResultListener = null;
    private Activity mActivity = null;

    private AlertDialog mDialog = null;
    private int mOrderID = -1;

    private FastPay(CakeDelegate delegate){
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(CakeDelegate delegate){
        return new FastPay(delegate);

    }

    public void beginPayDialog(){
        mDialog.show();
        final Window window = mDialog.getWindow();
        if(window != null){
            window.setContentView(R.layout.dialog_pay_pannel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_pannel_upo_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            /**设置属性*/
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);

            window.findViewById(R.id.btn_dialog_pay_alipay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }
    }

    public FastPay setPayResultListener(IAlipayResultListener listener){
        this.mIAlipayResultListener = listener;
        return this;
    }

    public FastPay setOrderId(int orderId){
        this.mOrderID = orderId;
        return this;
    }

    private final void aliPay(int orderId){
        final String singUrl = "http://app.api.zanzuanshi.com/api/v1/alipay/a="+orderId;
        /**获取签名字符串*/
        RestClient.builder()
                .url(singUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String paySign = JSON.parseObject(response).getString("result");
                        CakeLogger.d("PAY_SIGN",paySign);
                        /**必须是异步的调用客户端支付接口*/
                        final PayAsyncTask payAsyncTask = new PayAsyncTask(mActivity, mIAlipayResultListener);
                        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);
                    }
                })
                .build()
                .post();
    }

    private final void weChatPay(int orderId){
        CakeLoader.stopLoading();
        final String weChatPrePayUrl = "";
        CakeLogger.d("WX_PAY",weChatPrePayUrl);

        final IWXAPI iwxapi = CakeWeChat.getInstance().getWXAPI();
        final String appId = Cake.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
        iwxapi.registerApp(appId);
        RestClient.builder()
                .url(weChatPrePayUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject result =
                                JSON.parseObject(response).getJSONObject("result");

                        final String prepayId = result.getString("prepayid");
                        final String partnerId = result.getString("partnerid");
                        final String packageValue = result.getString("package");
                        final String timestamp = result.getString("timestamp");
                        final String nonceStr = result.getString("noncestr");
                        final String paySign = result.getString("sign");

                        final PayReq payReq = new PayReq();
                        payReq.appId = appId;
                        payReq.prepayId = prepayId;
                        payReq.partnerId = partnerId;
                        payReq.packageValue = packageValue;
                        payReq.timeStamp = timestamp;
                        payReq.nonceStr = nonceStr;
                        payReq.sign = paySign;

                        iwxapi.sendReq(payReq);

                    }
                })
                .build()
                .post();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_dialog_pay_alipay){
            aliPay(mOrderID);
            mDialog.cancel();
        }else if(id == R.id.btn_dialog_pay_wechat){
            weChatPay(mOrderID);
            mDialog.cancel();
        }else if(id == R.id.btn_dialog_pay_cancel){
            mDialog.cancel();
        }
    }
}
