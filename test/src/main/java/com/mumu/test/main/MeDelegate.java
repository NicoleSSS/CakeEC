package com.mumu.test.main;

import android.os.Bundle;
import android.view.View;

import com.mumu.cake.delegates.bottom.BottomItemDelegate;
import com.mumu.test.R;

import androidx.annotation.NonNull;

/**
 * @ClassName: MeDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/28 19:00
 * @Version: 1.0
 */
public class MeDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_me;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
