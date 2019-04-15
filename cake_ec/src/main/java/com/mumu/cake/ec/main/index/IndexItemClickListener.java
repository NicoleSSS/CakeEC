package com.mumu.cake.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.detail.GoodsDetailDelegate;
import com.mumu.cake.ui.recycler.MultipleFields;
import com.mumu.cake.ui.recycler.MultipleItemEntity;

/**
 * @ClassName: IndexItemClickListener
 * @Description: 页面点击事件监听
 * @Author: 范琳琳
 * @CreateDate: 2019/3/17 16:43
 * @Version: 1.0
 */
public class IndexItemClickListener extends SimpleClickListener{

    private final CakeDelegate DELEGATE;

    private IndexItemClickListener(CakeDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(CakeDelegate delegate){
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity)baseQuickAdapter.getData().get(position);
        final int goodsId = entity.getField(MultipleFields.ID);
        final GoodsDetailDelegate detailDelegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(detailDelegate);
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
