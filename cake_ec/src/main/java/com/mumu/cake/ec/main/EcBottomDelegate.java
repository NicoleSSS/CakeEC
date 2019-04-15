package com.mumu.cake.ec.main;

import android.graphics.Color;

import com.mumu.cake.delegates.bottom.icon.BaseBottomDelegate;
import com.mumu.cake.delegates.bottom.BottomItemDelegate;
import com.mumu.cake.delegates.bottom.icon.BottomTabBean;
import com.mumu.cake.delegates.bottom.icon.ItemBuilder;
import com.mumu.cake.ec.main.cart.ShopCartDelegate;
import com.mumu.cake.ec.main.discover.DiscoverDelegate;
import com.mumu.cake.ec.main.index.IndexDelegate;
import com.mumu.cake.ec.main.personal.PersonalDelegate;
import com.mumu.cake.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * @ClassName: EcBottomDelegate
 * @Description:
 * @Author: 范琳琳
 * @CreateDate: 2019/3/14 23:32
 * @Version: 1.0
 */
public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {

        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();

        items.put(new BottomTabBean("{fa-home}", "首页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());

        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }

    @Override
    public void post(Runnable runnable) {

    }
}
