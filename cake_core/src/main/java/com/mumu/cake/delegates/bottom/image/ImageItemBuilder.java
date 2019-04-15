package com.mumu.cake.delegates.bottom.image;

import com.mumu.cake.delegates.bottom.BottomItemDelegate;

import java.util.LinkedHashMap;

/**
 * @ClassName: ItemBuilder
 * @Description: 构造器
 * @Author: 范琳琳
 * @CreateDate: 2019/3/14 20:59
 * @Version: 1.0
 */
public final class ImageItemBuilder {

    /**LinkedHashMap保证Tab的有序*/
    private final LinkedHashMap<ImageBottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ImageItemBuilder builder(){
        return new ImageItemBuilder();
    }

    public final ImageItemBuilder addItem(ImageBottomTabBean bean, BottomItemDelegate delegate){
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ImageItemBuilder addItems(LinkedHashMap<ImageBottomTabBean, BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<ImageBottomTabBean, BottomItemDelegate> build(){
        return ITEMS;
    }
}
