package com.mumu.cake.ec.main.index.search;

import com.alibaba.fastjson.JSONArray;
import com.mumu.cake.ui.recycler.DataConverter;
import com.mumu.cake.ui.recycler.MultipleFields;
import com.mumu.cake.ui.recycler.MultipleItemEntity;
import com.mumu.cake.util.storage.CakePreference;

import java.util.ArrayList;

/**
 * @ClassName: SearchDataConverter
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/26 17:38
 * @Version: 1.0
 */
public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr =
                CakePreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if(!jsonStr.equals("")){
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++){
                final String historyItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }

        return ENTITIES;
    }
}
