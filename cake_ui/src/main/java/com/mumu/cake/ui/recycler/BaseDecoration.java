package com.mumu.cake.ui.recycler;


import com.choices.divider.DividerItemDecoration;

import androidx.annotation.ColorInt;

/**
 * @ClassName: BaseDecoration
 * @Description: 分割线
 * @Author: 范琳琳
 * @CreateDate: 2019/3/16 22:31
 * @Version: 1.0
 */
public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size){
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size){
        return new BaseDecoration(color, size);
    }
}
