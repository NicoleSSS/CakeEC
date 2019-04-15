package com.mumu.cake.delegates.bottom.icon;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.mumu.cake.R;
import com.mumu.cake.R2;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.delegates.bottom.BottomItemDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * @ClassName: BaseBottomDelegate
 * @Description: 将底部Tab与对应的事件组合到一起
 * @Author: 范琳琳
 * @CreateDate: 2019/3/14 20:48
 * @Version: 1.0
 */
public abstract class BaseBottomDelegate extends CakeDelegate implements View.OnClickListener {

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    /**记录当前的Fragment*/
    private int mCurrentDelegate = 0;

    /**记录第一个展示Tab*/
    private int mIndexDelegate = 0;

    /**点击之后改变的颜色*/
    private int mClickedColor = Color.RED;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if(setClickedColor() != 0){
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for(Map.Entry<BottomTabBean, BottomItemDelegate> item: ITEMS.entrySet()){
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for(int i = 0; i < size; i++){
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout)mBottomBar.getChildAt(i);
            /**设置每个item的点击事件*/
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon = (IconTextView)item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView)item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);
            /**初始化数据*/
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            if(i == mIndexDelegate){
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    /**
     * 重置Tab点击时变化
     */
    private void resetColor(){
        final int count = mBottomBar.getChildCount();
        for(int i = 0; i < count; i++){
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);
            final AppCompatTextView itemTitle = (AppCompatTextView)item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        final int tag = (int)v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout)v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle = (AppCompatTextView)item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
        /**注意showHideFragment参数的先后顺序*/
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tag;
    }
}