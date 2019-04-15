package com.mumu.cake.ec.main.sort.content;


import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @ClassName: SectionBean
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/20 17:51
 * @Version: 1.0
 */
public class SectionBean extends SectionEntity<SectionContentItemEntity> {

    private boolean mIsMore = false;
    private int mId = -1;
    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

    public boolean isMore() {
        return mIsMore;
    }

    public void setIsMore(boolean isMore) {
        this.mIsMore = isMore;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }
}
