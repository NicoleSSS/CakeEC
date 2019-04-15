package com.mumu.cake.ec.pay;

import android.app.Activity;
import android.os.AsyncTask;

import com.alipay.sdk.app.PayResultActivity;
import com.alipay.sdk.app.PayTask;
import com.mumu.cake.app.Cake;
import com.mumu.cake.ui.loader.CakeLoader;
import com.mumu.cake.util.log.CakeLogger;

import java.util.Map;

/**
 * @ClassName: PayAsyncTask
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/22 21:02
 * @Version: 1.0
 */
public class PayAsyncTask extends AsyncTask<String, Void, Map<String,String>> {

    private final Activity ACTIVITY;
    private final IAlipayResultListener LISTENER;

    /**订单支付成功*/
    private static final String ALI_PAY_STATUS_SUCCESS = "9000";
    /**订单支付中*/
    private static final String ALI_PAY_STATUS_PAYING = "8000";
    /**订单支付失败*/
    private static final String ALI_PAY_STATUS_FAIL = "4000";
    /**订单支付取消*/
    private static final String ALI_PAY_STATUS_CANCEL = "6001";
    /**网络错误*/
    private static final String ALI_PAY_STATUS_CONNECT_ERROR = "6002";




    public PayAsyncTask(Activity activity, IAlipayResultListener listener) {
        this.ACTIVITY = activity;
        this.LISTENER = listener;
    }

    @Override
    protected Map<String, String> doInBackground(String... params) {
        final String aliPaySign = params[0];
        final PayTask payTask = new PayTask(ACTIVITY);
        return payTask.payV2(aliPaySign, true);
    }

    @Override
    protected void onPostExecute(Map<String,String> result) {
        super.onPostExecute(result);
        CakeLoader.stopLoading();
        final PayResult payResult = new PayResult(result);
        /**支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约是支付宝提供的公钥做验签*/
        final String resultInfo = payResult.getResult();// 同步返回需要验证的信息
        final String resultStatus = payResult.getResultStatus();
        CakeLogger.d("ALI_PAY_RESULT",resultInfo);
        CakeLogger.d("ALI_PAY_RESULT",resultStatus);

        switch (resultStatus){
            case ALI_PAY_STATUS_SUCCESS:
                if(LISTENER != null){
                    LISTENER.onPaySucess();
                }
                break;
            case ALI_PAY_STATUS_FAIL:
                if(LISTENER != null){
                    LISTENER.onPayFail();
                }
                break;
            case ALI_PAY_STATUS_PAYING:
                if(LISTENER != null){
                    LISTENER.onPaying();
                }
                break;
            case ALI_PAY_STATUS_CANCEL:
                if(LISTENER != null){
                    LISTENER.onPayCancel();
                }
                break;
            case ALI_PAY_STATUS_CONNECT_ERROR:
                if(LISTENER != null){
                    LISTENER.onPayConnectError();
                }
                break;
            default:
                break;

        }
    }
}
