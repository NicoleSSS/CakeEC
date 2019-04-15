package com.mumu.cake.ec.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mumu.cake.ui.recycler.DataConverter;
import com.mumu.cake.ui.recycler.ItemType;
import com.mumu.cake.ui.recycler.MultipleFields;
import com.mumu.cake.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @ClassName: ShopCartDataConverter
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/22 11:06
 * @Version: 1.0
 */
public class ShopCartDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = dataArray.size();
        for(int i = 0; i < size; i++){
            final JSONObject data = dataArray.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String desc = data.getString("desc");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(ShopCartFields.TITLE, title)
                    .setField(ShopCartFields.DESC, desc)
                    .setField(ShopCartFields.COUNT, count)
                    .setField(ShopCartFields.PRICE, price)
                    .setField(ShopCartFields.IS_SELECTED, false)
                    .setField(ShopCartFields.POSITION, i)
                    .build();

            dataList.add(entity);
        }

        return dataList;
    }
}
