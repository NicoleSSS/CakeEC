package com.mumu.cake.ec.main.sort;

import android.os.Bundle;
import android.view.View;

import com.mumu.cake.delegates.bottom.BottomItemDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.main.sort.content.ContentDelegate;
import com.mumu.cake.ec.main.sort.list.VerticalListDelegate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ClassName: SortDelegate
 * @Description:
 * @Author: 范琳琳
 * @CreateDate: 2019/3/14 23:33
 * @Version: 1.0
 */
public class SortDelegate extends BottomItemDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container,ContentDelegate.newInstance(1));
    }


}
