package com.mumu.cakeec.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.IError;
import com.mumu.cake.net.callback.IFailure;
import com.mumu.cake.net.callback.ISuccess;

import androidx.annotation.NonNull;


/**
 * @ClassName: ExampleDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/11 9:32
 * @Version: 1.0
 */
public class ExampleDelegate extends CakeDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                //.url("https://news.baidu.com/")
                .url("http://192.168.1.7:8888/cake/data")
                //.params("", "")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
//                        convert(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }


    @Override
    public void post(Runnable runnable) {

    }
}
