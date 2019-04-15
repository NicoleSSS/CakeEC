package com.mumu.cake.ec.main.personal.settings;

import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.R2;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.ISuccess;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;

/**
 * @ClassName: AboutDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/25 22:06
 * @Version: 1.0
 */
public class AboutDelegate extends CakeDelegate {

    @BindView(R2.id.tv_info)
    AppCompatTextView mTextView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("about")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        mTextView.setText(info);
                    }
                })
                .build()
                .get();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
