package com.mumu.cake.ec.main.personal.settings;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.main.personal.list.ListBean;

/**
 * @ClassName: SettingsClickListener
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/25 22:03
 * @Version: 1.0
 */
public class SettingsClickListener extends SimpleClickListener {

    private final CakeDelegate DELEGATE;

    public SettingsClickListener(CakeDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                //这是消息推送的开关
                break;
            case 2:
                DELEGATE.getSupportDelegate().start(bean.getDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
