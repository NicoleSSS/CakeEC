package com.mumu.cake.net.callback;

import android.os.Handler;


import com.mumu.cake.ui.loader.CakeLoader;
import com.mumu.cake.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ClassName: RequestCallbacks
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/11 11:27
 * @Version: 1.0
 */
public class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest request, ISuccess success,
                            IFailure failure, IError error,
                            LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else{
            if(ERROR != null){
                ERROR.onError(response.code(), response.message());
            }
        }
        if(LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    CakeLoader.stopLoading();
                }
            }, 1000);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE != null){
            FAILURE.onFailure();
        }
        if(REQUEST != null){
            REQUEST.onRequestEnd();
        }
    }
}
