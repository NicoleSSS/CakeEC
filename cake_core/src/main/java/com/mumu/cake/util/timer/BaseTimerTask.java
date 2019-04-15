package com.mumu.cake.util.timer;

import java.util.TimerTask;

/**
 * @ClassName: BaseTimerTask
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/12 16:29
 * @Version: 1.0
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if(mITimerListener != null){
            mITimerListener.onTimer();
        }
    }
}
