package com.mumu.cake.ec.main.sort.content;

/**
 * @ClassName: SectionContentItemEntity
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/20 18:17
 * @Version: 1.0
 */
public class SectionContentItemEntity {

    private int mGoodsId = 0;
    private String mGoodsName = null;
    private String mGoodsThumb = null;

    public int getGoodsId() {
        return mGoodsId;
    }

    public void setGoodsId(int goodsId) {
        this.mGoodsId = goodsId;
    }

    public String getGoodsName() {
        return mGoodsName;
    }

    public void setGoodsName(String goodsName) {
        this.mGoodsName = goodsName;
    }

    public String getGoodsThumb() {
        return mGoodsThumb;
    }

    public void setGoodsThumb(String goodsThumb) {
        this.mGoodsThumb = goodsThumb;
    }
}
