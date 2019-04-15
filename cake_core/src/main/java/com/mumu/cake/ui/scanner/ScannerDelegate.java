package com.mumu.cake.ui.scanner;

import android.os.Bundle;
import android.view.View;

import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ui.camera.RequestCodes;
import com.mumu.cake.util.callback.CallbackManager;
import com.mumu.cake.util.callback.CallbackType;
import com.mumu.cake.util.callback.IGlobalCallback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * @ClassName: ScannerDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/26 15:18
 * @Version: 1.0
 */
public class ScannerDelegate extends CakeDelegate implements ZBarScannerView.ResultHandler {

    private ScanView mScanView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mScanView == null){
            mScanView = new ScanView(getContext());
        }
        mScanView.setAutoFocus(true);
        mScanView.setResultHandler(this);
    }

    @Override
    public Object setLayout() {
        return mScanView;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if(mScanView != null){
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mScanView != null){
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        @SuppressWarnings("unchecked")
        final IGlobalCallback<String> callback =  CallbackManager
                .getInstance()
                .getCallback(CallbackType.ON_SCAN);
        if(callback != null){
            callback.executeCallback(rawResult.getContents());
        }
        getSupportDelegate().pop();

    }
}
