package com.mumu.cake.ec.main.personal.settings;

import android.os.Bundle;
import android.view.View;

import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;

import androidx.annotation.NonNull;

/**
 * @ClassName: NameDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/24 19:08
 * @Version: 1.0
 */
public class NameDelegate extends CakeDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
