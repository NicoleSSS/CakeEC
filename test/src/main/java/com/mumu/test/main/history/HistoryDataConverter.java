package com.mumu.test.main.history;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ConvertUtils;
import com.mumu.cake.ui.recycler.DataConverter;
import com.mumu.cake.ui.recycler.MultipleFields;
import com.mumu.cake.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @ClassName: HistoryDataConverter
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/29 21:34
 * @Version: 1.0
 */
public class HistoryDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONObject dataRoot = JSON.parseObject(getJsonData()).getJSONObject("data");

        final JSONArray array = dataRoot.getJSONArray("list");
        final int size = array.size();
        for (int i = 0; i < size; i++) {

            final JSONObject data = array.getJSONObject(i);
            final int alarm_level = data.getInteger("alarm_level");
            final String alarm_time = data.getString("alarm_time");
            final String recovery_time = data.getString("recovery_time");

            final String message = data.getString("message");
            final String state = data.getString("state");
            final String device_name = data.getString("device_name");
            final String dispose_man = data.getString("dispose_man");
            final String hospital = data.getString("hospital");

            final int id = data.getInteger("id");
            final int device_id = data.getInteger("device_id");
            final int hospital_id = data.getInteger("hospital_id");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(HistoryItemType.ITEM_HISTORY)
                    .setField(MultipleFields.ID, id)
                    .setField(HistoryItemFields.STATE, state)
                    .setField(HistoryItemFields.ALARM_LEVEL, alarm_level)
                    .setField(HistoryItemFields.ALARM_TIME, alarm_time)
                    .setField(HistoryItemFields.DEVICE_ID, device_id)
                    .setField(HistoryItemFields.DISPOSE_MAN, dispose_man)
                    .setField(HistoryItemFields.DEVICE_NAME, device_name)
                    .setField(HistoryItemFields.HOSPITAL, hospital)
                    .setField(HistoryItemFields.HOSPITAL_ID, hospital_id)
                    .setField(HistoryItemFields.MESSAGE, message)
                    .setField(HistoryItemFields.RECOVERY_TIME, recovery_time)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
