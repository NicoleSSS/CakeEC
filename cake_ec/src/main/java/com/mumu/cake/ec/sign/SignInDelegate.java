package com.mumu.cake.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.R2;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.ISuccess;
import com.mumu.cake.util.log.CakeLogger;
import com.mumu.cake.wechat.CakeWeChat;
import com.mumu.cake.wechat.callbacks.IWeChatSignInCallback;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: SignInDelegate
 * @Description: 登录页面
 * @Author: 范琳琳
 * @CreateDate: 2019/3/12 23:12
 * @Version: 1.0
 */
public class SignInDelegate extends CakeDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener){
            mISignListener = (ISignListener)activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        checkForm();
        RestClient.builder()
                .url("sign_up")
                .params("email", mEmail.getText().toString())
                .params("password", mPassword.getText().toString())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        CakeLogger.json("USER_PROFILE", response);
                        SignHandler.onSingIn(response, mISignListener);
                    }
                })
                .build()
                .post();
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat(){
        CakeWeChat.getInstance().onSignSuccess(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {
                Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
            }
        }).signIn();
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        getSupportDelegate().start(new SignUpDelegate());
    }

    private boolean checkForm(){
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if(email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误邮箱格式");
            isPass = false;
        }else{
            mEmail.setError(null);
        }

        if(password.isEmpty() || password.length() < 6){
            mPassword.setError("请填写至少6位密码");
            isPass = false;
        }else{
            mPassword.setError(null);
        }

        return isPass;

    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
