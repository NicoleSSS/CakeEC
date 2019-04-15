package com.mumu.cake.ec.main.discover;

import android.os.Bundle;
import android.view.View;

import com.mumu.cake.delegates.bottom.BottomItemDelegate;
import com.mumu.cake.delegates.web.WebDelegateImpl;
import com.mumu.cake.ec.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @ClassName: DiscoverDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 7:33
 * @Version: 1.0
 */
public class DiscoverDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, delegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
