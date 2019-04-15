package com.mumu.cake.ec.main.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.main.personal.list.ListBean;

/**
 * @ClassName: PersonalClickListener
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/25 18:06
 * @Version: 1.0
 */
public class PersonalClickListener extends SimpleClickListener {

    private final CakeDelegate DELEGATE;

    public PersonalClickListener(CakeDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean)baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id){
            case 1:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
                break;
            case 2:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
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
