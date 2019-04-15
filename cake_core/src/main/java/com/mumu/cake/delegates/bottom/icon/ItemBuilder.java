package com.mumu.cake.delegates.bottom.icon;

import com.mumu.cake.delegates.bottom.BottomItemDelegate;

import java.util.LinkedHashMap;

/**
 * @ClassName: ItemBuilder
 * @Description: 构造器
 * @Author: 范琳琳
 * @CreateDate: 2019/3/14 20:59
 * @Version: 1.0
 */
public final class ItemBuilder {

    /**LinkedHashMap保证Tab的有序*/
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate){
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build(){
        return ITEMS;
    }
}
