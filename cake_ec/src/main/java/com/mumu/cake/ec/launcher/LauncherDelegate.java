package com.mumu.cake.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.mumu.cake.app.AccountManager;
import com.mumu.cake.app.IUserChecker;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.R2;
import com.mumu.cake.ui.launcher.ILauncherListener;
import com.mumu.cake.ui.launcher.OnLauncherFinishTag;
import com.mumu.cake.ui.launcher.ScrollLauncherTag;
import com.mumu.cake.util.storage.CakePreference;
import com.mumu.cake.util.timer.BaseTimerTask;
import com.mumu.cake.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: LauncherDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/12 16:27
 * @Version: 1.0
 */
public class LauncherDelegate extends CakeDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    /**倒计时的数字*/
    private int mCount = 5;

    private ILauncherListener mILauncherListener = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0,1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener)activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    /**
     * 判断是否显示滑动启动页
     */
    private void checkIsShowScroll(){
        if(!CakePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            getSupportDelegate().start(new LauncherScrollDelegate(),SINGLETASK);
        }else{
            /**检查用户是否已经登录*/
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if(mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNoSignIn() {
                    if(mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTvTimer != null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if(mCount < 0){
                        if(mTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void post(Runnable runnable) {

    }
}
