package com.mumu.cake.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * @ClassName: MultipleItemEntity
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/16 17:38
 * @Version: 1.0
 */
public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUES = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELES_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUEUES);

    MultipleItemEntity(LinkedHashMap<Object,Object> fields) {
        FIELES_REFERENCE.get().putAll(fields);
    }

    public static MultipleItemEntityBuilder builder(){
        return new MultipleItemEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int)FIELES_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key){
        return (T)FIELES_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getFields(){
        return FIELES_REFERENCE.get();
    }

    public final MultiItemEntity setField(Object key, Object value){
        FIELES_REFERENCE.get().put(key, value);
        return this;
    }

}
