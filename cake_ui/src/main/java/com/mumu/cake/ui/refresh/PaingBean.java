package com.mumu.cake.ui.refresh;

/**
 * @ClassName: PaingBean
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/16 21:39
 * @Version: 1.0
 */
public final class PaingBean {

    /**当前是第几页*/
    private int mPageIndex = 0;
    /**总数据条数*/
    private int mTotal = 0;
    /**一页显示几条数据*/
    private int mPageSize = 0;
    /**当前已经显示了几条数据*/
    private int mCurrentCount = 0;
    /**加载延迟*/
    private int mDelayed = 0;

    public int getPageIndex() {
        return mPageIndex;
    }

    public PaingBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PaingBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PaingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PaingBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PaingBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    PaingBean addIndex(){
        mPageIndex++;
        return this;
    }
}
