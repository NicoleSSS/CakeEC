package com.mumu.test;

import android.os.Bundle;

import com.mumu.cake.activities.ProxyActivity;
import com.mumu.cake.app.Cake;
import com.mumu.cake.delegates.CakeDelegate;

import androidx.appcompat.app.ActionBar;

public class TestActivity extends ProxyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        Cake.getConfigurator().withActivity(this);
    }

    @Override
    public CakeDelegate setRootDelegate() {
        return new TestDelegate();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
