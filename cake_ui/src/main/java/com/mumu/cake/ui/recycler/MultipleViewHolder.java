package com.mumu.cake.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @ClassName: MultipleViewHolder
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/16 18:26
 * @Version: 1.0
 */
public class MultipleViewHolder extends BaseViewHolder {

    private MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view){
        return new MultipleViewHolder(view);
    }
}
