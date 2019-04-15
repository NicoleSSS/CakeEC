package com.mumu.cake.delegates.bottom.image;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

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
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * @ClassName: BaseImageBottomDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/28 19:28
 * @Version: 1.0
 */
public abstract class BaseImageBottomDelegate extends CakeDelegate implements View.OnClickListener{

    private final ArrayList<ImageBottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<ImageBottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    /**记录当前的Fragment*/
    private int mCurrentDelegate = 0;

    /**记录第一个展示Tab*/
    private int mIndexDelegate = 0;

    /**点击之后改变的颜色*/
    private int mClickedColor = Color.RED;

    @BindView(R2.id.image_bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    public abstract LinkedHashMap<ImageBottomTabBean, BottomItemDelegate> setItems(ImageItemBuilder builder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public Object setLayout() {
        return R.layout.delegate_image_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if(setClickedColor() != 0){
            mClickedColor = setClickedColor();
        }

        final ImageItemBuilder builder = ImageItemBuilder.builder();
        final LinkedHashMap<ImageBottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for(Map.Entry<ImageBottomTabBean, BottomItemDelegate> item: ITEMS.entrySet()){
            final ImageBottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for(int i = 0; i < size; i++){
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_image_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout)mBottomBar.getChildAt(i);
            /**设置每个item的点击事件*/
            item.setTag(i);
            item.setOnClickListener(this);
            final AppCompatImageView itemImage = (AppCompatImageView)item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView)item.getChildAt(1);
            final ImageBottomTabBean bean = TAB_BEANS.get(i);
            /**初始化数据*/
            itemImage.setImageResource(bean.getImageNorm());
            itemTitle.setText(bean.getTitle());
            if(i == mIndexDelegate){
                itemImage.setImageResource(bean.getImageForce());
                itemTitle.setTextColor(mClickedColor);
            }
        }
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.image_bottom_bar_delegate_container, mIndexDelegate, delegateArray);

    }

    /**
     * 重置Tab点击时变化
     */
    private void resetColor(){
        final int count = mBottomBar.getChildCount();
        for(int i = 0; i < count; i++){
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final AppCompatImageView itemIcon = (AppCompatImageView) item.getChildAt(0);
            final ImageBottomTabBean bean = TAB_BEANS.get(i);
            itemIcon.setImageResource(bean.getImageNorm());
            final AppCompatTextView itemTitle = (AppCompatTextView)item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onClick(View v) {
        final int tag = (int)v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout)v;
        final AppCompatImageView itemImage = (AppCompatImageView) item.getChildAt(0);
        final ImageBottomTabBean bean = TAB_BEANS.get(tag);
        itemImage.setImageResource(bean.getImageForce());
        final AppCompatTextView itemTitle = (AppCompatTextView)item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
        /**注意showHideFragment参数的先后顺序*/
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tag;
    }
}
