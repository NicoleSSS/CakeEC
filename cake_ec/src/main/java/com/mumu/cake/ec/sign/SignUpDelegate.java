package com.mumu.cake.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.R2;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.ISuccess;
import com.mumu.cake.util.log.CakeLogger;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: SignUpDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/12 22:24
 * @Version: 1.0
 */
public class SignUpDelegate extends CakeDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener){
            mISignListener = (ISignListener)activity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if(checkForm()){
            RestClient.builder()
                    .url("sign_up")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            CakeLogger.json("USER_PROFILE", response);
                            SignHandler.onSingUp(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
            //Toast.makeText(getContext(), "验证通过", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink(){
        getSupportDelegate().start(new SignInDelegate());
    }

    private boolean checkForm(){
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;
        if(name.isEmpty()){
            mName.setError("请输入名字");
            isPass = false;
        }else{
            mName.setError(null);
        }

        if(email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误邮箱格式");
            isPass = false;
        }else{
            mEmail.setError(null);
        }

        if(phone.isEmpty() || phone.length() != 11){
            mPhone.setError("手机号码错误");
            isPass = false;
        }else{
            mPhone.setError(null);
        }

        if(password.isEmpty() || password.length() < 6){
            mPassword.setError("请填写至少6位密码");
            isPass = false;
        }else{
            mPassword.setError(null);
        }

        if(rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword .equals(password))){
            mRePassword.setError("密码验证错误");
            isPass = false;
        }else{
            mRePassword.setError(null);
        }

        return isPass;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
