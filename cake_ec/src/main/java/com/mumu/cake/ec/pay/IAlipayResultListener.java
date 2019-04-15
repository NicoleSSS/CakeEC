package com.mumu.cake.ec.pay;

/**
 * @ClassName: IAlipayResultListener
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/22 20:03
 * @Version: 1.0
 */
public interface IAlipayResultListener {

    void onPaySucess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();

}
