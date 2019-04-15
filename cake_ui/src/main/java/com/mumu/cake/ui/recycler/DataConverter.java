package com.mumu.cake.ui.recycler;

import java.util.ArrayList;

/**
 * @ClassName: DataConverter
 * @Description: 数据转换的接口
 * @Author: 范琳琳
 * @CreateDate: 2019/3/16 16:51
 * @Version: 1.0
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData(){
        if(mJsonData == null || mJsonData.isEmpty()){
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }
}
